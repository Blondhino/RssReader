package com.biondic.rssreader.subscriptions.domain.datasource

import arrow.core.Either
import model.DatabaseError
import com.biondic.rssreader.subscriptions.domain.model.Subscription

interface LocalSubscriptionsDatasource {
    fun getSubscriptions(): Either<DatabaseError, List<Subscription>>
    fun getSingleSubscription(url: String): Either<DatabaseError, List<Subscription>>
    fun saveSubscription(subscription: Subscription): Either<DatabaseError, Unit>
    fun deleteSubscription(url: String): Either<DatabaseError, Unit>
    fun updateSubscription(subscription: Subscription): Either<DatabaseError, Unit>
}
