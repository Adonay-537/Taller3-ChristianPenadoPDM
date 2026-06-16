package com.example.com.pdm0126.parcial2room.model


import com.example.com.pdm0126.parcial2room.data.database.entities.QuestionEntity

data class Question(
    val id: Int = 0,
    val title: String,
    val placeCount: Int = 0
)

fun Question.toEntity(): QuestionEntity {
    return QuestionEntity(
        id = id,
        title = title
    )
}