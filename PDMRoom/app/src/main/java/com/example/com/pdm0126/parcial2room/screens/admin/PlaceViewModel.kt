package com.example.com.pdm0126.parcial2room.screens.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.com.pdm0126.parcial2room.Parcial2Application
import com.example.com.pdm0126.parcial2room.data.repository.PlaceRepository
import com.example.com.pdm0126.parcial2room.model.Place
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlaceViewModel(
    private val placeRepository: PlaceRepository,
    private val questionId: Int
) : ViewModel() {


    val places: StateFlow<List<Place>> =
        placeRepository.getPlaces(questionId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    fun addPlace(value: String, imageUrl: String?) {
        viewModelScope.launch {
            placeRepository.addPlace(value, imageUrl, questionId)
        }
    }

    fun deletePlace(place: Place) {
        viewModelScope.launch {
            placeRepository.deletePlace(place)
        }
    }

    fun updatePlace(place: Place) {
        viewModelScope.launch { placeRepository.updatePlace(place) }
    }

    companion object {

        fun provideFactory(questionId: Int) = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as Parcial2Application
                PlaceViewModel(app.appProvider.providePlaceRepository(), questionId)
            }
        }
    }
}