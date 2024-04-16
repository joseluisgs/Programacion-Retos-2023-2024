package org.example.exceptions.personajes

sealed class PersonajeError(val message: String){
    class PersonajeNotFound(message: String) : PersonajeError(message)
    class PersonajeNotSaved(message: String) : PersonajeError(message)
    class PersonajeNotUpdated(message: String) : PersonajeError(message)
    class PersonajeNotDeleted(message: String) : PersonajeError(message)
    class PersonajesNotFetched(message: String) : PersonajeError(message)
    class PersonajeInvalido(message: String): PersonajeError(message)
}