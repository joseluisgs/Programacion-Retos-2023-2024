package org.example.services.cache.personajes

import org.example.config.Config
import PersonajeDto
import org.example.services.cache.base.Cache
import org.lighthousegames.logging.logging

private val logger = logging()

class PersonajesCache : Cache<PersonajeDto, Long> {
    private val cache: MutableMap<Long, PersonajeDto> = mutableMapOf()
    override fun put(key: Long, value: PersonajeDto) {
        logger.debug { "Guardando personaje en cache con id $key" }
        // Es de tamaño fijo, si se llena, eliminamos el primero
        if (cache.size >= Config.cacheSize && !cache.containsKey(key)) {
            val firstKey = cache.keys.first()
            logger.debug { "Eliminando personaje en cache con id $firstKey porque está llena" }
            cache.remove(firstKey)
        }
        cache[key] = value
    }

    override fun get(key: Long): PersonajeDto? {
        logger.debug { "Obteniendo personaje en cache con id $key" }
        return cache[key]
    }

    override fun remove(key: Long): PersonajeDto? {
        logger.debug { "Eliminando personaje en cache con id $key" }
        return cache.remove(key)
    }

    override fun clear() {
        logger.debug { "Limpiando cache de Personajes" }
        cache.clear()
    }
}