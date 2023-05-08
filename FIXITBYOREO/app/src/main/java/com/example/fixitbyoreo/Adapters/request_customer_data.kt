package com.example.fixitbyoreo.Adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fixitbyoreo.R
import com.example.fixitbyoreo.request_worker

class request_customer_data(private val request_customer_list:ArrayList<request_worker>) :
    RecyclerView.Adapter<request_customer_data.request_customer_view_holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): request_customer_view_holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.request_customer_list_item,parent,false)
        return request_customer_view_holder(itemView)
    }

    override fun onBindViewHolder(holder: request_customer_view_holder, position: Int) {
        val currentItem = request_customer_list[position]
        holder.cusName.text = currentItem.cusName
        holder.jobDesc.text = currentItem.jobDesc
    }

    override fun getItemCount(): Int {
        return request_customer_list.size
    }

    class request_customer_view_holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cusName : TextView = itemView.findViewById(R.id.tv_request_worker_name)
        val jobDesc : TextView = itemView.findViewById(R.id.tv_request_customer_description)
        }
}
