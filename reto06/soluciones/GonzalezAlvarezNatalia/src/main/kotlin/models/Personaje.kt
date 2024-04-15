package org.example.models

import kotlinx.serialization.Serializable

/**
 * Clase de datos que representa un personaje.
 *
 * @param id Identificador único del personaje.
 * @param tipo Tipo del personaje.
 * @param nombre Nombre del personaje.
 * @param habilidad Habilidad del personaje.
 * @param ataque Ataque del personaje.
 * @param edad Edad del personaje.
 * @param arma Arma del personaje.
 * @param createdAt Fecha de creación del personaje en formato de cadena de texto.
 * @param updatedAt Fecha de actualización del personaje en formato de cadena de texto.
 * @param isDeleted Indicador de si el personaje ha sido eliminado (true) o no (false).
 * @author Natalia Gonzalez
 * @since 1.0
 */
@Serializable
data class Personaje(
    val id: Long = -1,
    val tipo: String,
    val nombre: String,
    val habilidad: String,
    val ataque: String,
    val edad: Int,
    val arma: String,
    val createdAt: String = "",
    val updatedAt: String = "",
    val isDeleted: Boolean? = false
)
