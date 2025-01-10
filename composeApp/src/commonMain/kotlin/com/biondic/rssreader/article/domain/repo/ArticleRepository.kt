package com.biondic.rssreader.article.domain.repo

import com.biondic.rssreader.article.domain.model.ArticleData
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun getArticlesForSubscription(
        url: String,
        shouldRefresh: Boolean,
    ): Flow<ArticleData>
}
