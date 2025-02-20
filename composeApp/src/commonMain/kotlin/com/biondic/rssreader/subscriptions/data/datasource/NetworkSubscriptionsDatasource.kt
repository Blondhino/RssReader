package com.biondic.rssreader.subscriptions.data.datasource

import arrow.core.Either
import model.NetworkError
import networking.FeedFetcher
import com.biondic.rssreader.subscriptions.data.mapper.RssFeedToSubscriptionMapper
import com.biondic.rssreader.subscriptions.domain.datasource.RemoteSubscriptionsDatasource
import com.biondic.rssreader.subscriptions.domain.model.Subscription

class NetworkSubscriptionsDatasource(
    private val feedFetcher: FeedFetcher,
    private val feedMapper: RssFeedToSubscriptionMapper,
) : RemoteSubscriptionsDatasource {
    override suspend fun getSubscription(url: String): Either<NetworkError, Subscription> =
        feedFetcher(url).map(feedMapper::map)
}
