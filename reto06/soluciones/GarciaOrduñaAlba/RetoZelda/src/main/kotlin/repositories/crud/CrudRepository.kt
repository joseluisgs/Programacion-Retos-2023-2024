package org.example.repositories.crud

/**
 * Interfaz genérica que define las operaciones básicas de un repositorio CRUD (Create, Read, Update, Delete).
 * @param T El tipo de entidad gestionada por el repositorio.
 * @param ID El tipo de identificador utilizado para las entidades.
 */
interface CrudRepository<T, ID> {
    /**
     * Recupera todas las entidades del repositorio.
     * @return Una lista que contiene todas las entidades almacenadas en el repositorio.
     */
    fun findAll(): List<T>

    /**
     * Recupera una entidad del repositorio por su identificador.
     * @param id El identificador de la entidad a buscar.
     * @return La entidad correspondiente al identificador especificado, o nulo si no se encuentra.
     */
    fun findById(id: ID): T?

    /**
     * Guarda una nueva entidad en el repositorio o actualiza una entidad existente.
     * @param item La entidad a guardar o actualizar.
     * @return La entidad guardada o actualizada.
     */
    fun save(item: T): T

    /**
     * Actualiza una entidad existente en el repositorio.
     * @param id El identificador de la entidad a actualizar.
     * @param item La entidad actualizada.
     * @return La entidad actualizada, o nulo si no se encuentra la entidad con el identificador especificado.
     */
    fun update(id: ID, item: T): T?

    fun delete(id: ID, logical: Boolean = false): T?
}