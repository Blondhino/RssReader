package com.biondic.rssreader.subscriptions.domain.usecase

import arrow.core.Either
import arrow.core.raise.either
import model.RepositoryError
import networking.FeedFetcher
import com.biondic.rssreader.subscriptions.data.mapper.RssFeedToSubscriptionMapper
import com.biondic.rssreader.subscriptions.domain.repo.SubscriptionRepository

class AddNewSubscription(
    private val repository: SubscriptionRepository,
    private val feedFetcher: FeedFetcher,
    private val feedToSubscriptionMapper: RssFeedToSubscriptionMapper,
) {
    suspend operator fun invoke(url: String): Either<RepositoryError, Unit> = either {
        feedFetcher.invoke(url)
            .map(feedToSubscriptionMapper::map)
            .map(repository::addNewSubscription)
            .bind()
    }
}
