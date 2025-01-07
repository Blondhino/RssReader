package com.biondic.rssreader.subscriptions.di

import com.biondic.rssreader.subscriptions.SubscriptionsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun subscriptionsModule() = module {
    factoryOf(::SubscriptionsViewModel)
}
