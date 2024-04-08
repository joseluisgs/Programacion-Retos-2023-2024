package org.example.exceptions.storage

sealed class StorageExceptions(message: String): Exception(message) {
    class StoreException(message: String) : StorageExceptions(message)
    class LoadException(message: String) : StorageExceptions(message)
    class FileNotFound(message: String) : StorageExceptions(message)

}