package com.biondic.rssreader.di

import com.biondic.rssreader.article.di.articleModule
import Dictionary
import com.biondic.rssreader.subscriptions.di.subscriptionsModule
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun sharedModule() = module {
    factoryOf(::Dictionary)
    includes(subscriptionsModule())
    includes(articleModule())
    includes(networkingModule())
    includes(databaseModule())
    includes(navigationModule())
}
