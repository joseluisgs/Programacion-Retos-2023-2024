package org.example.services.storage

/**
 * Interfaz que define métodos para almacenar y cargar datos de un tipo específico.
 *
 * @param T el tipo de datos que se almacenarán y cargarán.
 * @since 1.0
 * @author Natalia Gonzalez
 */
interface Storage<T> {
    /**
     * Almacena los datos proporcionados.
     *
     * @param data Lista de datos a almacenar.
     * @return true si el almacenamiento fue exitoso, false de lo contrario.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun store(data: List<T>): Boolean

    /**
     * Carga los datos desde un archivo con el nombre especificado.
     *
     * @param fileName Nombre del archivo desde el cual cargar los datos.
     * @return Lista de datos cargados.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun load(fileName: String): List<T>
}
