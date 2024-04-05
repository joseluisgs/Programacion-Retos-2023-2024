package repositories.crud

interface CrudRepository<T,ID> {
    fun findAll(): List<T>
    fun findByName(name: ID): T?
    fun save(item: T): T
    fun update(name: ID, item: T): T?
    fun delete(name: ID, logical: Boolean = false): T?
}