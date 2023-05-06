package com.example.fixit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.fixit.databinding.ActivityWorkerBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class Customer : AppCompatActivity() {

    private lateinit var binding : ActivityWorkerBinding
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        var btnLogout = findViewById<ImageButton>(R.id.cusBtnLogout)
        btnLogout.setOnClickListener{
            firebaseAuth.signOut()
            startActivity(Intent(this,LogInSignUp::class.java))
            finish()
        }


        }


}