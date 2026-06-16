package com.example.com.pdm0126.parcial2room.data.database.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.com.pdm0126.parcial2room.model.Question

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String
)

fun QuestionEntity.toModel(): Question {
    return Question(
        id = id,
        title = title,
        placeCount = 0
    )
}