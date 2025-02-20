package com.biondic.rssreader.article.domain.model

import arrow.core.Either
import model.DatabaseError
import model.NetworkError

sealed interface ArticleData {
    data class Local(val results: Either<DatabaseError, List<Article>>) : ArticleData
    data class Remote(val results: Either<NetworkError, List<Article>>) : ArticleData
}
