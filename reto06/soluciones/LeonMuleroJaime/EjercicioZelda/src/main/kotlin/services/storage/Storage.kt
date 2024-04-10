package org.example.services.storage

interface Storage<T> {
    fun store(data: List<T>): Boolean
    fun load(fileName: String): List<T>
}