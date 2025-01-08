package com.biondic.rssreader.core.database

import app.cash.sqldelight.db.SqlDriver
import com.biondic.rssreader.RssDatabase

fun provideDatabase(driver: SqlDriver): RssDatabase = RssDatabase(driver)
