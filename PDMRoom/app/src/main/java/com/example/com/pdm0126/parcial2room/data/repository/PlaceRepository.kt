package com.example.com.pdm0126.parcial2room.data.repository
import com.example.com.pdm0126.parcial2room.model.Place
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {
    fun getPlaces(questionId: Int): Flow<List<Place>>

    suspend fun addPlace(value: String, imageUrl: String?, questionId: Int)
    suspend fun deletePlace(place: Place)

    suspend fun updatePlace(place: Place)
}