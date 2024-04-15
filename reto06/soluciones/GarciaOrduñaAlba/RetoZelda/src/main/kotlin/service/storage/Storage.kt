package org.example.service.storage

/**
 * Interfaz genérica que define operaciones básicas de almacenamiento y carga de datos.
 * @param T El tipo de datos a almacenar y cargar.
 */
interface Storage<T> {
    /**
     * Almacena una lista de datos.
     * @param data La lista de datos a almacenar.
     * @return true si la operación de almacenamiento fue exitosa, false de lo contrario.
     */
    fun store(data: List<T>): Boolean

    /**
     * Carga datos desde un archivo.
     * @param fileName El nombre del archivo desde donde cargar los datos.
     * @return Una lista de datos cargados desde el archivo.
     */
    fun load(fileName: String): List<T>
}