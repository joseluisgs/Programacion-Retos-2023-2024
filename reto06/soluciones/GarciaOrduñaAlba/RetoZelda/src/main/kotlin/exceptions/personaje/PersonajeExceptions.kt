package org.example.exceptions.personaje

/**
 * Sealed class para representar errores relacionados con operaciones en la base de datos de personajes.
 */
/**
 * Clase sellada que representa excepciones relacionadas con operaciones de personajes.
 * Esta clase sirve como una base para las excepciones específicas relacionadas con los personajes.
 * @param message El mensaje de error asociado con la excepción.
 */
sealed class PersonajeExceptions(message: String): Exception(message) {
    /**
     * Excepción lanzada cuando un personaje no se encuentra.
     */
    class PersonajeNotFoundException(message: String): PersonajeExceptions(message)

    /**
     * Excepción lanzada cuando no se encuentra un tipo específico de personaje.
     */
    class PersonajeTypeNotFoundException(message: String): PersonajeExceptions(message)

    /**
     * Excepción lanzada cuando un personaje no se guarda correctamente.
     */
    class PersonajeNotSavedException(message: String) : PersonajeExceptions(message)

    /**
     * Excepción lanzada cuando un personaje no se actualiza correctamente.
     */
    class PersonajeNotUpdatedException(message: String) : PersonajeExceptions(message)

    /**
     * Excepción lanzada cuando un personaje no se elimina correctamente.
     */
    class PersonajeNotDeletedException(message: String) : PersonajeExceptions(message)

    /**
     * Excepción lanzada cuando no se puede obtener información de un personaje.
     */
    class PersonajeNotFetchedException(message: String) : PersonajeExceptions(message)

    /**
     * Excepción lanzada cuando hay un problema con los datos de un personaje.
     */
    class PersonajeDataException(message: String) : PersonajeExceptions(message)
}