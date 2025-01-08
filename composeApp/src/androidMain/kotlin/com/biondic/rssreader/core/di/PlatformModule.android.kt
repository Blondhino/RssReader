package com.biondic.rssreader.core.di

import com.biondic.rssreader.core.database.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    factory { DatabaseDriverFactory(get()).createDriver() }
}
