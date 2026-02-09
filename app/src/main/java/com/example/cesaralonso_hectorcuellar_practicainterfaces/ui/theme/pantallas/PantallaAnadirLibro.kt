package com.example.cesaralonso_hectorcuellar_practicainterfaces.ui.theme.pantallas

import FondoImagen
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Pantalla para añadir un libro nuevo.
// Tiene un TextField para el título y botones Guardar / Cancelar.
// No toca la lógica global, solo devuelve el título al onGuardar.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAnadirLibro(
    onGuardar: (String) -> Unit,
    onCancelar: () -> Unit
) {
    // Estado del texto (lo que va escribiendo el usuario)
    var titulo by remember { mutableStateOf("") }

    // Fondo con imagen para que quede más pro
    FondoImagen {
        Scaffold(
            containerColor = androidx.compose.ui.graphics.Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Añadir libro") }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                // Card donde meto el formulario para que quede más bonito
                ElevatedCard(
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.92f)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // Icono decorativo
                            Icon(
                                imageVector = Icons.Default.MenuBook,
                                contentDescription = null
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "Nuevo título",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }

                        // Campo de texto para el título
                        OutlinedTextField(
                            value = titulo,
                            onValueChange = { titulo = it },
                            label = { Text("Título del libro") },
                            placeholder = { Text("Ej: El principito") },
                            supportingText = { Text("Consejo: evita títulos vacíos.") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                // Botón Guardar: recorta espacios y si no está vacío, lo manda al onGuardar
                Button(
                    onClick = {
                        val limpio = titulo.trim()
                        if (limpio.isNotEmpty()) onGuardar(limpio)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Guardar")
                }

                // Botón Cancelar: vuelve atrás
                OutlinedButton(
                    onClick = onCancelar,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancelar")
                }
            }
        }
    }
}