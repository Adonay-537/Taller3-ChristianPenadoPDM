package com.example.com.pdm0126.parcial2room.data.repository

import com.example.com.pdm0126.parcial2room.data.database.dao.PlaceDao
import com.example.com.pdm0126.parcial2room.data.database.entities.toEntity
import com.example.com.pdm0126.parcial2room.data.database.entities.toModel
import com.example.com.pdm0126.parcial2room.model.Place
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlaceRepositoryImpl(
    private val placeDao: PlaceDao
) : PlaceRepository {

    override fun getPlaces(questionId: Int): Flow<List<Place>> {
        return placeDao.getPlacesForQuestion(questionId).map { entities ->
            entities.map { it.toModel() }
        }
    }

    override suspend fun addPlace(name: String, imageUrl: String, questionId: Int) {

        val place = Place(name = name, imageUrl = imageUrl, votes = 0, questionId = questionId)
        placeDao.insertPlace(place.toEntity())
    }

    override suspend fun deletePlace(place: Place) {
        placeDao.deletePlace(place.toEntity())
    }
}