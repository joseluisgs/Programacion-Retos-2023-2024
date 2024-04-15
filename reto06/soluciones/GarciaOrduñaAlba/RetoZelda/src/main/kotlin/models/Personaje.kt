package org.example.models

import kotlinx.serialization.Serializable
import java.time.LocalDate

/**
 * Clase que representa un personaje en el sistema.
 * @property id El identificador único del personaje. Por defecto es -1 si no se proporciona.
 * @property tipo El tipo de personaje (ejemplo: Guerrero, Enemigo).
 * @property nombre El nombre del personaje.
 * @property habilidad La habilidad del personaje.
 * @property ataque El nivel de ataque del personaje.
 * @property edad La edad del personaje.
 * @property arma El arma del personaje.
 * @property createdAt La fecha de creación del personaje.
 * @property updatedAt La fecha de actualización del personaje. Puede ser nulo si no se ha actualizado.
 * @property isDeleted Indica si el personaje ha sido eliminado. Por defecto es falso.
 */
abstract class Personaje (
    val id: Int = -1,
    val nombre: String,
    val habilidad: String,
    val ataque: Int,
    val edad: Int,
    val arma: String,
    val createdAt: LocalDate,
    val updatedAt: LocalDate?,
    val isDeleted: Boolean?
)