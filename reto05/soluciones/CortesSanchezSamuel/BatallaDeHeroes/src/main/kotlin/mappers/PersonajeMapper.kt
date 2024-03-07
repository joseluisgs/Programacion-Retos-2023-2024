package mappers

import dto.PersonajeDto
import models.Personaje

fun Personaje.toDto():PersonajeDto{
    return PersonajeDto(
        id = this.id.toString(),
        nickname = this.nickname,
        nombre = this.nombre,
        edad = this.edad.toString(),
        vivo = if (this.vivo) "Si" else "No",
        villano = if (this.villano) "Si" else "No",
        habilidad = this.habilidad,
        pc = this.pc.toString()
    )
}

fun PersonajeDto.toPersonaje():Personaje{
    return Personaje(
        id = this.id.toInt(),
        nickname = this.nickname,
        nombre = this.nombre,
        edad = this.edad.toInt(),
        vivo = this.vivo.toBoolean(),
        villano = this.villano.toBoolean(),
        habilidad = this.habilidad,
        pc = this.pc.toInt()
    )
}