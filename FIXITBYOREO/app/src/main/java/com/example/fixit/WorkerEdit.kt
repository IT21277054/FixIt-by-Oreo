package com.example.fixit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class WorkerEdit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_edit)

        var btnEditCancel = findViewById<ImageButton>(R.id.btnCancel)
        btnEditCancel.setOnClickListener{
            startActivity(Intent(this,Worker::class.java))
            finish()
        }

    }
}