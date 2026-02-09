package com.example.cesaralonso_hectorcuellar_practicainterfaces.ui.theme.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

// Cabecera/resumen con tarjetas: pendientes, leídos y total.
// Esto lo saco arriba para que se vea claro el estado de la biblioteca.
@Composable
fun HeaderResumen(
    pendientes: Int,
    leidos: Int,
    total: Int,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            // Un pelín transparente para que se integre con el fondo
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.92f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Resumen",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(10.dp))

            // Fila con 3 mini-resúmenes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ResumenItem(label = "Pendientes", value = pendientes.toString())
                ResumenItem(label = "Leídos", value = leidos.toString())
                ResumenItem(label = "Total", value = total.toString())
            }
        }
    }
}

// Item pequeño del resumen: número grande + texto pequeño
@Composable
private fun ResumenItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, style = MaterialTheme.typography.headlineSmall)
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// Chip de estado para cada libro.
// Si está leído -> "Leído" con icono Book
// Si no -> "Por leer" con icono MenuBook
@Composable
fun StatusChip(leido: Boolean, modifier: Modifier = Modifier) {
    val text = if (leido) "Leído" else "Por leer"
    AssistChip(
        onClick = { },       // No hace nada, es solo visual
        enabled = false,     // Lo desactivo para que sea “etiqueta”
        label = { Text(text) },
        leadingIcon = {
            Icon(
                imageVector = if (leido) Icons.Default.Book else Icons.Default.MenuBook,
                contentDescription = null
            )
        },
        modifier = modifier
    )
}

// Estado vacío (cuando no hay libros o el filtro deja la lista vacía).
@Composable
fun EmptyState(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Menu, // icono genérico para el empty
            contentDescription = null,
            modifier = Modifier.size(56.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}