package org.example.dto

import kotlinx.serialization.Serializable

@Serializable
data class PersonajeDto(
    val tipo:String,
    val nombre:String,
    val habilidad:String,
    val ataque:Int,
    val edad:Int,
    val arma:String,
    val created_at: String,
    val updated_at: String?,
    val is_deleted:Boolean?
)