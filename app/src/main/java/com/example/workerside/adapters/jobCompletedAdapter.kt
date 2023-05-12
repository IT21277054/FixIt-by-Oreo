package com.example.workerside.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workerside.R
import com.example.workerside.models.jobModelWorkerCompleted
import com.example.workerside.models.paymentModel
import com.google.firebase.database.*

class jobCompletedAdapter (private val completedJobList: ArrayList<jobModelWorkerCompleted>) : RecyclerView.Adapter<jobCompletedAdapter.ViewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewJobSearch = LayoutInflater.from(parent.context).inflate(R.layout.completedjoblistitem,parent,false)
        return ViewHolder(itemViewJobSearch,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentJob = completedJobList[position]
        holder.tvCompletedJobsItemJobType.text = currentJob.title
        holder.tvCompletedJobsItemCustomerName.text = currentJob.cusName
        holder.tvCompletedJobsItemJobLocation.text = currentJob.location

    }

    override fun getItemCount(): Int {
        return completedJobList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvCompletedJobsItemJobType : TextView = itemView.findViewById(R.id.tvCompletedJobsItemJobType)
        val tvCompletedJobsItemCustomerName : TextView = itemView.findViewById(R.id.tvCompletedJobsItemCustomerName)
        val tvCompletedJobsItemJobLocation : TextView = itemView.findViewById(R.id.tvCompletedJobsItemJobLocation)

        val deleteBtn : Button = itemView.findViewById(R.id.completedJobsDeleteBtn)

        init{
            deleteBtn.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}