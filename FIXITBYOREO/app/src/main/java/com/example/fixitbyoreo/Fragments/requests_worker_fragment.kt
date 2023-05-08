package com.example.fixitbyoreo.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fixitbyoreo.Adapters.request_worker_data
import com.example.fixitbyoreo.R
import com.example.fixitbyoreo.request_worker
import com.google.firebase.database.*

//, PopupMenu.OnMenuItemClickListener
class requests_worker_fragment : Fragment() {

    //getting object for database reference
    private lateinit var dbref: DatabaseReference
    private lateinit var request_worker_recyclerView: RecyclerView
    lateinit var request_worker_array_list: ArrayList<request_worker>

//    private var id: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val requestListView = inflater.inflate(R.layout.request_worker_list_item, container, true)
//
        val request_worker_delete_btn =
            requestListView.findViewById<ImageView>(R.id.request_worker_decline_btn)
//
//        request_worker_option_btn.setOnClickListener {
//            val popupMenu = PopupMenu(requireContext(), request_worker_option_btn)
//            popupMenu.inflate(R.menu.request_worker_menu)
//            popupMenu.setOnMenuItemClickListener(this)
//            popupMenu.show()
//        }
        val rootView = inflater.inflate(R.layout.fragment_requests_worker, container, false)

        //Retrieve data for Job Requests
        request_worker_recyclerView = rootView.findViewById(R.id.request_worker_list)
        request_worker_recyclerView.layoutManager = LinearLayoutManager(requireContext())
        request_worker_recyclerView.setHasFixedSize(true)

        request_worker_array_list = arrayListOf<request_worker>()
        getRequestWorkerData()

        //delete function - Stopped for testing
//        request_worker_recyclerView.adapter = request_worker_data(request_worker_array_list) { request ->
//        request_worker_delete_btn.setOnClickListener {
//            val builder = AlertDialog.Builder(requireContext())
//            builder.setTitle("Decline Request")
//            builder.setMessage("Are you sure you want to decline the request")
//            builder.setCancelable(false)
//
//            builder.setPositiveButton("Yes") { _, _ ->
//                val deleteRequest = FirebaseDatabase.getInstance().getReference("job_request")
//                    .child(id.toString()).removeValue()
//                Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_LONG).show()
//            }
//            builder.setNegativeButton("No") { _, _ ->
//
//            }
//            val alertDialog = builder.create()
//            alertDialog.show()
//        }

    return rootView
}

//this will get the data from database and put in an array to send to the binder
fun getRequestWorkerData() {
    dbref =
        FirebaseDatabase.getInstance("https://fixit-by-oreo-default-rtdb.asia-southeast1.firebasedatabase.app")
            .getReference("job_request")

    dbref.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            request_worker_array_list.clear()
            if (snapshot.exists()) {
                for (request_worker_snapshot in snapshot.children) {

                    val request = request_worker_snapshot.getValue(request_worker::class.java)
                    request_worker_array_list.add(request!!)
                }
                request_worker_recyclerView.adapter = request_worker_data(request_worker_array_list)
            }
        }

        override fun onCancelled(error: DatabaseError) {
            Log.d("FirebaseError", error.message) // Log the error message
        }
    })
}

//    override fun onMenuItemClick(p0: MenuItem?): Boolean {
//        TODO("Not yet implemented")
//    }
}