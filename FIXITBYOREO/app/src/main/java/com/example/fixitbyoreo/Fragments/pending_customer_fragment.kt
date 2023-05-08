package com.example.fixitbyoreo.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fixitbyoreo.Adapters.request_customer_data
import com.example.fixitbyoreo.Adapters.request_worker_data
import com.example.fixitbyoreo.R
import com.example.fixitbyoreo.request_worker
import com.google.firebase.database.*

class pending_customer_fragment : Fragment() {

    private lateinit var dbref: DatabaseReference
    private lateinit var request_customer_recyclerView: RecyclerView
    private lateinit var request_customer_array_list: ArrayList<request_worker>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val rootView =
            inflater.inflate(R.layout.fragment_pending_customer_fragment, container, false)

        //Retrieve data for Job Requests
        request_customer_recyclerView = rootView.findViewById(R.id.request_customer_list)
        request_customer_recyclerView.layoutManager = LinearLayoutManager(requireContext())
        request_customer_recyclerView.setHasFixedSize(true)

        request_customer_array_list = arrayListOf<request_worker>()
        getRequestWorkerData()


        return rootView
    }

    private fun getRequestWorkerData() {
        dbref =
            FirebaseDatabase.getInstance("https://fixit-by-oreo-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("job_request")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                request_customer_array_list.clear()
                if (snapshot.exists()) {
                    for (request_worker_snapshot in snapshot.children) {

                        val request = request_worker_snapshot.getValue(request_worker::class.java)
                        request_customer_array_list.add(request!!)
                    }
                    request_customer_recyclerView.adapter =
                        request_customer_data(request_customer_array_list)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("FirebaseError", error.message) // Log the error message
            }
        })
    }
}

