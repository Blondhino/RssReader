package com.biondic.rssreader.core.networking

import arrow.core.Either
import com.biondic.rssreader.core.model.NetworkError
import com.biondic.rssreader.core.model.rss.RssFeed
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class FeedFetcher(
    private val httpClient: HttpClient,
) {
    suspend operator fun invoke(url: String): Either<NetworkError, RssFeed> = safeRssFeedFetch {
        httpClient.get(url).bodyAsText()
    }.map { it.setOriginLink(url) }

    private fun RssFeed.setOriginLink(url: String) =
        copy(subscriptionInfo = subscriptionInfo.copy(link = url))
}
