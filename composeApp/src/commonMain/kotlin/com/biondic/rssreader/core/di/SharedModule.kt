package com.biondic.rssreader.core.di

import com.biondic.rssreader.article.di.articleModule
import com.biondic.rssreader.core.database.di.databaseModule
import com.biondic.rssreader.core.networking.di.networkingModule
import com.biondic.rssreader.core.utils.Dictionary
import com.biondic.rssreader.subscriptions.di.subscriptionsModule
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun sharedModule() = module {
    factoryOf(::Dictionary)
    includes(subscriptionsModule())
    includes(articleModule())
    includes(networkingModule())
    includes(databaseModule())
}
