package com.example.practicajetpackcompose.data

/* Creamos el modelo de datos principal de la app
*  id como identificador unico,
*  titulo es el texto que introduce el usuario,
*  y completado para poder filtrar  entre pendientes y completados */

data class Elemento(
    val id: Long,
    val titulo: String,
    val completado: Boolean = false
)