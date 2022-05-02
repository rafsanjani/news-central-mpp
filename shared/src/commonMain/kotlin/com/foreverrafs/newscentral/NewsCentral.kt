package com.foreverrafs.newscentral

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class NewsCentral internal
constructor(
    private val httpClient: HttpClient
) {
    private val baseUrl =
        "https://us-central1-ardent-gate-173721.cloudfunctions.net/myApp/news/page/"

    @Throws(Exception::class)
    suspend fun fetchNews(page: Int): Result {
        return try {
            val response: NewsResponse =  httpClient.get("$baseUrl/$page").body()
            Result.Success(response.articles.distinctBy { it.headline })
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
    companion object
}
