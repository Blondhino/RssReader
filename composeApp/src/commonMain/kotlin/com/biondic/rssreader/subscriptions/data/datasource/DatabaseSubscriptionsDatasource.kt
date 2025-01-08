package com.biondic.rssreader.subscriptions.data.datasource

import arrow.core.Either
import com.biondic.rssreader.SubscriptionQueries
import com.biondic.rssreader.core.database.safeDatabaseCall
import com.biondic.rssreader.core.model.DatabaseError
import com.biondic.rssreader.subscriptions.data.mapper.SubscriptionEntityMapper
import com.biondic.rssreader.subscriptions.domain.datasource.LocalSubscriptionsDatasource
import com.biondic.rssreader.subscriptions.domain.model.Subscription

class DatabaseSubscriptionsDatasource(
    private val subscriptionTable: SubscriptionQueries,
    private val entityMapper: SubscriptionEntityMapper,
) : LocalSubscriptionsDatasource {
    override fun getSubscriptions(): Either<DatabaseError, List<Subscription>> =
        safeDatabaseCall { subscriptionTable.getAll().executeAsList() }
            .map(entityMapper::map)

    override fun getSingleSubscription(url: String): Either<DatabaseError, List<Subscription>> =
        safeDatabaseCall { subscriptionTable.getByUrl(url).executeAsList() }
            .map { entityMapper.map(it) }

    override fun saveSubscription(subscription: Subscription): Either<DatabaseError, Unit> =
        safeDatabaseCall {
            subscriptionTable.insert(
                url = subscription.url,
                title = subscription.title,
                description = subscription.description,
                imageUrl = subscription.imageUrl,
                isFavorite = if (subscription.isFavorite) 1L else 0L,
            )
        }

    override fun deleteSubscription(url: String): Either<DatabaseError, Unit> =
        safeDatabaseCall { subscriptionTable.delete(url) }

    override fun updateSubscription(subscription: Subscription): Either<DatabaseError, Unit> =
        safeDatabaseCall {
            subscriptionTable.updateSubscription(
                url = subscription.url,
                title = subscription.title,
                description = subscription.description,
                imageUrl = subscription.imageUrl,
                isFavorite = if (subscription.isFavorite) 1L else 0L,
            )
        }
}
