package org.example.models

import kotlinx.serialization.Serializable

/**
 * Clase que representa a un héroe.
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

@Serializable
data class Heroe(
    val id : Int,
    val nickname : String,
    val nombre : String,
    val edad : Int,
    val vivo : Boolean,
    val villano : Boolean,
    val habilidad : String,
    val pc : Int
)
