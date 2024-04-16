package org.example.mappers

import org.example.dto.PersonajeDto
import org.example.models.Personaje
import java.time.LocalDate

/**
 * Clase con funciones de extensión para transformar los datos de la clase DTO al modelo y viceversa
 * @see org.example.models.Personaje
 * @see org.example.dto.PersonajeDto
 */
fun PersonajeDto.toPersonaje():Personaje{
    return Personaje(
        id = this.id,
        nombre = this.nombre,
        tipo = this.tipo,
        clase = this.clase,
        habilidad = this.habilidad,
        ataque = this.ataque,
        edad = this.edad,
        arma = this.arma,
        createdAt = LocalDate.parse(this.createdAt),
        updatedAt  = this.updatedAt?.let{ LocalDate.parse(it)},
        isDeleted = this.isDeleted
    )
}

fun Personaje.toPersonajeDto():PersonajeDto{
    return PersonajeDto(
        id = this.id,
        nombre = this.nombre,
        tipo = this.tipo,
        clase = this.clase,
        habilidad = this.habilidad,
        ataque = this.ataque,
        edad = this.edad,
        arma = this.arma,
        createdAt = this.createdAt.toString(),
        updatedAt  = this.updatedAt?.toString(),
        isDeleted = this.isDeleted
    )
}