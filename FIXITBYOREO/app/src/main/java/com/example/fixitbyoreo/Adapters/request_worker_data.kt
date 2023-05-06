package com.example.fixitbyoreo.Adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fixitbyoreo.Fragments.requests_worker_fragment
import com.example.fixitbyoreo.R
import com.example.fixitbyoreo.request_worker

class request_worker_data(private val request_worker_list:ArrayList<request_worker>) :
    RecyclerView.Adapter<request_worker_data.request_worker_view_holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): request_worker_view_holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.request_worker_list_item,parent,false)
        return request_worker_view_holder(itemView)
    }
//here the data is binded to the recycle view items
    override fun onBindViewHolder(holder: request_worker_view_holder, position: Int) {
        val currentItem = request_worker_list[position]
        holder.cusName.text = currentItem.cusName
        holder.jobDesc.text = currentItem.jobDesc
    }

    override fun getItemCount(): Int {
        return request_worker_list.size
    }

    class request_worker_view_holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cusName : TextView = itemView.findViewById(R.id.tv_request_customer_name)
        val jobDesc : TextView = itemView.findViewById(R.id.tv_request_worker_description)
        }
}
