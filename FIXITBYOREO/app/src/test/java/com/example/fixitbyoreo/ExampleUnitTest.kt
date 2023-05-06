package com.example.fixitbyoreo

import android.app.Dialog
import android.widget.EditText
import android.widget.Spinner
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

@Test
fun testShowDialog() {

    // Set up dialog
    whenever(mockDialog.findViewById<Spinner>(R.id.popup_title_spinner)).thenReturn(mockTitleSpinner)
    whenever(mockDialog.findViewById<EditText>(R.id.popup_description_input)).thenReturn(mockDescriptionEditText)
    whenever(mockDialog.findViewById<Button>(R.id.form_submit_button)).thenReturn(mockSubmitButton)

    // Set up views
    whenever(mockTitleSpinner.selectedItem).thenReturn("Title")
    whenever(mockDescriptionEditText.text).thenReturn("Test description")

    // Set up Firebase database
    whenever(mockDatabase.child("job_request")).thenReturn(mockChildReference)
    whenever(mockChildReference.push()).thenReturn(mockChildReference)

    // Action
    val listener = showDialog(mockDialog, mockDatabase)
    mockSubmitButton.performClick()

    // Assertion
    verify(mockChildReference).setValue(any())
    verify(mockDialog).dismiss()
    verify(mockDescriptionEditText, never()).error = anyString()
    verify(listener).onSuccess()
}
