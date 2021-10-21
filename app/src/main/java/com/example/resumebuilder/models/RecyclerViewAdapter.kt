package com.example.resumebuilder.models

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.resumebuilder.R
import com.example.resumebuilder.models.helper.ExperienceEducationDTO
import android.provider.Settings.System.DATE_FORMAT
import java.text.DateFormat
import java.text.SimpleDateFormat


class RecyclerViewAdapter(var experienceEducationDTOs: List<ExperienceEducationDTO>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.work_experience_or_education_per_rv,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtTitle.text = experienceEducationDTOs[position].title
        holder.txtComUnvName.text = experienceEducationDTOs[position].expUniName
        holder.txtComUnvLocation.text = experienceEducationDTOs[position].location
        holder.txtFrom.text = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(experienceEducationDTOs[position].from)
        if(experienceEducationDTOs[position].isCurrent)
            holder.txtTo.text = "STILL"
        else
            holder.txtTo.text = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(experienceEducationDTOs[position].to)
        if(experienceEducationDTOs[position].isExperience)
            holder.txtDuties.text = experienceEducationDTOs[position].duteis
        else
            holder.txtDuties.isVisible = false
    }

    override fun getItemCount() = experienceEducationDTOs.size

    class MyViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        var txtComUnvName: TextView = itemView.findViewById(R.id.txtComUnvName)
        var txtComUnvLocation: TextView = itemView.findViewById(R.id.txtComUnvLocation)
        var txtFrom: TextView = itemView.findViewById(R.id.txtFrom)
        var txtTo: TextView = itemView.findViewById(R.id.txtTo)
        var txtDuties: TextView = itemView.findViewById(R.id.txtDuties)


        fun bind(prodcut: ExperienceEducationDTO){
            itemView.setOnClickListener {
//                var intent = Intent(itemView.context, ItemDetails::class.java)
//                intent.putExtra("product",prodcut)
//                itemView.context.startActivity(intent)
            }
        }
    }


}