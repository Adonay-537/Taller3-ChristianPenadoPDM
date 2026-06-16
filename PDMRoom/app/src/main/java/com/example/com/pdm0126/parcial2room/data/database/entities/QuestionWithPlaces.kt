package com.example.com.pdm0126.parcial2room.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.example.com.pdm0126.parcial2room.model.Question

data class QuestionWithPlaces(
    @Embedded val question: QuestionEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "questionId"
    )
    val places: List<PlaceEntity>
)

fun QuestionWithPlaces.toModel(): Question {
    return Question(
        id = question.id,
        title = question.title,
        placeCount = places.size
    )
}