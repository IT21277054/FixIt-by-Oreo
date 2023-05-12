package com.example.fixit

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.fixit.model.ReviewData
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class ReviewProfile : AppCompatActivity() {

    //    val intent = Intent(this, ReviewProfile::class.java)
    //    intent.putExtra("added_review", name)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_profile)
        FirebaseApp.initializeApp(this)

        var database = Firebase.database.reference

        try {
            val review_name = intent.getStringExtra("added_review")

            val nameTextView = findViewById<TextView>(R.id.nameTextView)
            val reviewTitleTextView = findViewById<TextView>(R.id.reviewTitleTextView)
            val reviewDescriptionTextView = findViewById<TextView>(R.id.reviewDescriptionTextView)
            val ratingPreviewRatingsBar = findViewById<RatingBar>(R.id.ratingBar_view)

            val editReviewButton = findViewById<Button>(R.id.editReviewButton)

            if (review_name != null) {

                Log.d("Review Activity", review_name)

                database
                    .child("reviews").child(review_name)
                    .get()
                    .addOnSuccessListener {
                        var reviewData = it.getValue(ReviewData::class.java)

//                        if (reviewData != null) {
//                            reviewData.reviewDescription?.let { it1 -> Log.d("Review Activity", it1) }
//                        }
                        if (reviewData != null) {
                            nameTextView.setText(reviewData.name)
                            reviewTitleTextView.setText(reviewData.reviewTitle)
                            reviewDescriptionTextView.setText(reviewData.reviewDescription)
                            ratingPreviewRatingsBar.setRating(reviewData.starRating)

                            editReviewButton.setOnClickListener{
                                val intent = Intent(this, EditReviewActivity::class.java)
                                intent.putExtra("name", reviewData.name)
                                intent.putExtra("email", reviewData.email)
                                intent.putExtra("title", reviewData.reviewTitle)
                                intent.putExtra("description", reviewData.reviewDescription)
                                intent.putExtra("rating", reviewData.starRating)

                                startActivity(intent)
                            }


                        }

                    }.addOnFailureListener {
                        Log.d("Review Activity", "Retrieved Failed")
                        Toast.makeText(this@ReviewProfile,"Couldn't add review", Toast.LENGTH_SHORT ).show()
                    }.addOnCompleteListener {
                        Log.d("Review Activity", "Retrieval Complete")
                    }
            }
//
        }catch (exception:Exception){
            Log.d("Review Activity", exception.toString())
        }


    }

    override fun onRestart() {
        super.onRestart()
        var database = Firebase.database.reference

        try {
            val review_name = intent.getStringExtra("added_review")

            val nameTextView = findViewById<TextView>(R.id.nameTextView)
            val reviewTitleTextView = findViewById<TextView>(R.id.reviewTitleTextView)
            val reviewDescriptionTextView = findViewById<TextView>(R.id.reviewDescriptionTextView)
            val ratingPreviewRatingsBar = findViewById<RatingBar>(R.id.ratingBar_view)

            val editReviewButton = findViewById<Button>(R.id.editReviewButton)

            if (review_name != null) {

                Log.d("Review Activity", review_name)

                database
                    .child("reviews").child(review_name)
                    .get()
                    .addOnSuccessListener {
                        var reviewData = it.getValue(ReviewData::class.java)

//                        if (reviewData != null) {
//                            reviewData.reviewDescription?.let { it1 -> Log.d("Review Activity", it1) }
//                        }
                        if (reviewData != null) {
                            nameTextView.setText(reviewData.name)
                            reviewTitleTextView.setText(reviewData.reviewTitle)
                            reviewDescriptionTextView.setText(reviewData.reviewDescription)
                            ratingPreviewRatingsBar.setRating(reviewData.starRating)

                            editReviewButton.setOnClickListener{
                                val intent = Intent(this, EditReviewActivity::class.java)
                                intent.putExtra("name", reviewData.name)
                                intent.putExtra("email", reviewData.email)
                                intent.putExtra("title", reviewData.reviewTitle)
                                intent.putExtra("description", reviewData.reviewDescription)
                                intent.putExtra("rating", reviewData.starRating)

                                startActivity(intent)
                            }


                        }

                    }.addOnFailureListener {
                        Log.d("Review Activity", "Retrieved Failed")
                        Toast.makeText(this@ReviewProfile,"Couldn't add review", Toast.LENGTH_SHORT ).show()
                    }.addOnCompleteListener {
                        Log.d("Review Activity", "Retrieval Complete")
                    }
            }
//
        }catch (exception:Exception){
            Log.d("Review Activity", exception.toString())
        }
    }
}