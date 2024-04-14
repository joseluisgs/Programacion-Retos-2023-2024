package org.example.services.cache.personajes

import org.example.config.Config
import org.example.models.Personaje
import org.example.services.cache.base.Cache
import org.lighthousegames.logging.logging

private val logger = logging()

/**
 * Implementación de la caché para almacenar objetos de tipo [Personaje].
 *
 * @since 1.0
 * @author Natalia Gonzalez
 */
class PersonajesCache : Cache<Personaje, Long> {
    private val cache: MutableMap<Long, Personaje> = mutableMapOf()

    /**
     * Almacena un personaje en la caché con la clave especificada.
     *
     * @param key ID del personaje.
     * @param value Objeto [Personaje] a almacenar en la caché.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun put(key: Long, value: Personaje) {
        logger.debug { "Guardando personaje en cache con id $key" }
        if (cache.size >= Config.cacheSize && !cache.containsKey(key)) {
            val firstKey = cache.keys.first()
            logger.debug { "Eliminando personaje en cache con id $firstKey porque está llena" }
            cache.remove(firstKey)
        }
        cache[key] = value
    }

    /**
     * Obtiene un personaje de la caché utilizando su ID como clave.
     *
     * @param key ID del personaje a recuperar.
     * @return Objeto [Personaje] asociado al ID especificado, o null si no se encuentra en la caché.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun get(key: Long): Personaje? {
        logger.debug { "Obteniendo personaje en cache con id $key" }
        return cache[key]
    }

    /**
     * Elimina un personaje de la caché utilizando su ID como clave.
     *
     * @param key ID del personaje a eliminar de la caché.
     * @return Objeto [Personaje] eliminado de la caché, o null si no se encuentra en la caché.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun remove(key: Long): Personaje? {
        logger.debug { "Eliminando personaje en cache con id $key" }
        return cache.remove(key)
    }

    /**
     * Elimina todos los objetos almacenados en la caché.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun clear() {
        logger.debug { "Limpiando cache de Personajes" }
        cache.clear()
    }
}
