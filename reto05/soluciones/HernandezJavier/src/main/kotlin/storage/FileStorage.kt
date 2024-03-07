package org.example.storage

interface FileStorage <T>{
    fun readFromFile(): List<T>
    fun writeFromFile(list: List<T>)
}