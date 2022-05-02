package com.foreverrafs.newscentral

fun NewsCentral.Companion.create() = NewsCentral(
    httpClient = AndroidHttpClient(withLog = true)
)