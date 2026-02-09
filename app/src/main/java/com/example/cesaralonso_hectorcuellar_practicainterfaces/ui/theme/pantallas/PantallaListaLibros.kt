package com.example.cesaralonso_hectorcuellar_practicainterfaces.ui.theme.pantallas

import FondoImagen
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cesaralonso_hectorcuellar_practicainterfaces.model.Libro

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
    val leidos = remember(libros) { libros.count { it.leido } }
    val total = libros.size

    val librosFiltrados = remember(libros, filtro) {
        when (filtro) {
            FiltroLibros.TODOS -> libros
            FiltroLibros.LEIDOS -> libros.filter { it.leido }
            FiltroLibros.POR_LEER -> libros.filter { !it.leido }
        }
    }

    FondoImagen {
        Scaffold(
            containerColor = androidx.compose.ui.graphics.Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.MenuBook,
                                    contentDescription = null
                                )
                                Spacer(Modifier.width(8.dp))
                                Text("MyLibrary")
                            }
                            Text(
                                text = "Tu lista de lectura",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
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
                    .padding(padding)
                    .padding(horizontal = 16.dp)
            ) {

                Spacer(Modifier.height(8.dp))

                HeaderResumen(
                    pendientes = pendientes,
                    leidos = leidos,
                    total = total
                )

                Spacer(Modifier.height(14.dp))

                // “Segmented buttons” para filtro (más pro que chips sueltos)
                SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                    SegmentedButton(
                        selected = filtro == FiltroLibros.TODOS,
                        onClick = { onCambiarFiltro(FiltroLibros.TODOS) },
                        shape = SegmentedButtonDefaults.itemShape(index = 0, count = 3)
                    ) { Text("Todos", textAlign = TextAlign.Center) }

                    SegmentedButton(
                        selected = filtro == FiltroLibros.POR_LEER,
                        onClick = { onCambiarFiltro(FiltroLibros.POR_LEER) },
                        shape = SegmentedButtonDefaults.itemShape(index = 1, count = 3)
                    ) { Text("Por leer", textAlign = TextAlign.Center) }

                    SegmentedButton(
                        selected = filtro == FiltroLibros.LEIDOS,
                        onClick = { onCambiarFiltro(FiltroLibros.LEIDOS) },
                        shape = SegmentedButtonDefaults.itemShape(index = 2, count = 3)
                    ) { Text("Leídos", textAlign = TextAlign.Center) }
                }

                Spacer(Modifier.height(10.dp))

                if (librosFiltrados.isEmpty()) {
                    val msg = when (filtro) {
                        FiltroLibros.TODOS -> "Aún no has añadido libros."
                        FiltroLibros.POR_LEER -> "No tienes libros pendientes."
                        FiltroLibros.LEIDOS -> "Aún no has marcado libros como leídos."
                    }
                    EmptyState(
                        title = "Nada por aquí",
                        subtitle = msg
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 88.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(librosFiltrados, key = { it.titulo }) { libro ->
                            ElevatedCard(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.elevatedCardColors(
                                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.92f)
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = libro.titulo,
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                        Spacer(Modifier.height(6.dp))
                                        StatusChip(leido = libro.leido)
                                    }

                                    Spacer(Modifier.width(8.dp))

                                    Checkbox(
                                        checked = libro.leido,
                                        onCheckedChange = { onToggleLeido(libro) }
                                    )

                                    IconButton(onClick = { onSolicitarEliminar(libro) }) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
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
    }
}