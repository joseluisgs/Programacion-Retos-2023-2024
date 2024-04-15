package org.example.dto

import kotlinx.serialization.Serializable
/**
 * Clase que representa un DTO (Data Transfer Object) para un personaje en un juego.
 * Esta clase almacena información básica sobre un personaje.
 * @property id El identificador único del personaje.
 * @property tipo El tipo o categoría del personaje.
 * @property nombre El nombre del personaje.
 * @property habilidad La habilidad especial del personaje.
 * @property ataque El valor de ataque del personaje.
 * @property edad La edad del personaje.
 * @property arma El arma que utiliza el personaje.
 * @property createdAt La fecha y hora de creación del personaje en formato de cadena de texto.
 * @property updatedAt La fecha y hora de la última actualización del personaje en formato de cadena de texto, puede ser nulo si no se ha actualizado.
 * @property isDeleted Indica si el personaje ha sido eliminado.
 */
@Serializable
data class PersonajeDto(
    val id: Int,
    val tipo: String,
    val nombre: String,
    val habilidad: String,
    val ataque: Int,
    val edad: Int,
    val arma: String,
    val createdAt: String,
    val updatedAt: String?,
    val isDeleted: Boolean?
)