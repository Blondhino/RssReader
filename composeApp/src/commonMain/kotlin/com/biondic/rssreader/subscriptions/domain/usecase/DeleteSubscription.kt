package com.biondic.rssreader.subscriptions.domain.usecase

import com.biondic.rssreader.subscriptions.domain.repo.SubscriptionRepository

class DeleteSubscription(
    private val repository: SubscriptionRepository,
) {
    operator fun invoke(url: String) = repository.deleteSubscription(url)
}
