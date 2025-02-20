package com.biondic.rssreader.article.domain.usecase

import arrow.core.Either
import com.biondic.rssreader.article.domain.model.Article
import com.biondic.rssreader.article.domain.model.ArticleData.Remote
import com.biondic.rssreader.article.domain.repo.ArticleRepository
import model.RefreshStrategy
import model.RefreshStrategy.SyncWithRemote
import model.RepositoryError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class GetArticles(
    private val repository: ArticleRepository,
    private val extractArticles: ExtractArticles,
) {
    operator fun invoke(
        subscriptionUrl: String,
        refreshStrategy: RefreshStrategy,
        refreshingCompleted: () -> Unit,
    ): Flow<Either<RepositoryError, List<Article>>> = repository.getArticlesForSubscription(
        shouldRefresh = refreshStrategy is SyncWithRemote,
        url = subscriptionUrl,
    ).onEach { if (it is Remote) refreshingCompleted() }
        .map(extractArticles::invoke)
}
