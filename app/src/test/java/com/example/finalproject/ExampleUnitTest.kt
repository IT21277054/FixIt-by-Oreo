package com.example.finalproject

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun validValues() {
        val title = "Gardner"
        val description = "Cleaning Garden"
        assertTrue(testValue(title,description))
    }

    @Test
    fun inValidValues() {
        val title = "Choose Value"
        val description = ""
        assertFalse(testValue(title,description))
    }


    fun testValue(title: String,description:String): Boolean {

        if (title == "Choose Value" || description.isBlank()) {
//            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            return false
        }

        if (description.isEmpty()) {
//            descriptionEditText.error = "Description is required"
            return false
        }

        return true
    }



}

