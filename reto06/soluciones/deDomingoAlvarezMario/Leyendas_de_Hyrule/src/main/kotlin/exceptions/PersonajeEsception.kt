package org.example.exceptions

sealed class PersonajeEsception(message: String) : Exception(message) {
    class PersonajeNotFoundException(message: String) : PersonajeEsception(message)
    class PersonaNotSavedException(message: String) : PersonajeEsception(message)
    class PersonaNotUpdatedException(message: String) : PersonajeEsception(message)
    class PersonaNotDeletedException(message: String) : PersonajeEsception(message)
    class PersonajeNotFetchedException(message: String) : PersonajeEsception(message)
}