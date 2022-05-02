package com.foreverrafs.newscentral

import platform.Foundation.NSDateFormatter
import platform.Foundation.NSISO8601DateFormatWithFullDate
import platform.Foundation.NSISO8601DateFormatter


actual class PrettyDateFormatter internal actual constructor(pattern: String) {
    private val dateFormatter = NSDateFormatter()
    private val dateConverter = NSISO8601DateFormatter()

    init {
        dateConverter.setFormatOptions(
            NSISO8601DateFormatWithFullDate
        )
        dateFormatter.dateFormat = pattern
    }

    @Throws(Exception::class)
    actual fun format(dateTime: String): String {
        val date = dateConverter.dateFromString(dateTime)
            ?: throw IllegalArgumentException("Error parsing date")

        return dateFormatter.stringFromDate(date)
    }

    actual companion object {
        actual fun ofPattern(pattern: String): PrettyDateFormatter {
            return PrettyDateFormatter(pattern)
        }
    }
}