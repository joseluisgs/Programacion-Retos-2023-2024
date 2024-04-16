package org.example.cache

/**
 * Interfaz de Caché genérica con las operaciones a implementar
 */
interface Cache<T,KEY> {
    fun get(key:KEY): T?
    fun put(key:KEY, value: T)
    fun remove(key: KEY)
    fun clear()
}