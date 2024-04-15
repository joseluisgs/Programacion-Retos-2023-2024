package org.example.exceptions.personajes

/**
 * Clase sellada que reune las excepciones relacionadas con los personajes.
 *
 * @param message Mensaje de error de la excepción.
 * @author Natalia Gonzalez
 * @since 1.0
 */
sealed class PersonajesException(message: String) : Exception(message) {
    /**
     * Clase que lanza una excepción cuando no se encuentra un personaje.
     *
     * @param message Mensaje de error de la excepción.
     */
    class PersonajesNotFoundException(message: String) : PersonajesException(message)

    /**
     * Clase que lanza una excepción cuando no se puede guardar un personaje.
     *
     * @param message Mensaje de error de la excepción.
     */
    class PersonajesNotSavedException(message: String) : PersonajesException(message)

    /**
     * Clase que lanza una excepción cuando no se puede actualizar un personaje.
     *
     * @param message Mensaje de error de la excepción.
     */
    class PersonajesNotUpdatedException(message: String) : PersonajesException(message)

    /**
     * Clase que lanza una excepción cuando no se puede eliminar un personaje.
     *
     * @param message Mensaje de error de la excepción.
     */
    class PersonajesNotDeletedException(message: String) : PersonajesException(message)

    /**
     * Clase que lanza una excepción cuando no se pueden recuperar los personajes.
     *
     * @param message Mensaje de error de la excepción.
     */
    class PersonajesNotFetchedException(message: String) : PersonajesException(message)
}
