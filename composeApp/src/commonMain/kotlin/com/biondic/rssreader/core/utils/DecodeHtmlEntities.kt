package com.biondic.rssreader.core.utils

fun decodeHtmlEntities(input: String): String { // Regular expression to match numeric HTML entities (e.g., &#38;)
    val regex = "&#(\\d+);".toRegex()
    return regex.replace(input) { matchResult ->
        val codePoint = matchResult.groupValues[1].toIntOrNull()
        codePoint?.toChar()?.toString()
            ?: matchResult.value // If not a valid numeric entity, return the original match
    }
}
