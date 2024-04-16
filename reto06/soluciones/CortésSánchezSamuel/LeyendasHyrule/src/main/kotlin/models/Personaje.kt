package models

import kotlinx.serialization.Serializable

@Serializable
open class Personaje(
    val nombre: String,
    val habilidad: String,
    val ataque: Int,
    val edad: Int,
    val arma: String
)