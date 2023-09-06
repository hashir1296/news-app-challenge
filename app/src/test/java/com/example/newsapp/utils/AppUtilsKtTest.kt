package com.example.newsapp.utils

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AppUtilsKtTest {

    private lateinit var helper: Helper

    @Before
    fun setup() {
        helper = Helper()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun isPalindrome() {
        val result = helper.isPalindrome("hello")

        assertEquals(false, result)
    }

    @Test
    fun isPalindrome_level_string() {
        val result = helper.isPalindrome("level")

        assertEquals(true, result)
    }
}