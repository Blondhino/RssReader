package com.biondic.rssreader.subscriptions.ui.interaction

interface SubscriptionsViewEffect {
    data class OpenArticles(val url: String, val sourceTitle: String) : SubscriptionsViewEffect
}
