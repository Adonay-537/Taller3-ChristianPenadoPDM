package com.example.com.pdm0126.parcial2room.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {
    @Serializable
    data object Home : Routes()

    @Serializable
    data object Results : Routes()

    @Serializable
    data object AdminQuestions : Routes()

    @Serializable
    data class AdminPlaces(val questionId: Int) : Routes()
}