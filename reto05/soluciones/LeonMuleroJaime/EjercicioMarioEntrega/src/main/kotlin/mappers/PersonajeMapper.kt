package mappers

import dto.PersonajeDTO
import models.Personaje

fun Personaje.toDto(): PersonajeDTO {
    return PersonajeDTO(
        id = this.id.toString(),
        nickName = this.nickName,
        nombre = this.nombre,
        edad = this.edad.toString(),
        vivo = this.vivo.toString(),
        villano = this.villano.toString(),
        habilidad = this.habilidad,
        puntosCombate = this.puntosCombate.toString()
    )
}

fun PersonajeDTO.toModel(): Personaje {
    return Personaje(
        id = this.id.toInt(),
        nickName = this.nickName,
        nombre = this.nombre,
        edad = this.edad.toInt(),
        vivo = this.vivo.toBoolean(),
        villano = this.villano.toBoolean(),
        habilidad = this.habilidad,
        puntosCombate = this.puntosCombate.toInt()
    )
}