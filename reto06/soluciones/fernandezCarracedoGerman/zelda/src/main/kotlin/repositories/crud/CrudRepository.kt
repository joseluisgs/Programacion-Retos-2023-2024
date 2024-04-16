package org.example.repositories.crud

/**
 * Interfaz de Repositorio genérico con las operaciones a implementar
 */
interface CrudRepository<T,ID> {
    fun findAll(): List<T>
    fun findById(id: ID): T?
    fun save(item: T): T
    fun update(id: ID, item: T): T?
    fun delete(id: ID, logical:Boolean = false): T?
}