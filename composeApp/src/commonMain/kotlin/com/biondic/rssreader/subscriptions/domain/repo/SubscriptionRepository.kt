package com.biondic.rssreader.subscriptions.domain.repo

import arrow.core.Either
import com.biondic.rssreader.core.model.RepositoryError
import com.biondic.rssreader.subscriptions.data.model.SubscriptionData
import com.biondic.rssreader.subscriptions.domain.model.Subscription
import kotlinx.coroutines.flow.Flow

interface SubscriptionRepository {
    fun getMySubscriptions(shouldRefresh: Boolean): Flow<SubscriptionData>
    fun addNewSubscription(subscription: Subscription): Either<RepositoryError, Unit>
    fun deleteSubscription(url: String): Either<RepositoryError, Unit>
    fun updateSubscription(subscription: Subscription): Either<RepositoryError, Unit>
}
