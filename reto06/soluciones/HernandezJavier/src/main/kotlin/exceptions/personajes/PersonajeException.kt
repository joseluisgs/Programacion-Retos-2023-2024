package org.example.exceptions.personajes

sealed class PersonajeException(message: String) : Exception(message) {
    class PersonajeNotFoundException(message: String) : PersonajeException(message)
    class PersonajeNotSavedException(message: String) : PersonajeException(message)
    class PersonajeNotUpdatedException(message: String) : PersonajeException(message)
    class PersonajeNotDeletedException(message: String) : PersonajeException(message)
    class PersonajesNotFetchedException(message: String) : PersonajeException(message)
}