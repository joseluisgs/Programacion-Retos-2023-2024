package org.example.repositories.base



interface CrudRepository <T,ID>{
    fun getAll(): Array<T>
    fun getById(key: ID): T?
    fun getByName(name: String): T?
    fun save(value: T): T
    fun update(key: ID, value: T): T?
    fun delete(key: ID): T?
    fun sortById(): Array<T>
    fun sortByName(): Array<T>

}