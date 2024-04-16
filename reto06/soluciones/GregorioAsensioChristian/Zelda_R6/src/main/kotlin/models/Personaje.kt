package org.example.models

import java.time.LocalDate

abstract class Personaje(
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