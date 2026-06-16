package com.example.com.pdm0126.parcial2room.model

data class Place(
    val id: Int = 0,
    val name: String,
    val imageUrl: String,
    val votes: Int,
    val questionId: Int = 0
)