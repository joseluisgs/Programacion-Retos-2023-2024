package org.example.services

import org.example.models.Heroe

/**
 * Interfaz para el servicio de heroe
 * @see getAllHeroes
 * @see getHeroeById
 * @see saveHeroe
 * @see updateHeroe
 * @see deleteHeroe
 * @author Jaime Leon Mulero
 * @since 1.0.0
 */
interface HeroeService {
    /**
     * Función que devuelve un array con todos los heroes
     * @return Array genérico
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    fun getAllHeroes(): Array<Heroe>

    /**
     * Función que recibe un id y devuelve un heroe con esa id
     * @return Array genérico
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    fun getHeroeById(id: Int): Heroe

    /**
     * Función que recibe un heroe y devuelve el heroe añadido
     * @return heroe
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    fun saveHeroe(heroe: Heroe): Heroe

    /**
     * Función que recibe una id y un heroe y devuelve el heroe actualizado
     * @return heroe
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    fun updateHeroe(id: Int, heroe: Heroe): Heroe

    /**
     * Función que recibe una id y devuelve el heroe borrado
     * @return heroe
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    fun deleteHeroe(id: Int): Heroe
}