package org.example.services.cache.personajes

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.config.Config
import org.example.models.Personaje
import org.example.services.cache.base.Cache
import org.example.services.cache.errors.CacheError
import org.lighthousegames.logging.logging

private val logger = logging()
class PersonajesCache(
    private val size: Int
): Cache<String, Personaje> {
    private val cache = mutableMapOf<String, Personaje>()
    override fun get(key: String): Result<Personaje, CacheError> {
        logger.debug { "Obteniendo personaje de la cache" }
        return if(cache.containsKey(key)){
            Ok(cache.getValue(key))
        }else{
            Err(CacheError("No existe el personaje en la cache"))
        }
    }

    override fun put(key: String, value: Personaje): Result<Personaje, Nothing> {
        logger.debug { "Guardando personaje en la cache" }
        if(cache.size >= size && cache.containsKey(key)){
            logger.debug { "Eliminando valor de la cache" }
            cache.remove(cache.keys.first())
        }
        cache[key] = value
        return Ok(value)
    }

    override fun remove(key: String): Result<Personaje, CacheError> {
        logger.debug { "Eliminando personaje de la cache" }
        return if(cache.containsKey(key)){
            Ok(cache.remove(key)!!)
        }else{
            Err(CacheError("No existe el personaje en la cache"))
        }
    }

    override fun clear(): Result<Unit, Nothing> {
        logger.debug { "Limpiando los registros de la cache" }
        cache.clear()
        return Ok(Unit)
    }
}