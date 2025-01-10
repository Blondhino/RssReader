package com.biondic.rssreader.subscriptions.domain.usecase

import arrow.core.Either
import com.biondic.rssreader.core.model.RepositoryError
import com.biondic.rssreader.subscriptions.domain.model.Subscription
import com.biondic.rssreader.subscriptions.domain.repo.SubscriptionRepository

class ToggleSubscriptionIsFavoriteState(
    private val repository: SubscriptionRepository,
) {
    operator fun invoke(subscription: Subscription): Either<RepositoryError, Unit> =
        repository.updateSubscription(subscription.copy(isFavorite = !subscription.isFavorite))
}
