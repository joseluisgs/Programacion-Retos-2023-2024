package services.storage

import java.io.File

interface Storage<T> {
    fun store(data: List<T>): Boolean
    fun load(file: String): List<T>
}