package com.biondic.rssreader.di

import navigation.AppNavigator
import navigation.Navigation
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun navigationModule(): Module = module {
    singleOf(::AppNavigator) bind Navigation::class
}