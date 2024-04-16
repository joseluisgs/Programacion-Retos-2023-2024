package org.example.cache
import org.example.config.Config
import org.lighthousegames.logging.logging

/**
 * Clase que implementa una caché genérica en un mapa
 */
private val log = logging()

class CacheImpl<T,KEY> (
    val size: Int = Config.cacheSize
) : Cache<T,KEY> {

    private val cache = mutableMapOf<KEY,T>()

    override fun get(key: KEY): T? {
        log.debug { "Elemento obtenido de caché, clave: $key" }
        return cache[key]
    }

    override fun put(key: KEY, value: T) {
        log.debug { "Elemento guardado en caché, clave: $key" }
        if (cache.size >= size && !cache.containsKey(key)) {
            val firstKey = cache.keys.first()
            log.debug { "Eliminando valor de la cache por clave $firstKey" }
            cache.remove(firstKey)
        }
        cache[key] = value
    }

    override fun remove(key: KEY) {
        log.debug { "Elemento eliminado de caché, clave: $key" }
        cache.remove(key)
    }

    override fun clear() {
        log.debug { "Limpiando la caché" }
        cache.clear()
    }

}