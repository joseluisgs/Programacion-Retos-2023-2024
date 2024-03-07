package org.example.dto

import kotlinx.serialization.Serializable

/**
 * Clase DTO para la transferencia de datos al importar y exportar a los ficheros los datos de la lista de personajes.
 */
@Serializable
data class HeroeDto(
    val id:String,
    val nickName:String,
    val nombre:String,
    val edad:String,
    val vivo:String,
    val villano:String,
    val habilidad:String,
    val puntosCombate:String
)