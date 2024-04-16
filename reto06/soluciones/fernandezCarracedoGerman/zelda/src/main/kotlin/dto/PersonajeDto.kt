package org.example.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Clase DTO para las transferencias a y desde la base de datos, así como al importar y exportar a los ficheros de personajes
 */
@Serializable
data class PersonajeDto(
    val id: Long,
    val nombre: String,
    val tipo: String,
    val clase: String,
    val habilidad: String,
    val ataque: Int,
    val edad: Int,
    val arma: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String?,
    @SerialName("is_deleted")
    val isDeleted: Boolean?
)
