package org.example.dto

/**
 * Clase de datos para PersonajesDto
 *
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 *
 * @param id identificador único para el personaje
 * @param nickName versión abreviada del nombre del personaje
 * @param nombre nombre completo del personaje
 * @param edad edad del personaje
 * @param vivo si el personaje está vivo o no
 * @param villano si el personaje es un villano o no
 * @param habilidades habilidades especiales del personaje
 * @param puntosCombate puntos de daño que el personaje puede infligir
 */
data class PersonajesDto (
    val id: Int,
    val nickName: String,
    val nombre: String,
    val edad: Int,
    val vivo: Boolean,
    val villano: Boolean,
    val habilidades: String,
    val puntosCombate: Int,
)