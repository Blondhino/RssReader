package com.biondic.rssreader.core.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.biondic.rssreader.RssDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(
        schema = RssDatabase.Schema,
        context = context,
        name = "rss.db",
    )
}
