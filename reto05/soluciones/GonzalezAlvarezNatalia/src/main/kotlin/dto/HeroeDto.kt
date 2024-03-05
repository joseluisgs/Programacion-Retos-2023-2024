package org.example.dto

/**
 * Clase que representa un héroe en formato DTO.
 * @param id El ID del héroe.
 * @param nickname El apodo del héroe.
 * @param nombre El nombre del héroe.
 * @param edad La edad del héroe.
 * @param vivo Indica si el héroe está vivo o no.
 * @param villano Indica si el personaje es un villano o no.
 * @param habilidad La habilidad del héroe.
 * @param pc Los puntos de combate del héroe.
 * @author Natalia González
 * @since 1.0
 */
data class HeroeDto(
    val id : String,
    val nickname : String,
    val nombre : String,
    val edad : String,
    val vivo : String,
    val villano : String,
    val habilidad : String,
    val pc : String
)
