package org.example.models

import kotlinx.serialization.Serializable

data class Hero(
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