package com.example.com.pdm0126.parcial2room.screens.voting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.com.pdm0126.parcial2room.data.repository.VotingApiRepository
import com.example.com.pdm0126.parcial2room.data.repository.VotingRepository
import com.example.com.pdm0126.parcial2room.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class VotingViewModel : ViewModel() {

    private val repository: VotingRepository = VotingApiRepository()

    private val _options = MutableStateFlow<List<Place>>(emptyList())
    val options = _options.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private val _votedOptionId = MutableStateFlow<Int?>(null)
    val votedOptionId = _votedOptionId.asStateFlow()

    init {
        loadOptions()
    }

    fun loadOptions() {
        viewModelScope.launch {
            _error.value = null
            _loading.value = true
            repository.getOptions()
                .onSuccess { _options.value = it }
                .onFailure { _error.value = "No se pudieron cargar los lugares. Revisa tu conexion e intenta de nuevo." }
            _loading.value = false
        }
    }


    fun vote(optionId: Int) {
        viewModelScope.launch {
            _error.value = null
            _loading.value = true
            repository.vote(optionId)
                .onSuccess { _votedOptionId.value = optionId }
                .onFailure { _error.value = "No se pudo registrar tu voto. Intenta de nuevo." }
            _loading.value = false
        }
    }
}