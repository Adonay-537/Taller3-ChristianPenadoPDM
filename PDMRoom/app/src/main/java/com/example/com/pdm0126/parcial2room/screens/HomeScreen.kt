package com.example.com.pdm0126.parcial2room.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToAdmin: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("RankeUCA") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFBCD599))
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            ElevatedCard(
                modifier = Modifier.fillMaxWidth().clickable { navigateToAdmin() }
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Gestor de preguntas", style = MaterialTheme.typography.titleLarge)
                    Text("Administra votaciones y lugares localmente.", style = MaterialTheme.typography.bodyMedium)
                }
            }

            // Tarjeta 2: Votar (Deshabilitada)
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                onClick = { /* No hace nada aún */ }
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Votar", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f))
                    Text("Próximamente", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f))
                }
            }
        }
    }
}