package org.example.mappers

import PersonajeDto
import org.example.models.*
fun PersonajeDto.toPersonaje(): Personaje {
    return when (this.tipo) {
        "Enemigo" -> Enemigo(
            id = this.id,
            nombre = this.nombre,
            habilidad = this.habilidad,
            ataque = this.ataque,
            edad = this.edad!!,
            arma = this.arma,
        )
        "Aliado" -> Aliado(
            id = this.id,
            nombre = this.nombre,
            habilidad = this.habilidad,
            ataque = this.ataque,
            edad = this.edad!!,
            arma = this.arma,
        )
        else -> throw IllegalArgumentException("Tipo de persona no soportado")
    }
}
fun Personaje.toPersonajeDto(): PersonajeDto {
    return when (this) {

        is Aliado -> PersonajeDto(
            id = this.id,
            tipo = "Aliado",
            nombre = this.nombre,
            habilidad = this.habilidad,
            ataque = this.ataque,
            edad = this.edad,
            arma = this.arma,
        )

        is Enemigo -> PersonajeDto(
            id = this.id,
            tipo = "Enemigo",
            nombre = this.nombre,
            habilidad = this.habilidad,
            ataque = this.ataque,
            edad = this.edad,
            arma = this.arma,
        )
        else -> throw IllegalArgumentException("Tipo de persona no soportado")
    }
}