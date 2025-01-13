package com.biondic.rssreader.subscriptions.data.repo

import arrow.core.Either
import com.biondic.rssreader.core.model.NetworkError
import com.biondic.rssreader.core.model.RepositoryError
import com.biondic.rssreader.subscriptions.domain.datasource.LocalSubscriptionsDatasource
import com.biondic.rssreader.subscriptions.domain.datasource.RemoteSubscriptionsDatasource
import com.biondic.rssreader.subscriptions.domain.model.Subscription
import com.biondic.rssreader.subscriptions.domain.model.SubscriptionData
import com.biondic.rssreader.subscriptions.domain.repo.SubscriptionRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OfflineFirstSubscriptionRepository(
    private val localSubscriptions: LocalSubscriptionsDatasource,
    private val remoteSubscriptions: RemoteSubscriptionsDatasource,
) : SubscriptionRepository {
    override fun getMySubscriptions(shouldRefresh: Boolean): Flow<SubscriptionData> = flow {
        val localData = localSubscriptions.getSubscriptions()
        emit(SubscriptionData.Local(localData))
        if (shouldRefresh) {
            localData.onRight { subscriptions ->
                coroutineScope {
                    subscriptions.map { it.url }
                        .map { async { remoteSubscriptions.getSubscription(it) } }
                        .map { it.await() }
                        .also { updateDatabaseEntries(it) }
                        .also { emit(SubscriptionData.Remote(it)) }
                }
            }
        }
    }

    private fun updateDatabaseEntries(remoteResults: List<Either<NetworkError, Subscription>>) {
        remoteResults.map { subscriptions -> subscriptions.map { addNewSubscription(it) } }
    }

    override fun addNewSubscription(subscription: Subscription): Either<RepositoryError, Unit> =
        localSubscriptions.saveSubscription(subscription)

    override fun deleteSubscription(url: String): Either<RepositoryError, Unit> =
        localSubscriptions.deleteSubscription(url)

    override fun updateSubscription(subscription: Subscription): Either<RepositoryError, Unit> =
        localSubscriptions.updateSubscription(subscription)
}
