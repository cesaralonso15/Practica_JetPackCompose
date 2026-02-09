# MyLibrary – Biblioteca personal en Jetpack Compose

Aplicación Android desarrollada con Jetpack Compose cuyo objetivo es gestionar una lista personal de libros por leer, permitiendo añadirlos, marcarlos como leídos, filtrarlos y eliminarlos.

Este proyecto forma parte de la práctica de la asignatura **Desarrollo de Interfaces** del ciclo **2º DAM** en el IES Gabriel García Márquez.

---

## Autores

- Héctor Cuéllar  
- César Alonso  

---

## Objetivo de la aplicación

Diseñar e implementar una aplicación Android completa centrada en la experiencia de usuario y el diseño de interfaces utilizando exclusivamente **Jetpack Compose** y siguiendo buenas prácticas de organización, estado y navegación.

La temática elegida ha sido **MyLibrary – Biblioteca personal**, que permite gestionar libros pendientes de lectura.  
---

## Funcionalidades implementadas

La aplicación cumple los requisitos funcionales definidos en la práctica:

- Visualización de una lista de libros  
- Añadir nuevos libros  
- Marcar libros como leídos  
- Eliminar libros  
- Filtro de libros (todos / leídos / por leer)  
- Contador de libros pendientes visible en la interfaz  
- Diálogo de confirmación antes de eliminar un libro  

---

## Pantallas de la aplicación

### 1. Pantalla principal – Lista de libros
Incluye:

- TopAppBar con el título de la aplicación  
- Lista de libros mediante **LazyColumn**  
- Cada elemento muestra:
  - Título del libro  
  - Checkbox de leído  
  - Icono de eliminación  
- FloatingActionButton para añadir nuevos libros  

### 2. Pantalla de añadir libro
Contiene:

- Campo de texto para introducir el título  
- Botón para guardar  
- Botón para cancelar  

Al guardar:

- El libro se añade a la lista  
- Se regresa a la pantalla principal  

---

## Estructura del proyecto

Organización principal del código:

- `model/` → clases de datos (Libro)  
- `ui/theme/` → tema visual, colores y tipografía  
- `ui/theme/pantallas/` → pantallas composables:
  - PantallaListaLibros  
  - PantallaAnadirLibro  
  - Componentes reutilizables  

Esta estructura separa **modelo, interfaz y componentes**, facilitando la mantenibilidad y la claridad del código.

---

## Decisiones de diseño

Para mejorar la experiencia de usuario se han aplicado:

- Diseño basado en **Material Design 3**  
- Uso de tarjetas elevadas para cada libro  
- Indicadores visuales de estado (leído / pendiente)  
- Fondo con imagen oscurecida para mejorar la legibilidad  
- Botones segmentados para el filtrado  
- Pantallas vacías informativas cuando no hay contenido  

Estas decisiones buscan una interfaz clara, moderna y centrada en el usuario, alineada con el objetivo de la práctica.

---

## Criterios de evaluación cubiertos

El proyecto aborda los apartados de evaluación definidos:

- Uso correcto de Jetpack Compose  
- Gestión del estado y recomposición  
- Diseño de la interfaz  
- Navegación entre pantallas  
- Limpieza y organización del código  
- Funcionalidad completa  
