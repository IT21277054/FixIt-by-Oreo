package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class Complete : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var jobArrayList: ArrayList<PendingJobs>
    private lateinit var onGoingBackBtn:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete)

        userRecyclerView = findViewById(R.id.pendingList)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        onGoingBackBtn = findViewById(R.id.onGoingBackBtn)
        onGoingBackBtn.setOnClickListener(){
            // Create an Intent to start the new activity
            val intent = Intent(this, DashBoard::class.java)

            // Start the new activity
            startActivity(intent)
        }

        jobArrayList = arrayListOf<PendingJobs>()
        getUserData()

    }


    private fun getUserData() {
        database = FirebaseDatabase.getInstance().getReference("Jobs")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                jobArrayList.clear()
                if(snapshot.exists()){
                    for(jobSnapshot in snapshot.children){
                        val client = jobSnapshot.getValue(PendingJobs::class.java)
                        if(client?.status == "complete" || client?.status == "cancelled") {
                            jobArrayList.add(client)
                        }
                    }
                    userRecyclerView.adapter = CompleteJobsAdapter(jobArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })



    }


}