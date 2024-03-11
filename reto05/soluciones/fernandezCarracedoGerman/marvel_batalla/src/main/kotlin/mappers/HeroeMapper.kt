package org.example.mappers

import org.example.dto.HeroeDto
import org.example.models.Heroe

/**
 * Mapeador para pasar del modelo al DTO y viceversa, implementado mediante funciones de extensión sobre las clases
 * Heroe y HeroeDto
 * @see Heroe
 * @see HeroeDto
 */
fun HeroeDto.toHeroe(): Heroe {
    return Heroe(
        this.id.toInt(),
        this.nickName,
        this.nombre,
        this.edad.toInt(),
        this.vivo.toBoolean(),
        this.villano.toBoolean(),
        this.habilidad,
        this.puntosCombate.toInt()
    )
}

fun Heroe.toHeroeDto():HeroeDto{
    return HeroeDto(
        this.id.toString(),
        this.nickName,
        this.nombre,
        this.edad.toString(),
        this.vivo.toString(),
        this.villano.toString(),
        this.habilidad,
        this.puntosCombate.toString()
    )
}