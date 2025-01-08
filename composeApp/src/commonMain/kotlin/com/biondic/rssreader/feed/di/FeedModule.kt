package com.biondic.rssreader.feed.di

import com.biondic.rssreader.feed.ui.FeedViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun feedModule() = module {
    factoryOf(::FeedViewModel)
}
