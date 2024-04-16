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
            id = this.id,
            nombre = this.nombre,
            habilidad = this.habilidad,
            ataque = this.ataque,
            edad = this.edad,
            arma = this.arma,
            createdAt = LocalDate.parse(this.created_at),
            updatedAt = if (this.updated_at != null) LocalDate.parse(this.updated_at) else this.updated_at,
            isDeleted = this.is_deleted
        )
        "Enemigo" -> Enemigo (
            id = this.id,
            nombre = this.nombre,
            habilidad = this.habilidad,
            ataque = this.ataque,
            edad = this.edad,
            arma = this.arma,
            createdAt = LocalDate.parse(this.created_at),
            updatedAt = if (this.updated_at != null) LocalDate.parse(this.updated_at) else this.updated_at,
            isDeleted = this.is_deleted
        )

        else -> throw PersonajeExceptions.PersonajeTypeNotFoundException("Tipo de personaje no encontrado")
    }
}

fun Personaje.toPersonajeDto():PersonajeDto{
    return when (this) {
        is Guerrero -> PersonajeDto(
            id = this.id,
            tipo = "Guerrero",
            nombre = this.nombre,
            habilidad = this.habilidad,
            ataque = this.ataque,
            edad = this.edad,
            arma = this.arma,
            createdAt = this.createdAt.toString(),
            updatedAt = this.updatedAt.toString(),
            isDeleted = this.isDeleted
        )
        is Enemigo -> PersonajeDto(
            id = this.id,
            tipo = "Enemigo",
            nombre = this.nombre,
            habilidad = this.habilidad,
            ataque = this.ataque,
            edad = this.edad,
            arma = this.arma,
            created_at = this.createdAt.toString(),
            updated_at = this.updatedAt.toString(),
            is_deleted = this.isDeleted
        )

        else -> throw PersonajeExceptions.PersonajeTypeNotFoundException("Tipo de personaje no encontrado")
    }
}