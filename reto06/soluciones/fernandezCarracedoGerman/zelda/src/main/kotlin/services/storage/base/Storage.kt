package org.example.services.storage.base

interface Storage<T> {
    fun store(data: List<T>, fileName: String): Boolean
    fun load(fileName: String): List<T>
}