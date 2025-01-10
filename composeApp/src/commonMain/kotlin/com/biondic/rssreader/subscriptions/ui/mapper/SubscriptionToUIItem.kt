package com.biondic.rssreader.subscriptions.ui.mapper

import com.biondic.rssreader.subscriptions.domain.model.Subscription
import com.biondic.rssreader.subscriptions.ui.model.UISubscriptionItem

fun Subscription.toUiItem() = UISubscriptionItem(
    url = url,
    imageUrl = imageUrl,
    title = title,
    description = description,
    isFavorite = isFavorite,
)
