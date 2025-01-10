package com.biondic.rssreader.article.domain.datasource

import arrow.core.Either
import com.biondic.rssreader.article.domain.model.Article
import com.biondic.rssreader.core.model.NetworkError

interface RemoteArticlesDatasource {
    suspend fun getArticlesForSubscription(url: String): Either<NetworkError, List<Article>>
}
