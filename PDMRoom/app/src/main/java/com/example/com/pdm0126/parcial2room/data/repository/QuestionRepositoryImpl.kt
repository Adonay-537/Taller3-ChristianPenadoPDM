package com.example.com.pdm0126.parcial2room.data.repository

import com.example.com.pdm0126.parcial2room.data.database.dao.QuestionDao
import com.example.com.pdm0126.parcial2room.data.database.entities.QuestionEntity
import com.example.com.pdm0126.parcial2room.data.database.entities.toEntity
import com.example.com.pdm0126.parcial2room.data.database.entities.toModel
import com.example.com.pdm0126.parcial2room.model.Question
import com.example.com.pdm0126.parcial2room.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuestionRepositoryImpl(
    private val questionDao: QuestionDao
) : QuestionRepository {

    override fun getQuestions(): Flow<List<Question>> {
        return questionDao.getQuestionsWithPlaces().map { list ->
            list.map { it.toModel() }
        }
    }

    override suspend fun addQuestion(title: String) {
        questionDao.insertQuestion(QuestionEntity(title = title))
    }

    override suspend fun deleteQuestion(question: Question) {
        questionDao.deleteQuestion(question.toEntity())
    }

    override suspend fun updateQuestion(question: Question) {
        questionDao.updateQuestion(question.toEntity())
    }
}