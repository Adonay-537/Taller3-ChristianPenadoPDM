package com.example.com.pdm0126.parcial2room.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.com.pdm0126.parcial2room.data.database.entities.QuestionEntity
import com.example.com.pdm0126.parcial2room.data.database.entities.QuestionWithPlaces
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Transaction
    @Query("SELECT * FROM questions")
    fun getQuestionsWithPlaces(): Flow<List<QuestionWithPlaces>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: QuestionEntity)

    @Delete
    suspend fun deleteQuestion(question: QuestionEntity)

    @Update
    suspend fun updateQuestion(question: QuestionEntity)
}