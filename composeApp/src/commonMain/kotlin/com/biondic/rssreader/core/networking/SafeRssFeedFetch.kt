package com.biondic.rssreader.core.networking

import arrow.core.Either
import arrow.core.raise.either
import com.biondic.rssreader.core.model.NetworkError
import com.biondic.rssreader.core.model.rss.RssFeed
import com.biondic.rssreader.core.model.rss.RssString
import com.biondic.rssreader.core.model.rss.RssString.Companion.feed
import kotlinx.coroutines.CancellationException

suspend fun safeRssFeedFetch(
    apiRequest: suspend () -> String,
): Either<NetworkError, RssFeed> = either {
    Either.catch {
        RssString(apiRequest()).mapLeft { NetworkError.SerializationError }.bind().feed()
    }.mapLeft {
        if (it is CancellationException) throw it
        NetworkError.UnknownNetworkError(it.message.orEmpty())
    }.bind()
}
