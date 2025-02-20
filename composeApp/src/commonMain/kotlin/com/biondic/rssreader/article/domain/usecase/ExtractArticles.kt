package com.biondic.rssreader.article.domain.usecase

import arrow.core.Either
import arrow.core.Either.Left
import arrow.core.Either.Right
import arrow.core.raise.either
import arrow.core.right
import com.biondic.rssreader.article.domain.model.Article
import com.biondic.rssreader.article.domain.model.ArticleData
import com.biondic.rssreader.article.domain.model.ArticleData.Local
import com.biondic.rssreader.article.domain.model.ArticleData.Remote
import model.RepositoryError

class ExtractArticles {
    private val localItems: MutableList<Article> = mutableListOf()
    operator fun invoke(
        articleData: ArticleData,
    ): Either<RepositoryError, List<Article>> = when (articleData) {
        is Remote -> updateWithRemoteDataOrReturnLocal(articleData).right()
        is Local -> getArticlesOrErrorFromLocalData(articleData)
    }

    private fun getArticlesOrErrorFromLocalData(data: Local) = either {
        data.results.onRight {
            localItems.clear()
            localItems.addAll(it)
        }.bind()
    }

    private fun updateWithRemoteDataOrReturnLocal(remoteData: Remote): List<Article> =
        when (remoteData.results) {
            is Left -> localItems
            is Right -> {
                localItems.clear()
                localItems.addAll(remoteData.results.value)
                remoteData.results.value
            }
        }
}
