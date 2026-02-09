package com.example.cesaralonso_hectorcuellar_practicainterfaces.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

// Paleta de colores en modo claro.
// Aquí defino los colores que usa toda la app (Material 3).
private val LightColors = lightColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = Tertiary,

    // Fondo general de la app
    background = Surface,

    // Color de las superficies (cards, barras, etc.)
    surface = Surface,

    // Variante para cosas tipo tarjetas o componentes secundarios
    surfaceVariant = Surface.copy(alpha = 0.85f)
)

// Tema principal del proyecto.
// Se aplica en MainActivity para que toda la app tenga el mismo estilo:
// - colores
// - tipografía
// - formas (bordes redondeados)
@Composable
fun CesarAlonso_HectorCuellar_PracticaInterfacesTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,

        // Shapes: redondeos para que se vea más moderno y profesional
        // (cards, botones, etc.)
        shapes = Shapes(
            extraSmall = RoundedCornerShape(10.dp),
            small = RoundedCornerShape(14.dp),
            medium = RoundedCornerShape(18.dp),
            large = RoundedCornerShape(22.dp),
            extraLarge = RoundedCornerShape(28.dp)
        ),

        // Todo lo que envuelva este theme hereda el estilo
        content = content
    )
}