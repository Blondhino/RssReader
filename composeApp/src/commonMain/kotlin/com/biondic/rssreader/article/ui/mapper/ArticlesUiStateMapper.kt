package com.biondic.rssreader.article.ui.mapper

import arrow.core.Either
import arrow.core.Either.Left
import arrow.core.Either.Right
import com.biondic.rssreader.article.domain.model.Article
import com.biondic.rssreader.article.ui.model.ArticleScreenState
import com.biondic.rssreader.article.ui.model.UIArticleItem
import com.biondic.rssreader.core.model.RepositoryError

class ArticlesUiStateMapper {
    fun map(
        refreshing: Boolean,
        articles: Either<RepositoryError, List<Article>>,
        sourceTitle: String,
    ): ArticleScreenState = when (articles) {
        is Left -> ArticleScreenState.Error
        is Right -> ArticleScreenState.Content(
            isRefreshing = refreshing,
            articles = articles.value.map { article ->
                UIArticleItem(
                    title = article.title,
                    description = article.description,
                    imageUrl = article.imageUrl,
                    externalUrl = article.externLink,
                )
            }.distinctBy { it.title },
            screenTitle = sourceTitle,
        )
    }
}
