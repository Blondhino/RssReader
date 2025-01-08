package com.biondic.rssreader.subscriptions.domain.usecase

import com.biondic.rssreader.subscriptions.domain.repo.SubscriptionRepository

class GetMySubscriptions(
    private val repository: SubscriptionRepository,
) {
    operator fun invoke(shouldRefresh: Boolean = false) = repository.getMySubscriptions(shouldRefresh)
}
