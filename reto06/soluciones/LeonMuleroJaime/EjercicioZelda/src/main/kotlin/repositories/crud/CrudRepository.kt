package org.example.repositories.crud

interface CrudRepository<T, R> {
    fun findAll(): List<T>
    fun findByName(name: R): T?
    fun save(item: T): T
    fun update(name: R, item: T): T?
    fun deleteFisico(name: R): T?
    fun deleteLogico(name: R): T?
}