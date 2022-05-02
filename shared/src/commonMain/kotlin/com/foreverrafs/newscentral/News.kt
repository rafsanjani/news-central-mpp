package com.foreverrafs.newscentral

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    @SerialName("status") val status: String,
    @SerialName("message") val message: String,
    @SerialName("news") val articles: List<Article>,
)

@Serializable
data class Article(
    @SerialName("headline") val headline: String,
    @SerialName("id") val id: String,
    @SerialName("content") val content: String,
    @SerialName("imageUrl") val imageUrl: String,
    @SerialName("category") val category: String,
    @SerialName("date") val date: String,
)
