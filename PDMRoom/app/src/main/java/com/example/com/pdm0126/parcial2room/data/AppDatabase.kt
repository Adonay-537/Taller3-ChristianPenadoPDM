package com.example.com.pdm0126.parcial2room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.com.pdm0126.parcial2room.data.database.dao.PlaceDao
import com.example.com.pdm0126.parcial2room.data.database.dao.QuestionDao
import com.example.com.pdm0126.parcial2room.data.database.entities.PlaceEntity
import com.example.com.pdm0126.parcial2room.data.database.entities.QuestionEntity

@Database(
    entities = [QuestionEntity::class, PlaceEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionDao
    abstract fun placeDao(): PlaceDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "rankeuca_database"
                )
                    .fallbackToDestructiveMigration(false)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}