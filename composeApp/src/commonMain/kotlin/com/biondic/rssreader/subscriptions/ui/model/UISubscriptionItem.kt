package com.biondic.rssreader.subscriptions.ui.model

import com.biondic.rssreader.subscriptions.domain.model.Subscription

data class UISubscriptionItem(
    val title: String,
    val url: String,
    val isFavorite: Boolean,
    val imageUrl: String,
    val description: String,
) {
    fun toDomain() = Subscription(
        title = title,
        url = url,
        isFavorite = isFavorite,
        imageUrl = imageUrl,
        description = description,
    )
}
