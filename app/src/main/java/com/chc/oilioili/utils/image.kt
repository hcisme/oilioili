package com.chc.oilioili.utils

fun getCompleteImage(url: String, isThumbnail: Boolean = true): String {
    return "${BASE_URL}/api/web/file/getResource?sourceName=${url}${if (isThumbnail) "_thumbnail.jpg" else ""}"
}
