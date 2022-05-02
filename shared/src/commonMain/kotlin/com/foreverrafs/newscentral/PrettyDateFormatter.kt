package com.foreverrafs.newscentral

expect class PrettyDateFormatter internal constructor(pattern: String){
    fun format(dateTime: String): String

    companion object {
        @Throws(Exception::class)
        fun ofPattern(pattern: String): PrettyDateFormatter
    }
}