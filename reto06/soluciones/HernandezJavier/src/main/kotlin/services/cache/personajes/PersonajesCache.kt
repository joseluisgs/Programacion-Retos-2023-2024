package org.example.services.cache.personajes

import org.example.config.Config
import org.example.models.Personaje
import org.example.services.cache.base.Cache
import org.lighthousegames.logging.logging

private val logger = logging()
class PersonajesCache: Cache<Personaje, String> {
    private val cache: MutableMap<String, Personaje> = mutableMapOf()
    override fun put(key: String, value: Personaje) {
        logger.debug { "Guardando personaje en la cache con key $key" }
        if(cache.size >= Config.cacheSize && !cache.containsKey(key)){
            val firstKey = cache.keys.first()
            logger.debug { "Eliminando personaje en cache con id $firstKey debido a que está llena" }
            cache.remove(firstKey)
        }
        cache[key] = value
    }

    override fun get(key: String): Personaje? {
        logger.debug { "Obteniendo personaje de la cache con key $key" }
        return cache[key]
    }

    override fun remove(key: String): Personaje? {
        logger.debug { "Eliminando personaje de la cache con key $key" }
        return cache.remove(key)
    }

    override fun clear() {
        logger.debug { "Limpiando la cache de Personajes" }
        cache.clear()
    }
}