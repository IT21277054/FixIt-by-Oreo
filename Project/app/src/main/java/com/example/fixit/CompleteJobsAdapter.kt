package com.example.fixit

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Address
import android.location.Geocoder
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.collections.ArrayList

class CompleteJobsAdapter(private val userList: ArrayList<PendingJobs>) :
    RecyclerView.Adapter<CompleteJobsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.completerecycle_item,parent,false)
        return ViewHolder(itemView)

    }


    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentitem = userList[position]

        holder.status.text = currentitem.status ?: ""
        holder.title.text = currentitem.title ?: ""
        holder.jobDesc.text = currentitem.jobDesc ?: ""

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val status: TextView = itemView.findViewById(R.id.recStatus)
        val title: TextView = itemView.findViewById(R.id.recTitle)
        val jobDesc: TextView = itemView.findViewById(R.id.recDec)
        val deleteButton: ShapeableImageView = itemView.findViewById(R.id.btn2)


        var recordId: String = ""

        init {
            deleteButton.setOnClickListener {
                showDialog()

            }
        }

        fun bind(job: PendingJobs) {
            status.text = job.status ?: ""
            title.text = job.title ?: ""
            jobDesc.text = job.jobDesc ?: ""
        }

        private fun showDialog() {


            val dialog = Dialog(itemView.context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.bottomconfimationpopup)

            val confirmButton = dialog.findViewById<Button>(R.id.confirm_button)
            val cancelButton = dialog.findViewById<Button>(R.id.decline_button)
            confirmButton.setOnClickListener {

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val job = userList[position]
                    recordId = job.jobid
                    val database = FirebaseDatabase.getInstance().getReference("Jobs")
                    database.child(recordId).removeValue()

                    userList.removeAt(position)
                    notifyItemRemoved(position)
                    Toast.makeText(itemView.context, "Job deleted", Toast.LENGTH_SHORT).show()

                }
                Toast.makeText(itemView.context, "Yes button clicked", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            cancelButton.setOnClickListener{
                dialog.dismiss()
            }

                dialog.show()
                dialog.window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
                dialog.window?.setGravity(Gravity.BOTTOM)
            }

        }

    }







