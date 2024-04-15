package org.example.mappers

import org.example.dto.PersonajeDto
import org.example.exceptions.personaje.PersonajeExceptions
import org.example.models.Enemigo
import org.example.models.Guerrero
import org.example.models.Personaje
import java.time.LocalDate

/**
 * Convierte un objeto [PersonajeDto] en un objeto [Personaje].
 * @return Un objeto [Personaje] correspondiente al [PersonajeDto].
 * @throws PersonajeTypeNotFoundException Si el tipo de personaje especificado en el [PersonajeDto] no es reconocido.
 */
fun PersonajeDto.toPersonaje(): Personaje {
    return when (this.tipo) {
        "Guerrero" -> Guerrero (
            id = this.id,
            nombre = this.nombre,
            habilidad = this.habilidad,
            ataque = this.ataque,
            edad = this.edad,
            arma = this.arma,
            createdAt = LocalDate.parse(this.createdAt),
            updatedAt = if (this.updatedAt != null) LocalDate.parse(this.updatedAt) else this.updatedAt,
            isDeleted = this.isDeleted
        )
        "Enemigo" -> Enemigo (
            id = this.id,
            nombre = this.nombre,
            habilidad = this.habilidad,
            ataque = this.ataque,
            edad = this.edad,
            arma = this.arma,
            createdAt = LocalDate.parse(this.createdAt),
            updatedAt = if (this.updatedAt != null) LocalDate.parse(this.updatedAt) else this.updatedAt,
            isDeleted = this.isDeleted
        )

        else -> throw PersonajeExceptions.PersonajeTypeNotFoundException("Tipo de personaje no encontrado")
    }
}

/**
 * Convierte un objeto [Personaje] en un objeto [PersonajeDto].
 * @return Un objeto [PersonajeDto] correspondiente al [Personaje].
 * @throws PersonajeTypeNotFoundException Si el tipo de personaje del [Personaje] no es reconocido.
 */
fun Personaje.toPersonajeDto(): PersonajeDto {
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
            createdAt = this.createdAt.toString(),
            updatedAt = this.updatedAt.toString(),
            isDeleted = this.isDeleted
        )

        else -> throw PersonajeExceptions.PersonajeTypeNotFoundException("Tipo de personaje no encontrado")
    }
}