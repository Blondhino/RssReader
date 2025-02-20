package com.biondic.rssreader.subscriptions.ui.mapper

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.right
import model.RepositoryError
import com.biondic.rssreader.subscriptions.domain.model.Subscription
import com.biondic.rssreader.subscriptions.domain.model.SubscriptionData
import com.biondic.rssreader.subscriptions.domain.model.SubscriptionData.Local
import com.biondic.rssreader.subscriptions.domain.model.SubscriptionData.Remote

class ExtractSubscriptions(
    private val mergeRemoteDataIntoLocalItems: MergeRemoteDataIntoLocalItems,
) {
    private val localItems: MutableList<Subscription> = mutableListOf()
    operator fun invoke(
        subscriptionData: SubscriptionData,
    ): Either<RepositoryError, List<Subscription>> = when (subscriptionData) {
        is Local -> getSubscriptionsOrErrorFromLocalData(subscriptionData)
        is Remote -> combineExistingSubscriptionsWithRemoteData(subscriptionData)
    }

    private fun getSubscriptionsOrErrorFromLocalData(data: Local) = either {
        data.results.onRight {
            localItems.clear()
            localItems.addAll(it)
        }.bind()
    }

    private fun combineExistingSubscriptionsWithRemoteData(remoteData: Remote) =
        mergeRemoteDataIntoLocalItems(remoteData, localItems).right()
}
