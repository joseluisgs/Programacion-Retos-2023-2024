package org.example.service.cache.base

/**
 * Interfaz genérica para un caché que almacena objetos asociados a claves.
 * @param T El tipo de objetos que se almacenan en el caché.
 * @param KEY El tipo de claves utilizadas para acceder a los objetos en el caché.
 */
interface Cache<T, KEY> {
    /**
     * Almacena un objeto en el caché asociado a la clave especificada.
     * @param key La clave asociada al objeto.
     * @param value El objeto a almacenar en el caché.
     */
    fun put(key: KEY, value: T)

    /**
     * Obtiene el objeto asociado a la clave especificada desde el caché.
     * @param key La clave del objeto a recuperar.
     * @return El objeto asociado a la clave, o nulo si no hay ningún objeto asociado a esa clave.
     */
    fun get(key: KEY): T?

    /**
     * Elimina el objeto asociado a la clave especificada desde el caché.
     * @param key La clave del objeto a eliminar.
     * @return El objeto que fue eliminado del caché, o nulo si no hay ningún objeto asociado a esa clave.
     */
    fun remove(key: KEY): T?

    /**
     * Elimina todos los objetos almacenados en el caché.
     */
    fun clear()
}