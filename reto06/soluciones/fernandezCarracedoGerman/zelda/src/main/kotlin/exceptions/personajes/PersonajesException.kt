package org.example.exceptions.personajes

/**
 * Clase cerrada para crear un sistema de excepciones orientado a dominio
 * @see org.example.models.Personaje
 */
sealed class PersonajesException(message:String):Exception(message) {
    class PersonajeNotFetchedException(message: String): PersonajesException(message)
    class PersonajeNotFound(message: String): PersonajesException(message)
    class PersonajeNotSavedException(message: String) : PersonajesException(message)
    class PersonajeNotUpdatedException(message: String) : PersonajesException(message)
    class PersonajeNotDeletedException(message: String) : PersonajesException(message)
    class PersonajeNotValid(message: String):PersonajesException(message)
}