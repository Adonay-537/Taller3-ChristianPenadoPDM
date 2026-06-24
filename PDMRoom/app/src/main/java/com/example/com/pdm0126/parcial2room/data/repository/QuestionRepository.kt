package com.example.com.pdm0126.parcial2room.data.repository

import com.example.com.pdm0126.parcial2room.model.Question
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    fun getQuestions(): Flow<List<Question>>
    suspend fun addQuestion(title: String)
    suspend fun deleteQuestion(question: Question)
    suspend fun updateQuestion(question: Question)
}