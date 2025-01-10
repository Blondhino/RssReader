package com.biondic.rssreader.article.data.mapper

import com.biondic.rssreader.article.domain.model.Article
import com.biondic.rssreader.core.model.rss.RssFeed

class RssFeedToArticlesMapper {
    fun map(feed: RssFeed): List<Article> = feed.items.map {
        Article(
            title = it.title,
            description = it.description,
            imageUrl = it.mediaUrl,
            externLink = it.link,
            parentUrl = feed.subscriptionInfo.link,
        )
    }
}
