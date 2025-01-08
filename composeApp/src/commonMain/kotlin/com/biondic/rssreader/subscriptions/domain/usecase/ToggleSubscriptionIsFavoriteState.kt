package com.biondic.rssreader.subscriptions.domain.usecase

import arrow.core.Either
import com.biondic.rssreader.core.model.RepositoryError
import com.biondic.rssreader.subscriptions.data.model.SubscriptionData
import com.biondic.rssreader.subscriptions.domain.model.Subscription
import com.biondic.rssreader.subscriptions.domain.repo.SubscriptionRepository
import kotlinx.coroutines.flow.first

class ToggleSubscriptionIsFavoriteState(
    private val repository: SubscriptionRepository,
) {
    suspend fun invoke(subscription: Subscription): Either<RepositoryError, SubscriptionData> =
        repository.updateSubscription(subscription.copy(isFavorite = !subscription.isFavorite))
            .map {
                repository.getMySubscriptions(false).first()
            }
}
