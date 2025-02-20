package utils

fun String.extractPlainTextFromHtml(): String {
    val noTags = replace(Regex("<[^>]*>"), "")
    val decodedText = noTags.replace(Regex("&#(\\d+);")) { match ->
        val charCode = match.groupValues[1].toIntOrNull()
        charCode?.toChar()?.toString() ?: match.value
    }
    return decodedText
        .replace("&nbsp;", " ")
        .replace("&amp;", "&")
        .replace("&lt;", "<")
        .replace("&gt;", ">")
        .replace("&quot;", "\"")
        .replace("&apos;", "'")
        .replace("&quot;", "\"")
        .replace("&#8216;", "‘")
        .replace("&#8217;", "’")
        .replace("&#8230;", "…")
}
