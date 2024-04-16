package org.example.exceptions.storage

/**
 * Clase cerrada para crear un sistema de excepciones orientado a dominio
 * @see org.example.services.storage.base.Storage
 */
sealed class StorageException(message: String):Exception(message) {
    class StoreException(message: String):StorageException(message)
    class LoadException(message: String):StorageException(message)
}