package com.biondic.rssreader.subscriptions.domain.usecase

import arrow.core.Either
import com.biondic.rssreader.core.model.RefreshStrategy
import com.biondic.rssreader.core.model.RefreshStrategy.SyncWithRemote
import com.biondic.rssreader.core.model.RepositoryError
import com.biondic.rssreader.subscriptions.domain.model.Subscription
import com.biondic.rssreader.subscriptions.domain.model.SubscriptionData.Remote
import com.biondic.rssreader.subscriptions.domain.repo.SubscriptionRepository
import com.biondic.rssreader.subscriptions.ui.mapper.ExtractSubscriptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class GetMySubscriptions(
    private val repository: SubscriptionRepository,
    private val extractSubscriptions: ExtractSubscriptions,
) {
    operator fun invoke(
        refreshStrategy: RefreshStrategy,
        refreshingCompleted: () -> Unit,
    ): Flow<Either<RepositoryError, List<Subscription>>> =
        repository.getMySubscriptions(shouldRefresh = refreshStrategy is SyncWithRemote)
            .onEach { if (it is Remote) refreshingCompleted() }
            .map(extractSubscriptions::invoke)
}
