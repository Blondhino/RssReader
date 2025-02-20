package model.rss

data class RssFeed(
    val subscriptionInfo: RssSubscriptionInfo,
    val items: List<RssItem>,
)
