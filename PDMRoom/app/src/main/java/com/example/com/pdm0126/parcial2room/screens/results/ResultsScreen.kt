package com.example.com.pdm0126.parcial2room.screens.results

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.com.pdm0126.parcial2room.components.ErrorView
import com.example.com.pdm0126.parcial2room.components.LoadingView
import com.example.com.pdm0126.parcial2room.components.ResultItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultsScreen(
    navigateBack: () -> Unit,
    viewModel: ResultsViewModel = viewModel(),
) {
    val options by viewModel.options.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()
    val refreshing by viewModel.refreshing.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadResults()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Resultados") },
            )
        },
        bottomBar = {

            Button(
                onClick = navigateBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text("Nuevo (volver a votar)")
            }
        },
    ) { innerPadding ->

        PullToRefreshBox(
            isRefreshing = refreshing,
            onRefresh = { viewModel.refresh() },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            when {
                loading -> LoadingView()

                error != null -> ErrorView(
                    message = error!!,
                    onRetry = { viewModel.loadResults() },
                )

                else -> LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(options) { option ->
                        ResultItem(option = option)
                    }
                }
            }
        }
    }
}