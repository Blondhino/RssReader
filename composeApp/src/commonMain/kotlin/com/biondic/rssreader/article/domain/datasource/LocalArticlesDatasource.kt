package com.biondic.rssreader.article.domain.datasource

import arrow.core.Either
import com.biondic.rssreader.article.domain.model.Article
import com.biondic.rssreader.core.model.DatabaseError

interface LocalArticlesDatasource {
    suspend fun getArticlesForSubscription(url: String): Either<DatabaseError, List<Article>>
    suspend fun updateArticles(
        articles: List<Article>,
        subscriptionUrl: String,
    ): Either<DatabaseError, Unit>
}
