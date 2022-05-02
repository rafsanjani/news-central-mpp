package com.foreverrafs.newscentral

import kotlin.test.Test
import kotlin.test.assertEquals

class IosGreetingTest {

    @Test
    fun testExample() {
        val formatter = PrettyDateFormatter.ofPattern("MMM dd, yyyy")
        val date = formatter.format("2022-05-01T22:07:00.000Z")
        assertEquals(date, "May 01, 2022")
    }
}