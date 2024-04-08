package org.example.exceptions.personajes

sealed class PersonajeExceptions(message: String): Exception(message) {
    class PersonajeNotFoundException(message: String): PersonajeExceptions(message)
    class PersonajeTypeNotFoundException(message: String): PersonajeExceptions(message)
    class PersonajeNotSavedException(message: String) : PersonajeExceptions(message)
    class PersonajeNotUpdatedException(message: String) : PersonajeExceptions(message)
    class PersonajeNotDeletedException(message: String) : PersonajeExceptions(message)
    class PersonajeNotFetchedException(message: String) : PersonajeExceptions(message)
}