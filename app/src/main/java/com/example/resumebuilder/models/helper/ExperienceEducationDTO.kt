package com.example.resumebuilder.models.helper

import com.example.resumebuilder.models.Education
import com.example.resumebuilder.models.Experience
import java.io.Serializable
import java.util.*

data class ExperienceEducationDTO(
    var isExperience: Boolean,
    var id: Int,
    var expUniName: String,
    var title: String,
    var location: String,
    var duteis: String?,
    var from: Date,
    var to: Date?,
    var isCurrent: Boolean) : Serializable {

    companion object {
        fun fromExperience(experience: Experience) : ExperienceEducationDTO{
            return ExperienceEducationDTO(
                true,
                experience.id,
                experience.companyName,
                experience.title,
                experience.location,
                experience.duties,
                experience.from,
                experience.to,
                experience.isCurrentWorking)
        }

        fun fromEducation(education: Education) : ExperienceEducationDTO{
            return ExperienceEducationDTO(
                false,
                education.id,
                education.schoolName,
                education.title,
                education.location,
                null,
                education.from,
                education.to,
                education.isCurrentGraduated)
        }
    }
}