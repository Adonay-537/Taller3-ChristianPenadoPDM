package com.example.com.pdm0126.parcial2room.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.com.pdm0126.parcial2room.model.Place

@Entity(
    tableName = "places",
    foreignKeys = [
        ForeignKey(
            entity = QuestionEntity::class,
            parentColumns = ["id"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        )
    ],

    indices = [Index("questionId")]
)
data class PlaceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val value: String,
    val imageUrl: String?,
    val votes: Int = 0,
    val questionId: Int
)

fun PlaceEntity.toModel(): Place {
    return Place(
        id = id,
        value = value,
        imageUrl = imageUrl,
        votes = votes,
        questionId = questionId
    )
}

fun Place.toEntity(): PlaceEntity {
    return PlaceEntity(
        id = id,
        value = value,
        imageUrl = imageUrl,
        votes = votes,
        questionId = questionId
    )
}