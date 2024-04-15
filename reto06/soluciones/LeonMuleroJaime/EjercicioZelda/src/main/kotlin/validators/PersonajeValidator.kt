package org.example.validators

import org.example.exceptions.personajes.PersonajeExceptions
import org.example.models.Personaje

class PersonajeValidator {
    fun validate(personaje: Personaje): Personaje {
        val regexText = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]{3,}$".toRegex()
        val regexNum = "^[0-9]+$".toRegex()

        if (personaje.nombre.isEmpty()) throw PersonajeExceptions.PersonajeDataException("El nombre no puede estar vacío")
        if (!personaje.nombre.matches(regexText)) throw PersonajeExceptions.PersonajeDataException("El nombre solo puede contener letras [a-zA-Z]")

        if (personaje.habilidad.isEmpty()) throw PersonajeExceptions.PersonajeDataException("La habilidad no puede estar vacía")
        if (!personaje.habilidad.matches(regexText)) throw PersonajeExceptions.PersonajeDataException("La habilidad solo puede contener letras [a-zA-Z]")

        if (!personaje.ataque.toString().matches(regexNum)) throw PersonajeExceptions.PersonajeDataException("El ataque debe ser un número")
        if (personaje.ataque <= 0) throw PersonajeExceptions.PersonajeDataException("El ataque debe ser mayor a cero")

        if (!personaje.edad.toString().matches(regexNum)) throw PersonajeExceptions.PersonajeDataException("La edad debe ser un número")
        if (personaje.edad <= 0) throw PersonajeExceptions.PersonajeDataException("La edad debe ser mayor a cero")

        if (personaje.arma.isEmpty()) throw PersonajeExceptions.PersonajeDataException("El arma no puede estar vacía")
        if (!personaje.arma.matches(regexText)) throw PersonajeExceptions.PersonajeDataException("El arma solo contener letras [a-zA-Z]")

        return personaje
    }
}