package com.biondic.rssreader.subscriptions.data.mapper

import model.rss.RssFeed
import com.biondic.rssreader.subscriptions.domain.model.Subscription

class RssFeedToSubscriptionMapper {
    fun map(feed: RssFeed) = Subscription(
        url = feed.subscriptionInfo.link,
        imageUrl = feed.subscriptionInfo.imageUrl,
        isFavorite = false,
        title = feed.subscriptionInfo.title,
        description = feed.subscriptionInfo.description,
    )
}
