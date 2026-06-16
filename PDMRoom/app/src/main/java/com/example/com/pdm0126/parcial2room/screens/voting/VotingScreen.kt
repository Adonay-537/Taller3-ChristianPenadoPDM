package com.example.com.pdm0126.parcial2room.screens.voting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.com.pdm0126.parcial2room.components.ErrorView
import com.example.com.pdm0126.parcial2room.components.LoadingView
import com.example.com.pdm0126.parcial2room.components.OptionItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VotingScreen(
    navigateToResults: () -> Unit,
    navigateToAdmin: () -> Unit,
    viewModel: VotingViewModel = viewModel(),
) {
    val options by viewModel.options.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()
    val votedOptionId by viewModel.votedOptionId.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("RankeUca - Vota") },
                actions = {
                    IconButton(onClick = navigateToAdmin) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Administrar"
                        )
                    }
                }
            )
        },
        bottomBar = {

            Button(
                onClick = navigateToResults,
                enabled = votedOptionId != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text("Ir a resultados ")
            }
        },
    ) { innerPadding ->
        when {
            loading -> LoadingView(modifier = Modifier.padding(innerPadding))

            error != null -> ErrorView(
                message = error!!,
                onRetry = { viewModel.loadOptions() },
                modifier = Modifier.padding(innerPadding),
            )

            else -> LazyColumn(
                modifier = Modifier.padding(innerPadding),
            ) {
                items(options) { option ->
                    OptionItem(
                        option = option,

                        isVoted = option.id == votedOptionId,

                        onClick = { viewModel.vote(option.id) },
                    )
                }
            }
        }
    }
}