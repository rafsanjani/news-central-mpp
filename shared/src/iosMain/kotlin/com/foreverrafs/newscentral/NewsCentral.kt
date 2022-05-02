package com.foreverrafs.newscentral

fun NewsCentral.Companion.create(withLog: Boolean) = NewsCentral(
    httpClient = IosHttpClient(withLog = withLog)
)