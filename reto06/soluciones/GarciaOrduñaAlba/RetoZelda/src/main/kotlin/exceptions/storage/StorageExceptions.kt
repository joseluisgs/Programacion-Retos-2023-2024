package org.example.exceptions.storage

/**
 * Sealed class para representar errores relacionados con operaciones de almacenamiento.
 */
sealed class StorageExceptions(message: String) : Exception(message) {
    /**
     *Error lanzado cuando ocurre un error al intentar almacenar datos.
     * @param message El mensaje de error asociado con la excepción.
     */
    class StoreExceptions(message: String) : StorageExceptions(message)

    /**
     * Error lanzado cuando ocurre un error al intentar cargar datos.
     * @param message El mensaje de error asociado con la excepción.
     */
    class LoadExceptions(message: String) : StorageExceptions(message)
}