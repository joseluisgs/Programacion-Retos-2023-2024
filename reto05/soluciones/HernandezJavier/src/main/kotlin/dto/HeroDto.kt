package org.example.dto

import kotlinx.serialization.Serializable

@Serializable
data class HeroDto(
    val id: Int,
    val nickName: String,
    val nombre: String,
    val edad: Int,
    val vivo: Boolean,
    val villano: Boolean,
    val habilidad: String,
    val pc: Int
) {
}