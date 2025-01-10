package com.biondic.rssreader.article.domain.model

data class Article(
    val title: String,
    val description: String,
    val imageUrl: String,
    val externLink: String,
    val parentUrl: String,
)
