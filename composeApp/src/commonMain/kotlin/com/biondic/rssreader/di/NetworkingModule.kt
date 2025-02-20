package com.biondic.rssreader.di

import networking.FeedFetcher
import networking.provideHttpClient
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun networkingModule() = module {
    singleOf(::provideHttpClient)
    factoryOf(::FeedFetcher)
}
