package com.example.resumebuilder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resumebuilder.models.CVDataBase
import com.example.resumebuilder.models.RecyclerViewAdapter
import com.example.resumebuilder.models.helper.ExperienceEducationDTO
import kotlinx.android.synthetic.main.fragment_work_experience.*
import kotlinx.coroutines.launch

class WorkExperience : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_work_experience, container, false)
        var experienceEducationDTOs: ArrayList<ExperienceEducationDTO> = ArrayList<ExperienceEducationDTO>()
        
        launch {
            context?.let {
                var dao = CVDataBase(it).getDao()
                var userWithAllData = dao.getAllUsers()[0]
                for (experience in userWithAllData.experiences) {
                    //Toast.makeText(view.context, experience.companyName, Toast.LENGTH_LONG).show()
                    experienceEducationDTOs.add(ExperienceEducationDTO.fromExperience(experience))
                }
                for(education in userWithAllData.educations)
                    experienceEducationDTOs.add(ExperienceEducationDTO.fromEducation(education))
                var adapter = RecyclerViewAdapter(experienceEducationDTOs)
                val recyclerView = view.findViewById<RecyclerView>(R.id.recycleView)
                recyclerView.layoutManager = LinearLayoutManager(view.context)
                recyclerView.adapter = adapter
            }
        }
        //Toast.makeText(this.context,experienceEducationDTOs.size.toString(),Toast.LENGTH_LONG).show()


        return view
    }
}