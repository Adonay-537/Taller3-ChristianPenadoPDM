package com.example.com.pdm0126.parcial2room.model

data class Place(
    val id: Int = 0,
    val value: String,  //El valor en el que se vota
    val imageUrl: String? = null, //Haciendo la imagen opcional
    val votes: Int,
    val questionId: Int = 0
)