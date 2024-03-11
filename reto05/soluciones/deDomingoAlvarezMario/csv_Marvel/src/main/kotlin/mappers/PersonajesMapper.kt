package org.example.mappers

import org.example.dto.PersonajesDto
import org.example.models.Heroe
import org.example.models.Personaje
import org.example.models.Villano

/**
 * Pasamos los personajes a Héroe o Villano según un Boolean
 * Especificamos el Tipo
 *
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 *
 * @return Villano si villano == True
 * @return Héroe si villano == False
 */
class PersonajesMapper {
    fun PersonajesDto.toPersonaje(): Personaje {
        return when (this.villano) {
            true -> Villano(
                this.id,
                this.nickName,
                this.nombre,
            )
            false -> Heroe()
        }
    }
}
