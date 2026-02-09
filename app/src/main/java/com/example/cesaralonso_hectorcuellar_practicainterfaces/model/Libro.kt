package com.example.cesaralonso_hectorcuellar_practicainterfaces.model

// Clase de datos sencilla para representar un libro.
// - titulo: nombre del libro
// - leido: si está leído o no (por defecto false)
data class Libro (
    val titulo: String,
    val leido: Boolean = false
)