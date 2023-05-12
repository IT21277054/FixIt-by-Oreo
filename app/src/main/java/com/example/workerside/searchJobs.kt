package com.example.workerside

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workerside.adapters.jobSearchAdapter
import com.example.workerside.models.jobModel
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class searchJobs : AppCompatActivity() {

    //getting recycler variable
    private lateinit var searchJobRecyclerView: RecyclerView

    private lateinit var jobList: ArrayList<jobModel>

    private lateinit var dbRef : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_jobs)

        //back button
        val backBtn = findViewById<ImageView>(R.id.onGoingBackBtn)

        backBtn.setOnClickListener {
            val backBtnIntent = Intent(this, workerMainMenu::class.java)
            startActivity(backBtnIntent)
        }

        searchJobRecyclerView = findViewById(R.id.jobDisplayRecycler)
        searchJobRecyclerView.layoutManager = LinearLayoutManager(this)
        searchJobRecyclerView.setHasFixedSize(true)

        jobList = arrayListOf<jobModel>()
        getJobData()

    }

    private fun getJobData(){
        //database reference
        dbRef = FirebaseDatabase.getInstance().getReference("Jobs")

        val query = dbRef.orderByChild("status").equalTo("pending")

        query.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                jobList.clear()
                if(snapshot.exists()){
                    for(jobSnap in snapshot.children){
                        val jobData = jobSnap.getValue(jobModel::class.java)
                        jobList.add(jobData!!)
//                        if(jobData?.title == "Electrician"){
//                            jobList.add(jobData!!)
//                        }
                    }
                    val mAdapter = jobSearchAdapter(jobList)
                    searchJobRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListner(object : jobSearchAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            //getting values
                            val jobId = jobList[position].jobid.toString()
                            val cusName = jobList[position].cusName.toString()
                            val location = jobList[position].location.toString()
                            val jobDesc = jobList[position].jobDesc.toString()
                            val latitude = jobList[position].latitude
                            val longitude = jobList[position].longitude
                            val status = jobList[position].status.toString()
                            val title = jobList[position].title.toString()
                            val workerId = jobList[position].workerId.toString()
                            val acceptedDate = jobList[position].acceptedDate.toString()

                            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")


                            val dbrefAccept = FirebaseDatabase.getInstance().getReference("Jobs").child(jobId)
                            val jobInfo = jobModel(jobId,cusName,location,jobDesc, latitude,longitude,"ongoing",title,"12345",LocalDate.now().format(formatter))
                            dbrefAccept.setValue(jobInfo)
                        }

                    })

                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("FirebaseError", error.message) // Log the error message
            }

        })


    }

}