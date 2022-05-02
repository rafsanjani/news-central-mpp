package com.foreverrafs.newscentral

sealed class Result {
    data class Success(val data: List<Article>) : Result()
    data class Error(val exception: Exception) : Result()
    object Loading : Result()
}
