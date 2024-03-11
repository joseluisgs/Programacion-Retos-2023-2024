package storage

import java.io.File

interface Storage<T> {
    fun load(file: File): List<T>
    fun save(lista:List<T>,file: File)
}