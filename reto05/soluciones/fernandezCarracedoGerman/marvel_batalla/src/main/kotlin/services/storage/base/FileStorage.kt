package org.example.services.storage.base

import java.io.File

/**
 *  Interfaz con las operaciones de Almacenamiento de ficheros, de tipo genérico
 */
interface FileStorage<T> {
    fun readFromFile(file: File):List<T>
    fun writeToFile(list:List<T>,file:File)
}