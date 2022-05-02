package com.foreverrafs.newscentral

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

actual class PrettyDateFormatter internal actual constructor(pattern: String) {

    private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(pattern)

    actual fun format(dateTime: String): String {
        val localDate = ZonedDateTime.parse(dateTime)
        return dateTimeFormatter.format(localDate)
    }

    actual companion object {
        actual fun ofPattern(pattern: String): PrettyDateFormatter {
            return PrettyDateFormatter(pattern)
        }
    }
}