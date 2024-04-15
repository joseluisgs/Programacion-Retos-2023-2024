package org.example.services.cache.personajes

import org.example.config.Config
import org.example.models.Personaje
import org.example.services.cache.base.Cache
import org.lighthousegames.logging.logging

private val logger = logging()

class PersonajesCache: Cache<Personaje, Int> {
    private val cache: MutableMap<Int, Personaje> = mutableMapOf()

    override fun put(key: Int, value: Personaje) {
        logger.debug { "Guardando personaje en cache con id $key" }
        if (cache.size >= Config.cacheSize && !cache.containsKey(key)) {
            val firstKey = cache.keys.first()
            logger.debug { "Eliminando personaje en cache con id $firstKey por tamaño lleno" }
            cache.remove(firstKey)
        }
        cache[key] = value
    }

    override fun get(key: Int): Personaje? {
        logger.debug { "Obteniendo personaje en cache con id $key" }
        return cache[key]
    }

    override fun remove(key: Int): Personaje? {
        logger.debug { "Eliminando personaje en cache con id $key" }
        return cache.remove(key)
    }

    override fun clear() {
        logger.debug { "Limpiando cache de personajes" }
        cache.clear()
    }
}