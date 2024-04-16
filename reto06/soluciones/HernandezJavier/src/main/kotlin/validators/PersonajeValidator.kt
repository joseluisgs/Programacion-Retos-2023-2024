package org.example.validators

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.exceptions.personajes.PersonajeError
import org.example.models.Personaje

class PersonajeValidator {
    fun validate(personaje: Personaje): Result<Personaje, PersonajeError>{
        return when{
            personaje.nombre.isBlank() -> Err(PersonajeError.PersonajeInvalido("El nombre del personaje no puede estar vacio"))
            personaje.habilidad.isBlank() -> Err(PersonajeError.PersonajeInvalido("La habilidad del personaje no puede estar vacia"))
            personaje.arma.isBlank() -> Err(PersonajeError.PersonajeInvalido("El arma del personaje no puede estar vacía"))
            personaje.edad <= 0 -> Err(PersonajeError.PersonajeInvalido("La edad del personaje no puede ser igual o inferior a cero"))
            else -> Ok(personaje)
        }
    }
}