package org.example.validators.personaje

import org.example.exceptions.personajes.PersonajesException
import org.example.models.Personaje

/**
 * Clase que valida los datos de un personaje
 */
class PersonajesValidator {

    fun validate(personaje: Personaje){

    //validateId(personaje.id)
    validateNombre(personaje.nombre)
    validateTipo(personaje.tipo)
    validateClase(personaje.clase)
    validateHabilidad(personaje.habilidad)
    validateAtaque(personaje.ataque)
    validateEdad(personaje.edad)
    validateArma(personaje.arma)
    }

    private fun validateHabilidad(habilidad: String) {
        if (habilidad.length<5 || habilidad.length>50){
            throw PersonajesException.PersonajeNotValid("La habilidad del personaje no es válida, debe tener entre 5 y 50 caracteres")
        }

    }

    private fun validateArma(arma: String) {
        if (arma.length < 4 || arma.length > 50) {
            throw PersonajesException.PersonajeNotValid("El arma del personaje no es válida, debe tener entre 4 y 50 caracteres")
        }
    }

    private fun validateEdad(edad: Int) {
        if (edad < 1 || edad > 5000) {
            throw PersonajesException.PersonajeNotValid("La edad del personaje no es válida, debe tener entre 1 y 5000 años")
        }
    }

    private fun validateAtaque(ataque: Int) {
        if (ataque < 1 || ataque >= Int.MAX_VALUE)
            throw PersonajesException.PersonajeNotValid("El ataque del personaje no es válido, debe estar entre 1 y ${Int.MAX_VALUE}")
    }

    private fun validateClase(clase: String) {
        if (clase!="" && (clase.length < 4 || clase.length > 40)) {
            throw PersonajesException.PersonajeNotValid("La clase del personaje no es válida, debe ser vacía o si no, tener entre 4 y 40 caracteres")
        }
    }

    private fun validateTipo(tipo: String) {
        if (tipo != "Guerrero" && tipo != "Enemigo") {
            throw PersonajesException.PersonajeNotValid("El tipo del personaje no es válido, solo puede ser 'Guerrero' o 'Enemigo'")
        }

    }

    private fun validateNombre(nombre: String) {
        if (nombre.length < 4 || nombre.length > 50) {
            throw PersonajesException.PersonajeNotValid("El nombre del personaje no es válido, debe tener entre 4 y 50 caracteres")
        }
    }

/*    private fun validateId(id: Long) {
        if (id < 1) {
            throw PersonajesException.PersonajeNotValid("Id del personaje no válido, debe ser mayor que 0")
        }

    }*/
}