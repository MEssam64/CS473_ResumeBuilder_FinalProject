package com.example.resumebuilder.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.resumebuilder.models.helper.TimestampConverter

@Database(
    entities = [User::class, Experience::class, Education::class],
    version = 1
)
abstract class CVDataBase() : RoomDatabase() {
    abstract fun getDao(): DAO

//    companion object {
//        @Volatile private var instance: CVDataBase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: buildDataBase(context).also {
//                instance = it
//            }
//        }

//        private fun buildDataBase(context: Context) = Room.databaseBuilder(
//            context.applicationContext,
//            CVDataBase::class.java,
//            "CVDataBase"
//        ).build()
}