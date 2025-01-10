package com.biondic.rssreader.core.database.di

import com.biondic.rssreader.RssDatabase
import com.biondic.rssreader.core.database.provideDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun databaseModule() = module {
    singleOf(::provideDatabase)
    factory { get<RssDatabase>().subscriptionQueries }
    factory { get<RssDatabase>().articleQueries }
}
