package com.example.cesaralonso_hectorcuellar_practicainterfaces.ui.theme.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAnadirLibro(
    onGuardar: (String) -> Unit,
    onCancelar: () -> Unit
) {
    var titulo by remember { mutableStateOf("") }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            TopAppBar(
                title = { Text("Añadir libro") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(padding)
                .padding(16.dp)
        ) {
            TextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título del libro") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Row {
                Button(
                    onClick = {
                        val limpio = titulo.trim()
                        if (limpio.isNotEmpty()) onGuardar(limpio)
                    }
                ) {
                    Text("Guardar")
                }

                Spacer(Modifier.width(12.dp))

                OutlinedButton(onClick = onCancelar) {
                    Text("Cancelar")
                }
            }

            Spacer(Modifier.height(8.dp))
            Text(
                "Consejo: evita títulos vacíos.",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}