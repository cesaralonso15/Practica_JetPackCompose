package com.example.cesaralonso_hectorcuellar_practicainterfaces.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = Tertiary,

    background = Surface,                 // fondo general
    surface = Surface,                    // superficies
    surfaceVariant = Surface.copy(alpha = 0.85f) // tarjetas/contador
)

@Composable
fun CesarAlonso_HectorCuellar_PracticaInterfacesTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}

