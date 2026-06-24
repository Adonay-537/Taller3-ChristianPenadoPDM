package com.example.com.pdm0126.parcial2room.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.com.pdm0126.parcial2room.screens.HomeScreen
import com.example.com.pdm0126.parcial2room.screens.admin.PlaceScreen
import com.example.com.pdm0126.parcial2room.screens.admin.QuestionScreen
import com.example.com.pdm0126.parcial2room.screens.results.ResultsScreen
import com.example.com.pdm0126.parcial2room.screens.voting.VotingScreen

@Composable
fun RankeUCA_App() {
    val backStack = rememberNavBackStack(Routes.Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Home> {
                HomeScreen(
                    navigateToAdmin = { backStack.add(Routes.AdminQuestions) }
                )
            }
            entry<Routes.Results> {
                ResultsScreen(
                    navigateBack = { backStack.removeLastOrNull() }
                )
            }

            entry<Routes.AdminQuestions> {
                QuestionScreen(
                    onQuestionClick = { questionId ->
                        backStack.add(Routes.AdminPlaces(questionId))
                    }
                )
            }

            entry<Routes.AdminPlaces> { adminPlaces ->
                PlaceScreen(questionId = adminPlaces.questionId)
            }
        },
    )
}