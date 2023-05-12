package com.example.fixit

import com.example.fixit.database.ReviewDataAccess
import com.google.firebase.database.DatabaseReference
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ReviewDataAccessTest {
    @MockK
    lateinit var databaseReference: DatabaseReference

    @RelaxedMockK
    lateinit var onSuccess: () -> Unit

    @RelaxedMockK
    lateinit var onFailure: () -> Unit

    private lateinit var reviewDataAccess: ReviewDataAccess

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        reviewDataAccess = ReviewDataAccess()
    }

    @Test
    fun deleteReview_successful() {
        every { databaseReference.child("reviews").child("review_name_here").removeValue() } returns mockk()

        reviewDataAccess.deleteReview(
            "review_name_here",
            databaseReference,
            onSuccess,
            onFailure
        )

        verify { onSuccess.invoke() }
    }

    @Test
    fun deleteReview_failure() {
        every { databaseReference.child("reviews").child("review_name_here").removeValue() }
            .throws(RuntimeException("Database error"))

        reviewDataAccess.deleteReview(
            "review_name_here",
            databaseReference,
            onSuccess,
            onFailure
        )

        verify { onFailure.invoke() }
    }
}