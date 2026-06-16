package com.example.com.pdm0126.parcial2room.data.api

import kotlinx.serialization.Serializable

@Serializable
data class VoteRequestDto(
    val optionId: Int,
)


@Serializable
data class VoteResponseDto(
    val ok: Boolean,
    val message: String? = null,
)