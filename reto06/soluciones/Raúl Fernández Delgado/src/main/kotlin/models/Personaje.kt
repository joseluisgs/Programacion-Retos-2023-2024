package models

abstract class Personaje (
    val nombre: String,
    val habilidad: String,
    val ataque: Int,
    val arma: String,
    val isDeleted: Boolean?,
    val edad: Int
)