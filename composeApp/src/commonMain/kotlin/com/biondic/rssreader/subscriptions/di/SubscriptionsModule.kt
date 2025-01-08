package com.biondic.rssreader.subscriptions.di

import com.biondic.rssreader.subscriptions.data.datasource.DatabaseSubscriptionsDatasource
import com.biondic.rssreader.subscriptions.data.datasource.NetworkSubscriptionsDatasource
import com.biondic.rssreader.subscriptions.data.mapper.RssFeedToSubscriptionMapper
import com.biondic.rssreader.subscriptions.data.mapper.SubscriptionEntityMapper
import com.biondic.rssreader.subscriptions.data.repo.OfflineFirstSubscriptionRepository
import com.biondic.rssreader.subscriptions.domain.datasource.LocalSubscriptionsDatasource
import com.biondic.rssreader.subscriptions.domain.datasource.RemoteSubscriptionsDatasource
import com.biondic.rssreader.subscriptions.domain.repo.SubscriptionRepository
import com.biondic.rssreader.subscriptions.domain.usecase.AddNewSubscription
import com.biondic.rssreader.subscriptions.domain.usecase.DeleteSubscription
import com.biondic.rssreader.subscriptions.domain.usecase.GetMySubscriptions
import com.biondic.rssreader.subscriptions.domain.usecase.ToggleSubscriptionIsFavoriteState
import com.biondic.rssreader.subscriptions.ui.SubscriptionsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun subscriptionsModule() = module {
    factoryOf(::SubscriptionsViewModel)
    factoryOf(::AddNewSubscription)
    factoryOf(::GetMySubscriptions)
    factoryOf(::DeleteSubscription)
    factoryOf(::ToggleSubscriptionIsFavoriteState)
    factoryOf(::OfflineFirstSubscriptionRepository) bind SubscriptionRepository::class
    factoryOf(::DatabaseSubscriptionsDatasource) bind LocalSubscriptionsDatasource::class
    factoryOf(::NetworkSubscriptionsDatasource) bind RemoteSubscriptionsDatasource::class
    factoryOf(::RssFeedToSubscriptionMapper)
    factoryOf(::SubscriptionEntityMapper)
}
