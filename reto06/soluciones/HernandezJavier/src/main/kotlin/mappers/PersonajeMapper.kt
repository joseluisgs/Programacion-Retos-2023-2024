package org.example.mappers

import org.example.dto.PersonajeDto
import org.example.models.Enemigo
import org.example.models.Guerrero
import org.example.models.Personaje

fun PersonajeDto.toPersonaje(): Personaje{
    return when(this.tipo){
        "Guerreros" -> Guerrero(
            nombre = this.nombre,
            edad = this.edad,
            ataque = this.ataque,
            arma = this.arma,
            habilidad = this.habilidad,
            isDeleted = this.isDeleted
        )
        "Enemigos" -> Enemigo(
            nombre = this.nombre,
            edad = this.edad,
            ataque = this.ataque,
            arma = this.arma,
            habilidad = this.habilidad,
            isDeleted = this.isDeleted
        )
        else -> throw IllegalArgumentException("Tipo de personaje no soportado")
    }
}

fun Personaje.toPersonajeDto():PersonajeDto{
    return when(this) {
        is Guerrero -> PersonajeDto(
            nombre = this.nombre,
            tipo = "Guerreros",
            habilidad = this.habilidad,
            ataque = this.ataque,
            edad = this.edad,
            arma = this.arma,
            isDeleted = this.isDeleted
        )

        is Enemigo -> PersonajeDto(
            nombre = this.nombre,
            tipo = "Enemigos",
            habilidad = this.habilidad,
            ataque = this.ataque,
            edad = this.edad,
            arma = this.arma,
            isDeleted = this.isDeleted
        )
        else -> throw IllegalArgumentException("Tipo de personaje no soportado")
    }
}