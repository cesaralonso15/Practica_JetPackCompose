package com.example.cesaralonso_hectorcuellar_practicainterfaces

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cesaralonso_hectorcuellar_practicainterfaces.model.Libro
import com.example.cesaralonso_hectorcuellar_practicainterfaces.ui.theme.CesarAlonso_HectorCuellar_PracticaInterfacesTheme
import com.example.cesaralonso_hectorcuellar_practicainterfaces.ui.theme.navegacion.Rutas
import com.example.cesaralonso_hectorcuellar_practicainterfaces.ui.theme.pantallas.FiltroLibros
import com.example.cesaralonso_hectorcuellar_practicainterfaces.ui.theme.pantallas.PantallaAnadirLibro
import com.example.cesaralonso_hectorcuellar_practicainterfaces.ui.theme.pantallas.PantallaListaLibros

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Aplico el tema del proyecto y arranco la app
            CesarAlonso_HectorCuellar_PracticaInterfacesTheme {
                AppMyLibrary()
            }
        }
    }
}

// Composable principal de la app.
// Aquí controlo:
// - estado global (lista + filtro)
// - diálogo de confirmación para borrar
// - navegación entre pantallas
@Composable
fun AppMyLibrary() {
    val navController = rememberNavController()

    // Estado principal: lista de libros + filtro seleccionado
    var libros by remember { mutableStateOf(listOf<Libro>()) }
    var filtro by remember { mutableStateOf(FiltroLibros.TODOS) }

    // Estado para saber si hay un libro seleccionado para borrar (para abrir el diálogo)
    var libroParaEliminar by remember { mutableStateOf<Libro?>(null) }

    // Contador de pendientes (los que no están leídos)
    val pendientes = remember(libros) { libros.count { !it.leido } }

    // Diálogo de confirmación antes de borrar (requisito de la práctica)
    if (libroParaEliminar != null) {
        AlertDialog(
            onDismissRequest = { libroParaEliminar = null },
            title = { Text("Confirmar eliminación") },
            text = { Text("¿Quieres eliminar este libro de tu biblioteca?") },
            confirmButton = {
                TextButton(onClick = {
                    val objetivo = libroParaEliminar
                    if (objetivo != null) {
                        // Borro por título (para esta práctica vale de sobra)
                        libros = libros.filterNot { it.titulo == objetivo.titulo }
                    }
                    // Cierro el diálogo
                    libroParaEliminar = null
                }) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { libroParaEliminar = null }) {
                    Text("Cancelar")
                }
            }
        )
    }

    // Navegación: 2 destinos mínimos (lista y añadir)
    NavHost(
        navController = navController,
        startDestination = Rutas.LISTA
    ) {
        composable(Rutas.LISTA) {
            PantallaListaLibros(
                libros = libros,
                filtro = filtro,
                onCambiarFiltro = { filtro = it },

                // Cambia el estado leído/no leído del libro pulsado
                onToggleLeido = { libro ->
                    libros = libros.map {
                        if (it.titulo == libro.titulo) it.copy(leido = !it.leido) else it
                    }
                },

                // No borro directamente: solo pido borrar y abro el diálogo
                onSolicitarEliminar = { libroParaEliminar = it },

                pendientes = pendientes,

                // Navega a la pantalla de añadir
                onIrAAnadir = { navController.navigate(Rutas.ANADIR) }
            )
        }

        composable(Rutas.ANADIR) {
            PantallaAnadirLibro(
                // Guardar: añade el libro y vuelve atrás
                onGuardar = { titulo ->
                    libros = libros + Libro(titulo = titulo, leido = false)
                    navController.popBackStack()
                },
                // Cancelar: vuelve atrás sin hacer nada
                onCancelar = { navController.popBackStack() }
            )
        }
    }
}