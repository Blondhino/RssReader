package com.biondic.rssreader.di

import com.biondic.rssreader.RssDatabase
import database.provideDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun databaseModule() = module {
    singleOf(::provideDatabase)
    factory { get<RssDatabase>().subscriptionQueries }
    factory { get<RssDatabase>().articleQueries }
}
