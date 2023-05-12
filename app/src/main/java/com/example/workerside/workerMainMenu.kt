package com.example.workerside

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView

class workerMainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_main_menu)

        val profileBtn = findViewById<CardView>(R.id.profileCardWorker)
        val mapBtn = findViewById<CardView>(R.id.mapCardWorker)
        val jobBtn = findViewById<CardView>(R.id.jobsCardWorker)
        val onGoingBtn = findViewById<CardView>(R.id.onGoingCardWorker)
        val completedBtn = findViewById<CardView>(R.id.completedCardWorker)
//Main menu to job search page
        jobBtn.setOnClickListener {
            val jobPageIntent = Intent(this,searchJobs::class.java)
            startActivity(jobPageIntent)
        }

//        Main menu to on going job page
        onGoingBtn.setOnClickListener {
            val onGoingPageIntent = Intent(this,onGoingJobs::class.java)
            startActivity(onGoingPageIntent)
        }
        //Main menu to completed jobs
        completedBtn.setOnClickListener {
            val completedPageIntent = Intent(this, completedJobs::class.java)
            startActivity(completedPageIntent)
        }


//      Main Menu to completed job page
    }
}