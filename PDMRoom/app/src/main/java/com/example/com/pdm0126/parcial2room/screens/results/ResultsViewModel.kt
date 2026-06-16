package com.example.com.pdm0126.parcial2room.screens.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.com.pdm0126.parcial2room.data.repository.VotingApiRepository
import com.example.com.pdm0126.parcial2room.data.repository.VotingRepository
import com.example.com.pdm0126.parcial2room.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.collections.sortedByDescending

class ResultsViewModel : ViewModel() {

    private val repository: VotingRepository = VotingApiRepository()

    private val _options = MutableStateFlow<List<Place>>(emptyList())
    val options = _options.asStateFlow()


    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()


    private val _refreshing = MutableStateFlow(false)
    val refreshing = _refreshing.asStateFlow()

    fun loadResults() {
        viewModelScope.launch {
            _error.value = null
            _loading.value = true
            fetchOptions()
            _loading.value = false
        }
    }


    fun refresh() {
        viewModelScope.launch {
            _error.value = null
            _refreshing.value = true
            fetchOptions()
            _refreshing.value = false
        }
    }

    private suspend fun fetchOptions() {
        repository.getOptions()
            .onSuccess { places: List<Place> ->
                _options.value = places.sortedByDescending { option -> option.votes }
            }
            .onFailure { _error.value = "No se pudieron cargar los resultados. Intenta de nuevo." }
    }
}