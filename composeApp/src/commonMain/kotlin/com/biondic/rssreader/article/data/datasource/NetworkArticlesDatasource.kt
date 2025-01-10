package com.biondic.rssreader.article.data.datasource

import arrow.core.Either
import com.biondic.rssreader.article.data.mapper.RssFeedToArticlesMapper
import com.biondic.rssreader.article.domain.datasource.RemoteArticlesDatasource
import com.biondic.rssreader.article.domain.model.Article
import com.biondic.rssreader.core.model.NetworkError
import com.biondic.rssreader.core.networking.FeedFetcher

class NetworkArticlesDatasource(
    private val feedFetcher: FeedFetcher,
    private val articlesMapper: RssFeedToArticlesMapper,
) : RemoteArticlesDatasource {
    override suspend fun getArticlesForSubscription(url: String): Either<NetworkError, List<Article>> =
        feedFetcher(url).map(articlesMapper::map)
}
