package org.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Personaje(
    val id: Long = -1,
    val tipo: String,
    val nombre: String,
    val habilidad: String,
    val ataque: String,
    val edad: String,
    val arma: String,
    val isDeleted: Boolean = false
)