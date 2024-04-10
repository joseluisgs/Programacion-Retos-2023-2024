package mappers

import dto.PersonajeDto
import exceptions.personajes.PersonajesExceptions
import models.Enemigo
import models.Guerrero
import models.Personaje

fun PersonajeDto.toPersonaje():Personaje{
    return when(this.tipo){
        "Guerrero"->Guerrero(
            nombre = this.nombre,
            habilidad = this.habilidad,
            ataque=this.ataque,
            edad=this.edad,
            arma=this.arma,
            isDeleted=this.isDeleted
        )
        "Enemigo"->Enemigo(
            nombre = this.nombre,
            habilidad = this.habilidad,
            ataque=this.ataque,
            edad=this.edad,
            arma=this.arma,
            isDeleted=this.isDeleted
        )

        else -> throw PersonajesExceptions.TipoNoEncontrado()
    }
}

fun Personaje.toPersonajeDto():PersonajeDto{
    return when(this){
        is Guerrero-> PersonajeDto(
            nombre = this.nombre,
            tipo = "Guerrero",
            habilidad = this.habilidad,
            ataque=this.ataque,
            edad=this.edad,
            arma=this.arma,
            isDeleted=this.isDeleted
        )
        is Enemigo -> PersonajeDto(
            nombre = this.nombre,
            tipo = "Enemigo",
            habilidad = this.habilidad,
            ataque=this.ataque,
            edad=this.edad,
            arma=this.arma,
            isDeleted=this.isDeleted
        )

        else -> throw PersonajesExceptions.TipoNoEncontrado()
    }
}