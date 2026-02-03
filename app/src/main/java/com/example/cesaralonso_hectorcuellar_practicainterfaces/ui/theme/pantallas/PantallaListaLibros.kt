package com.example.cesaralonso_hectorcuellar_practicainterfaces.ui.theme.pantallas

import androidx.compose.foundation.background
import com.example.cesaralonso_hectorcuellar_practicainterfaces.model.Libro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


enum class FiltroLibros { TODOS, LEIDOS, POR_LEER }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaListaLibros(
    libros: List<Libro>,
    filtro: FiltroLibros,
    onCambiarFiltro: (FiltroLibros) -> Unit,
    onToggleLeido: (Libro) -> Unit,
    onSolicitarEliminar: (Libro) -> Unit,
    pendientes: Int,
    onIrAAnadir: () -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            TopAppBar(
                title = { Text("MyLibrary") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onIrAAnadir) {
                Icon(Icons.Default.Add, contentDescription = "Añadir libro")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(padding)
                .padding(16.dp)
        ) {
            // Contador visible de pendientes (por leer)
            Text(
                text = "Pendientes por leer: $pendientes",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(12.dp))

            // Filtro
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Filtro: ")
                Spacer(Modifier.width(8.dp))
                FilterChip(
                    selected = filtro == FiltroLibros.TODOS,
                    onClick = { onCambiarFiltro(FiltroLibros.TODOS) },
                    label = { Text("Todos") }
                )
                Spacer(Modifier.width(8.dp))
                FilterChip(
                    selected = filtro == FiltroLibros.POR_LEER,
                    onClick = { onCambiarFiltro(FiltroLibros.POR_LEER) },
                    label = { Text("Por leer") }
                )
                Spacer(Modifier.width(8.dp))
                FilterChip(
                    selected = filtro == FiltroLibros.LEIDOS,
                    onClick = { onCambiarFiltro(FiltroLibros.LEIDOS) },
                    label = { Text("Leídos") }
                )
            }

            Spacer(Modifier.height(12.dp))

            val librosFiltrados = remember(libros, filtro) {
                when (filtro) {
                    FiltroLibros.TODOS -> libros
                    FiltroLibros.LEIDOS -> libros.filter { it.leido }
                    FiltroLibros.POR_LEER -> libros.filter { !it.leido }
                }
            }

            // Lista con LazyColumn
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(librosFiltrados, key = { it.titulo }) { libro ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(libro.titulo, style = MaterialTheme.typography.titleMedium)
                                Text(
                                    if (libro.leido) "Leído" else "Por leer",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            Checkbox(
                                checked = libro.leido,
                                onCheckedChange = { onToggleLeido(libro) }
                            )

                            IconButton(onClick = { onSolicitarEliminar(libro) }) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Eliminar libro"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}