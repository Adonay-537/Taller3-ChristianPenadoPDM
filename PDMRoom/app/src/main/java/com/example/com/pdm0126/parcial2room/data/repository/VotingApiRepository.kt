package com.example.com.pdm0126.parcial2room.data.repository

import com.example.com.pdm0126.parcial2room.data.api.KtorClient
import com.example.com.pdm0126.parcial2room.data.api.OptionDto
import com.example.com.pdm0126.parcial2room.data.api.VoteRequestDto
import com.example.com.pdm0126.parcial2room.data.api.VoteResponseDto
import com.example.com.pdm0126.parcial2room.data.api.toModel
import com.example.com.pdm0126.parcial2room.model.Place
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class VotingApiRepository : VotingRepository {

    override suspend fun getOptions(): Result<List<Place>> {
        return try {
            val response: List<OptionDto> = KtorClient.client.get("options").body()
            Result.success(response.map { it.toModel() })
        } catch (e: Exception) {

            Result.failure(e)
        }
    }

    override suspend fun vote(optionId: Int): Result<Unit> {
        return try {
            val response: VoteResponseDto = KtorClient.client.post("vote") {
                contentType(ContentType.Application.Json)
                setBody(VoteRequestDto(optionId))
            }.body()

            if (response.ok) {
                Result.success(Unit)
            } else {

                Result.failure(Exception(response.message ?: "No se pudo registrar el voto"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}