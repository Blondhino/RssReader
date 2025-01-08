package com.biondic.rssreader.subscriptions.data.mapper

import com.biondic.rssreader.SubscriptionEntity
import com.biondic.rssreader.subscriptions.domain.model.Subscription

class SubscriptionEntityMapper {
    fun map(entity: SubscriptionEntity) = Subscription(
        url = entity.url,
        imageUrl = entity.imageUrl,
        isFavorite = entity.isFavorite.toInt() == 1,
        title = entity.title,
        description = entity.description,
    )

    fun map(subscriptions: List<SubscriptionEntity>) = subscriptions.map { map(it) }
}
