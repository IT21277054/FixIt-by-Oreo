package com.example.fixit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.example.fixit.databinding.ActivityWorkerBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Worker : AppCompatActivity() {

    private lateinit var binding : ActivityWorkerBinding
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        checkUser()

        var btnLogout = findViewById<ImageButton>(R.id.workBtnLogout)
        btnLogout.setOnClickListener{
            firebaseAuth.signOut()
            startActivity(Intent(this,LogInSignUp::class.java))
            finish()
        }

        var btnEdit = findViewById<ImageButton>(R.id.workBtnEdit)
        btnEdit.setOnClickListener{
            startActivity(Intent(this,WorkerEdit::class.java))
            finish()
        }

        var btnDelete = findViewById<ImageButton>(R.id.workBtnDelete)
        btnDelete.setOnClickListener{
            TODO("Profile Delete")
        }

    }

    private fun checkUser() {

        val firebaseUser = firebaseAuth.currentUser!!

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(firebaseUser.uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val name = snapshot.child("name").value
                    val email = snapshot.child("email").value
                    val age = snapshot.child("age").value
                    val number = snapshot.child("number").value

                    binding.workerName.text = name as CharSequence?
                    binding.workerEmail.text = email as CharSequence?
                    binding.workerAge.text = age as CharSequence?
                    binding.workerNumber.text = number as CharSequence?

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }


//                if (firebaseUser == null) {
//            Toast.makeText(this, "Not logged in", Toast.LENGTH_SHORT).show()
//        }
//        else {
//            val name = firebaseUser.name
//            val email = firebaseUser.email
//            val age = firebaseUser.age
//            val number = firebaseUser.number
//
//            binding.workerEmail.text = email
        })
    }







}