package com.example.com.pdm0126.parcial2room.data.repository

import com.example.com.pdm0126.parcial2room.model.Place


interface VotingRepository {
    suspend fun getOptions(): Result<List<Place>>
    suspend fun vote(optionId: Int): Result<Unit>
}