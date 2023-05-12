package com.example.workerside

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testInputAmount() {
        val inputAmount = "1000"
        assertTrue(isCorrectAmount(inputAmount))
    }

    @Test
    fun testInputAmountIncorrect() {
        val inputAmount = ""
        assertFalse(isIncorrectCorrectAmount(inputAmount))
    }

    fun isCorrectAmount(inputAmount: String): Boolean {
        return inputAmount.isNotEmpty()
    }

    fun isIncorrectCorrectAmount(inputAmount: String): Boolean {
        return inputAmount.isEmpty()
    }

}