package com.biondic.rssreader.article.data.repo

import com.biondic.rssreader.article.domain.datasource.LocalArticlesDatasource
import com.biondic.rssreader.article.domain.datasource.RemoteArticlesDatasource
import com.biondic.rssreader.article.domain.model.ArticleData
import com.biondic.rssreader.article.domain.model.ArticleData.Local
import com.biondic.rssreader.article.domain.model.ArticleData.Remote
import com.biondic.rssreader.article.domain.repo.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OfflineFirstArticleRepository(
    private val localArticles: LocalArticlesDatasource,
    private val remoteArticles: RemoteArticlesDatasource,
) : ArticleRepository {
    override fun getArticlesForSubscription(
        url: String,
        shouldRefresh: Boolean,
    ): Flow<ArticleData> = flow {
        val localData = localArticles.getArticlesForSubscription(url)
        emit(Local(localData))
            .also {
                if (shouldRefresh) {
                    remoteArticles.getArticlesForSubscription(url)
                        .also { emit(Remote(it)) }
                        .map { localArticles.updateArticles(articles = it, subscriptionUrl = url) }
                }
            }
    }
}
