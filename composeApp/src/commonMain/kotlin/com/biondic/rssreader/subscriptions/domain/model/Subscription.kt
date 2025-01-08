package com.biondic.rssreader.subscriptions.domain.model

data class Subscription(
    val url: String,
    val imageUrl: String,
    val isFavorite: Boolean,
    val title: String,
    val description: String,
)
