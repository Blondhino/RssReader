package database

import app.cash.sqldelight.db.SqlDriver
import com.biondic.rssreader.RssDatabase

fun provideDatabase(driver: SqlDriver): RssDatabase {
    driver.execute(null, "PRAGMA foreign_keys = ON", 0)
    return RssDatabase(driver)
}
