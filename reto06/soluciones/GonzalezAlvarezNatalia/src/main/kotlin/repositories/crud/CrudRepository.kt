package org.example.repositories.crud

/**
 * Interfaz que define las operaciones básicas de un repositorio CRUD.
 *
 * @param T Tipo de entidad almacenada en el repositorio.
 * @param ID Tipo de datos del identificador de la entidad.
 * @since 1.0
 * @author Natalia Gonzalez
 */
interface CrudRepository<T, ID> {
    /**
     * Busca y devuelve todas las entidades almacenadas en el repositorio.
     *
     * @return Lista de todas las entidades almacenadas.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun findAll(): List<T>

    /**
     * Busca y devuelve una entidad por su ID.
     *
     * @param id ID de la entidad a buscar.
     * @return La entidad encontrada, o null si no se encuentra ninguna entidad con el ID especificado.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun findById(id: ID): T?

    /**
     * Guarda una nueva entidad en el repositorio o actualiza una entidad existente.
     *
     * @param item Entidad a guardar o actualizar.
     * @return La entidad guardada o actualizada.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun save(item: T): T

    /**
     * Actualiza una entidad existente por su ID.
     *
     * @param id ID de la entidad a actualizar.
     * @param item Nueva versión de la entidad.
     * @return La entidad actualizada, o null si no se encuentra ninguna entidad con el ID especificado.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun update(id: ID, item: T): T?

    /**
     * Elimina una entidad por su ID.
     *
     * @param id ID de la entidad a eliminar.
     * @param logical Indica si se debe realizar una eliminación lógica (true) o física (false). Por defecto, es false.
     * @return La entidad eliminada, o null si no se encuentra ninguna entidad con el ID especificado.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun delete(id: ID, logical: Boolean = false): T?
}
