package org.example.exceptions.storage

sealed class StorageError(val message: String){
    class StoreError(message: String) : StorageError(message)
    class LoadError(message: String) : StorageError(message)
}