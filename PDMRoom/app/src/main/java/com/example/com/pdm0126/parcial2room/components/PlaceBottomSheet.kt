package com.example.com.pdm0126.parcial2room.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.com.pdm0126.parcial2room.model.Place

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceBottomSheet(
    placeToEdit: Place? = null, // Recibimos el objeto opcional
    onSave: (value: String, imageUrl: String?) -> Unit, // imageUrl ahora es nullable
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    // Si placeToEdit tiene datos, los cargamos; si no, inicializa vacío
    var value by rememberSaveable { mutableStateOf(placeToEdit?.value ?: "") }
    var imageUrl by rememberSaveable { mutableStateOf(placeToEdit?.imageUrl ?: "") }

    val isValid = value.isNotBlank() // La imagen ya no es obligatoria para ser válido

    ModalBottomSheet(sheetState = sheetState, onDismissRequest = onDismiss) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp).padding(bottom = 32.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            // Cambiamos el título dinámicamente
            Text(if (placeToEdit == null) "Nuevo Lugar" else "Editar Lugar", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            OutlinedTextField(value = value, onValueChange = { value = it }, label = { Text("Nombre/Valor") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            OutlinedTextField(value = imageUrl, onValueChange = { imageUrl = it }, label = { Text("URL de la imagen (Opcional)") }, modifier = Modifier.fillMaxWidth(), singleLine = true)

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                TextButton(onClick = onDismiss) { Text("Cancelar") }
                Button(
                    onClick = {
                        if (isValid) {
                            // Pasamos null si la imagen está vacía
                            onSave(value.trim(), imageUrl.trim().takeIf { it.isNotBlank() })
                            onDismiss()
                        }
                    },
                    enabled = isValid
                ) { Text("Guardar") }
            }
        }
    }
}