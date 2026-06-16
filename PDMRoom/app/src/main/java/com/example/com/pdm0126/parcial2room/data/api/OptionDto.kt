package com.example.com.pdm0126.parcial2room.data.api

import com.example.com.pdm0126.parcial2room.model.Place
import kotlinx.serialization.Serializable


@Serializable
data class OptionDto(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val votes: Int,
)

fun OptionDto.toModel(): Place = Place(
    id = id,
    name = name,
    imageUrl = imageUrl,
    votes = votes,
)