package org.example.repositories.crud

/**
 * Interfaz para el respositorio C.R.U.D.
 * @see getAllHeroes
 * @see getHeroeById
 * @see saveHeroe
 * @see updateHeroe
 * @see deleteHeroe
 * @author Jaime Leon Mulero
 * @since 1.0.0
 */
interface CrudRepository<T, ID> {
    /**
     * Función que devuelve un array con todos los heroes
     * @return Array genérico
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    fun getAllHeroes(): Array<T>

    /**
     * Función que recibe un id y devuelve un heroe con esa id
     * @return Array genérico
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    fun getHeroeById(id: ID): T?

    /**
     * Función que recibe un heroe y devuelve el heroe añadido
     * @return heroe o nulo
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    fun saveHeroe(value: T): T

    /**
     * Función que recibe una id y un heroe y devuelve el heroe actualizado
     * @return heroe
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    fun updateHeroe(id: ID, value: T): T?

    /**
     * Función que recibe una id y devuelve el heroe borrado
     * @return heroe o nulo
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    fun deleteHeroe(id: ID): T?
}