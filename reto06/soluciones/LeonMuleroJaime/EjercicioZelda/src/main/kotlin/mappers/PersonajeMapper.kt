package org.example.mappers

import org.example.dto.PersonajeDto
import org.example.exceptions.personajes.PersonajeExceptions
import org.example.models.Enemigo
import org.example.models.Guerrero
import org.example.models.Personaje
import java.time.LocalDate

fun PersonajeDto.toPersonaje(): Personaje {
    return when (this.tipo) {
        "Guerrero" -> Guerrero (
            nombre = this.nombre,
            habilidad = this.habilidad,
            ataque = this.ataque,
            edad = this.edad,
            arma = this.arma,
            created_at = LocalDate.parse(this.created_at),
            updated_at = if (this.updated_at != null) LocalDate.parse(this.updated_at) else this.updated_at,
            is_deleted = this.is_deleted
        )
        "Enemigo" -> Enemigo (
            nombre = this.nombre,
            habilidad = this.habilidad,
            ataque = this.ataque,
            edad = this.edad,
            arma = this.arma,
            created_at = LocalDate.parse(this.created_at),
            updated_at = if (this.updated_at != null) LocalDate.parse(this.updated_at) else this.updated_at,
            is_deleted = this.is_deleted
        )

        else -> throw PersonajeExceptions.PersonajeTypeNotFoundException("Tipo de personaje no encontrado")
    }
}

fun Personaje.toPersonajeDto():PersonajeDto{
    return when (this) {
        is Guerrero -> PersonajeDto(
            tipo = "Guerrero",
            nombre = this.nombre,
            habilidad = this.habilidad,
            ataque = this.ataque,
            edad = this.edad,
            arma = this.arma,
            created_at = this.created_at.toString(),
            updated_at = this.updated_at.toString(),
            is_deleted = this.is_deleted
        )
        is Enemigo -> PersonajeDto(
            tipo = "Enemigo",
            nombre = this.nombre,
            habilidad = this.habilidad,
            ataque = this.ataque,
            edad = this.edad,
            arma = this.arma,
            created_at = this.created_at.toString(),
            updated_at = this.updated_at.toString(),
            is_deleted = this.is_deleted
        )

        else -> throw PersonajeExceptions.PersonajeTypeNotFoundException("Tipo de personaje no encontrado")
    }
}