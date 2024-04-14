package org.example.exceptions.storage

/**
 * Clase sellada que reune las excepciones relacionadas con el almacenamiento.
 *
 * @param message Mensaje de error de la excepción.
 * @author Natalia Gonzalez
 * @since 1.0
 */
sealed class StorageException(message: String) : Exception(message) {
    /**
     * Clase que lanza una excepción cuando ocurre un error al almacenar datos.
     *
     * @param message Mensaje de error de la excepción.
     */
    class StoreException(message: String) : StorageException(message)

    /**
     * Clase que lanza una excepción cuando ocurre un error al cargar datos.
     *
     * @param message Mensaje de error de la excepción.
     */
    class LoadException(message: String) : StorageException(message)
}
