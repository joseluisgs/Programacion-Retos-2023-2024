package dto

import kotlinx.serialization.Serializable

@Serializable
data class PersonajeDTO (
    val id: String,
    val nickName: String,
    val nombre: String,
    val edad: String,
    val vivo: String,
    val villano: String,
    val habilidad: String,
    val puntosCombate: String
)