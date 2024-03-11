package mappers

import dto.PersonajesDto
import models.Personaje

fun Personaje.toDto(): PersonajesDto{
    return PersonajesDto(
        id = this.id.toString(),
        nickName = this.nickName,
        nombre = this.nombre,
        edad = this.edad.toString(),
        personajeVivo = if (this.personajeVivo) "Si, si lo esta" else "No, no lo esta",
        villano = if (this.villano) "Si, si lo es" else "No, no lo es",
        habilidad = this.habilidad,
        pc = this.pc.toString()
    )
}



fun PersonajesDto.toPersonaje(): Personaje {
    return Personaje(
        id = this.id.toInt(),
        nickName = this.nickName,
        nombre = this.nombre,
        edad = this.edad.toInt(),
        personajeVivo = this.personajeVivo.toBoolean(),
        villano = this.villano.toBoolean(),
        habilidad = this.habilidad,
        pc = this.pc.toInt()
    )
}