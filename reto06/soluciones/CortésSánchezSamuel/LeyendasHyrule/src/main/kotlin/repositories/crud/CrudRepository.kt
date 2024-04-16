package repositories.crud

interface CrudRepository<T, ID> {
    fun findAll(): List<T>
    fun save(item: T): T
    fun update(id: ID, item: T): T?
    fun delete(id: ID, logical: Boolean = false): T?
}