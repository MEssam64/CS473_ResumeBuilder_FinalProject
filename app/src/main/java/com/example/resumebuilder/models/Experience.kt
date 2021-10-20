package com.example.resumebuilder.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.resumebuilder.models.helper.DateConverter
import com.example.resumebuilder.models.helper.TimestampConverter
import java.io.Serializable
import java.util.*

@Entity
@TypeConverters(DateConverter::class)
data class Experience(
    var companyName: String,
    var companayURL: String,
    var location: String,
    var title: String,
    var from: Date,
    var to:Date? = null,
    var isCurrentWorking: Boolean,
    var userId: Int) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
