package org.example.exceptions

sealed class StorageException(message: String) : Exception(message) {
    class StoreException(message: String) : StorageException(message)
    class LoadException(message: String) : StorageException(message)
}