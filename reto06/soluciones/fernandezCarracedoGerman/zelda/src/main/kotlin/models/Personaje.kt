package org.example.models

import java.time.LocalDate

/**
 * Clase POJO que representa un personaje
 */
data class Personaje (
    val id: Long = -1,
    val nombre: String,
    val tipo: String,
    val clase: String,
    val habilidad: String,
    val ataque: Int,
    val edad: Int,
    val arma: String,
    val createdAt: LocalDate = LocalDate.now(),
    val updatedAt: LocalDate?=LocalDate.now(),
    val isDeleted: Boolean? = false
){
    override fun toString(): String {
        return "Personaje (id=$id, \tnombre=$nombre, $tipo${if (this.clase!="") " - " + this.clase else ""}, "+
                "H=$habilidad, A=$ataque, E=$edad, AR=$arma, "+
                "createdAt=$createdAt, updatedAt=$updatedAt, isDeleted=$isDeleted)"
    }
}