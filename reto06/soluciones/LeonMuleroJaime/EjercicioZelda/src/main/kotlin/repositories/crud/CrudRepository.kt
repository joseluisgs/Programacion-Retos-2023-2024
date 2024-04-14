package org.example.repositories.crud

interface CrudRepository<T, ID> {
    fun findAll(): List<T>
    fun findById(id: ID): T?
    fun save(item: T): T
    fun update(id: ID, item: T): T?
    fun deleteFisico(id: ID): T?
    fun deleteLogico(id: ID): T?
}