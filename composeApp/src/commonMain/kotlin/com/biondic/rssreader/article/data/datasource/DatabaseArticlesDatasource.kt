package com.biondic.rssreader.article.data.datasource

import arrow.core.Either
import com.biondic.rssreader.ArticleQueries
import com.biondic.rssreader.article.data.mapper.ArticleEntityMapper
import com.biondic.rssreader.article.domain.datasource.LocalArticlesDatasource
import com.biondic.rssreader.article.domain.model.Article
import database.safeDatabaseCall
import model.DatabaseError

class DatabaseArticlesDatasource(
    private val articlesTable: ArticleQueries,
    private val entityMapper: ArticleEntityMapper,
) : LocalArticlesDatasource {
    override suspend fun getArticlesForSubscription(url: String): Either<DatabaseError, List<Article>> =
        safeDatabaseCall {
            articlesTable.getAllBySubscription(url).executeAsList().map(entityMapper::map)
        }

    override suspend fun updateArticles(
        articles: List<Article>,
        subscriptionUrl: String,
    ): Either<DatabaseError, Unit> =
        safeDatabaseCall {
            articlesTable.deleteAllWithSubscription(subscriptionUrl)
            articles.forEach { article ->
                articlesTable.insert(
                    title = article.title,
                    externalUrl = article.externLink,
                    description = article.description,
                    imageUrl = article.imageUrl,
                    subscriptionUrl = article.parentUrl,
                )
            }
        }
}
