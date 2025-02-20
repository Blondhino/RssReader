package com.biondic.rssreader.subscriptions.domain.datasource

import arrow.core.Either
import model.NetworkError
import com.biondic.rssreader.subscriptions.domain.model.Subscription

interface RemoteSubscriptionsDatasource {
    suspend fun getSubscription(url: String): Either<NetworkError, Subscription>
}
