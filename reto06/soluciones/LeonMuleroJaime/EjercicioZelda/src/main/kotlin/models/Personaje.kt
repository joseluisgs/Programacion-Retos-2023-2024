package org.example.models

import java.time.LocalDate

abstract class Personaje (
    val nombre: String,
    val habilidad: String,
    val ataque: Int,
    val edad: Int,
    val arma: String,
    val created_at: LocalDate,
    val updated_at: LocalDate?,
    val is_deleted: Boolean?
)