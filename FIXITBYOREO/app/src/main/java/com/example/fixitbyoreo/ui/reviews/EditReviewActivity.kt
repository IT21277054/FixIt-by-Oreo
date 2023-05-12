package com.example.fixit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fixit.database.ReviewDataAccess
import com.example.fixit.model.ReviewData
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class EditReviewActivity: AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_review)

        FirebaseApp.initializeApp(this)
        var database = Firebase.database.reference


        val review_name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val rating = intent.getFloatExtra("rating", Float.MIN_VALUE)

        val nameTextField = findViewById<EditText>(R.id.nameTextField)
        val emailTextField = findViewById<EditText>(R.id.emailTextField)
        val reviewTitleTextField = findViewById<EditText>(R.id.reviewTitleTextField)
        val reviewDescriptionTextFiled = findViewById<EditText>(R.id.reviewDescriptionTitleField)
        var ratingsBar = findViewById<RatingBar>(R.id.ratingBar_view)


        nameTextField.setText(review_name)
        emailTextField.setText(email)
        reviewTitleTextField.setText(title)
        reviewDescriptionTextFiled.setText(description)
        ratingsBar.setRating(rating)



        var saveChangesButton = findViewById<Button>(R.id.saveChangesButton)
        var deleteReviewButton = findViewById<Button>(R.id.deleteReviewButton)



        saveChangesButton.setOnClickListener {

            val name = nameTextField.text.toString()
            val email = emailTextField.text.toString()
            val reviewTitle = reviewTitleTextField.text.toString()
            val reviewDescription = reviewDescriptionTextFiled.text.toString()
            val rating = ratingsBar.rating

            val review = ReviewData(name,email,reviewTitle,reviewDescription,rating)

            try {
                database
                    .child("reviews").child(name)
                    .setValue(review)
                    .addOnSuccessListener {
                        Log.d("Add Review Activity", "DB Works")
                        Toast.makeText(this@EditReviewActivity,"Updated Review", Toast.LENGTH_SHORT ).show()
                        val intent = Intent(this, ReviewProfile::class.java)
                        intent.putExtra("added_review", name)
                        startActivity(intent)
                    }.addOnFailureListener {
                        Log.d("Add Review Activity", "DB fail")
                        Toast.makeText(this@EditReviewActivity,"Couldn't add review", Toast.LENGTH_SHORT ).show()
                    }.addOnCompleteListener {
                        Log.d("Add Review Activity", "DB Complete")
                    }
            }catch (exception: Exception){
                Log.d("Add Review Activity",exception.toString())

            }
        }


        deleteReviewButton.setOnClickListener {
            try {
                if (review_name != null) {

                    ReviewDataAccess().deleteReview(
                        review_name,
                        database,
                        onSuccess = {
                            Log.d("Add Review Activity", "Removed")
                            Toast.makeText(this@EditReviewActivity,"Deleted Review", Toast.LENGTH_SHORT ).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        },
                        onFailure = {
                            Log.d("Add Review Activity", "DB fail")
                            Toast.makeText(this@EditReviewActivity,"Couldn't add review", Toast.LENGTH_SHORT ).show()
                        }
                    )

//                    database
//                        .child("reviews").child(review_name)
//                        .removeValue()
//                        .addOnSuccessListener {
//                            Log.d("Add Review Activity", "Removed")
//                            Toast.makeText(this@EditReviewActivity,"Deleted Review", Toast.LENGTH_SHORT ).show()
//                            val intent = Intent(this, MainActivity::class.java)
//
//                            startActivity(intent)
//                        }.addOnFailureListener {
//                            Log.d("Add Review Activity", "DB fail")
//                            Toast.makeText(this@EditReviewActivity,"Couldn't add review", Toast.LENGTH_SHORT ).show()
//                        }.addOnCompleteListener {
//                            Log.d("Add Review Activity", "DB Complete")
//                        }

                    }
            }catch (exception: Exception){
                Log.d("Add Review Activity",exception.toString())

            }
        }


    }
}