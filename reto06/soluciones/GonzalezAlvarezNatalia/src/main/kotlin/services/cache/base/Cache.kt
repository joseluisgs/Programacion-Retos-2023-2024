package org.example.services.cache.base

/**
 * Interfaz que define las operaciones básicas de una caché genérica.
 *
 * @param T Tipo de valor almacenado en la caché.
 * @param KEY Tipo de datos de la clave utilizada para acceder a los valores en la caché.
 * @since 1.0
 * @author Natalia Gonzalez
 */
interface Cache<T, KEY> {
    /**
     * Almacena un valor en la caché con la clave especificada.
     *
     * @param key Clave asociada al valor.
     * @param value Valor a almacenar en la caché.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun put(key: KEY, value: T)

    /**
     * Recupera el valor asociado a la clave especificada desde la caché.
     *
     * @param key Clave del valor a recuperar.
     * @return Valor asociado a la clave, o null si no se encuentra ningún valor para la clave especificada.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun get(key: KEY): T?

    /**
     * Elimina el valor asociado a la clave especificada de la caché.
     *
     * @param key Clave del valor a eliminar.
     * @return Valor asociado a la clave antes de eliminarlo, o null si no se encuentra ningún valor para la clave especificada.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun remove(key: KEY): T?

    /**
     * Elimina todos los valores almacenados en la caché.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun clear()
}
