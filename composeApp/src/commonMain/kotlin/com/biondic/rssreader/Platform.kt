package com.biondic.rssreader

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform