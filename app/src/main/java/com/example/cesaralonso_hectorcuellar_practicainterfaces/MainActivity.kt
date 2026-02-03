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
            CesarAlonso_HectorCuellar_PracticaInterfacesTheme {
                AppMyLibrary()
            }
        }
    }
}

@Composable
fun AppMyLibrary() {
    val navController = rememberNavController()

    // Estado principal (lista + filtro)
    var libros by remember { mutableStateOf(listOf<Libro>()) }
    var filtro by remember { mutableStateOf(FiltroLibros.TODOS) }

    // Estado para diálogo de borrado
    var libroParaEliminar by remember { mutableStateOf<Libro?>(null) }

    val pendientes = remember(libros) { libros.count { !it.leido } }

    // Diálogo de confirmación
    if (libroParaEliminar != null) {
        AlertDialog(
            onDismissRequest = { libroParaEliminar = null },
            title = { Text("Confirmar eliminación") },
            text = { Text("¿Quieres eliminar este libro de tu biblioteca?") },
            confirmButton = {
                TextButton(onClick = {
                    val objetivo = libroParaEliminar
                    if (objetivo != null) {
                        libros = libros.filterNot { it.titulo == objetivo.titulo }
                    }
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

    NavHost(
        navController = navController,
        startDestination = Rutas.LISTA
    ) {
        composable(Rutas.LISTA) {
            PantallaListaLibros(
                libros = libros,
                filtro = filtro,
                onCambiarFiltro = { filtro = it },
                onToggleLeido = { libro ->
                    libros = libros.map {
                        if (it.titulo == libro.titulo) it.copy(leido = !it.leido) else it
                    }
                },
                onSolicitarEliminar = { libroParaEliminar = it },
                pendientes = pendientes,
                onIrAAnadir = { navController.navigate(Rutas.ANADIR) }
            )
        }

        composable(Rutas.ANADIR) {
            PantallaAnadirLibro(
                onGuardar = { titulo ->
                    libros = libros + Libro(titulo = titulo, leido = false)
                    navController.popBackStack()
                },
                onCancelar = { navController.popBackStack() }
            )
        }
    }
}


