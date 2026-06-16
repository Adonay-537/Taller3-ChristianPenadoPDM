package com.example.com.pdm0126.parcial2room

import android.content.Context
import com.example.com.pdm0126.parcial2room.data.AppDatabase
import com.example.com.pdm0126.parcial2room.data.repository.PlaceRepository
import com.example.com.pdm0126.parcial2room.data.repository.PlaceRepositoryImpl
import com.example.com.pdm0126.parcial2room.data.repository.QuestionRepository
import com.example.com.pdm0126.parcial2room.data.repository.QuestionRepositoryImpl

class AppProvider(context: Context) {

    private val appDatabase = AppDatabase.getDatabase(context)

    private val questionDao = appDatabase.questionDao()
    private val placeDao = appDatabase.placeDao()

    private val questionRepository: QuestionRepository = QuestionRepositoryImpl(questionDao)
    private val placeRepository: PlaceRepository = PlaceRepositoryImpl(placeDao)

    fun provideQuestionRepository(): QuestionRepository {
        return questionRepository
    }
    fun providePlaceRepository(): PlaceRepository {
        return placeRepository
    }
}