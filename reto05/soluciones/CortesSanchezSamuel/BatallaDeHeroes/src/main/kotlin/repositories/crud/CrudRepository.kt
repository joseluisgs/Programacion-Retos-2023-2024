package repositories.crud

interface CrudRepository<T,ID> {

    fun findAll():List<T>
    fun findById(id: ID):T?
    fun save(value: T):T
    fun update(id: ID,value : T):T?
    fun deleteById(id: ID):T?

}