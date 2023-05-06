package com.example.fixit

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    lateinit var signupActivity: Signup
    @Test
    fun testIsValidPhoneNumber_InvalidNumber() {
        val invalidNumber = "12345"
        val invalidNumberWithAlpha = "1234abc"
        val invalidNumberWithSpace = "12 345"
        val invalidNumberWithExtraDigits = "1234567890123456"

        assertFalse(signupActivity.isValidPhoneNumber(invalidNumber))
        assertFalse(signupActivity.isValidPhoneNumber(invalidNumberWithAlpha))
        assertFalse(signupActivity.isValidPhoneNumber(invalidNumberWithSpace))
        assertFalse(signupActivity.isValidPhoneNumber(invalidNumberWithExtraDigits))
    }

}