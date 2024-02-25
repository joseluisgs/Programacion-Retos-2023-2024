package org.example.models

/**
 * Clase que representa un plato en el restaurante.
 *
 * @property nombre Nombre del plato.
 * @property primero Primer plato o entrada.
 * @property segundo Plato principal.
 * @property postre Postre.
 * @property precio Precio del plato.
 * @property valoracion Valoración promedio del plato.
 */
data class Plato(
    val nombre: String,
    val primero: String,
    val segundo: String,
    val postre: String,
    val precio: Double,
    val valoracion: Double
)