package com.example.fixit.database

import android.util.Log
import android.widget.Toast
import com.example.fixit.model.ReviewData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ReviewDataAccess {

    fun createReview(
        review: ReviewData,
        database: DatabaseReference,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        val reviewRef = review.name?.let { database.child("reviews").child(it) }

        reviewRef?.setValue(review)?.addOnSuccessListener {
            onSuccess()
        }?.addOnFailureListener {
            onFailure()
        }?.addOnCompleteListener {
            Log.d("EditReviewActivity", "Database operation complete")
        }
    }

    fun deleteReview(
        reviewName: String,
        database: DatabaseReference,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        val reviewRef = database.child("reviews").child(reviewName)

        reviewRef.removeValue()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure()
            }
            .addOnCompleteListener {
                Log.d("EditReviewActivity", "Database operation complete")
            }
    }

    fun editReview(
        review: ReviewData,
        database: DatabaseReference,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        val reviewRef = review.name?.let { database.child("reviews").child(it) }

        reviewRef?.setValue(review)?.addOnSuccessListener {
            onSuccess()
        }?.addOnFailureListener {
            onFailure()
        }?.addOnCompleteListener {
            Log.d("EditReviewActivity", "Database operation complete")
        }
    }

//    fun getReview(
//        reviewName: String,
//        database: DatabaseReference,
//    ): ReviewData {
//        val reviewRef = database.child("reviews").child(reviewName)
//
//        reviewRef.get()
//            .addOnSuccessListener {
//                return it.getValue(ReviewData)
//            }
//            .addOnFailureListener {
//                onFailure()
//            }
//            .addOnCompleteListener {
//                Log.d("EditReviewActivity", "Database operation complete")
//            }
//    }
}