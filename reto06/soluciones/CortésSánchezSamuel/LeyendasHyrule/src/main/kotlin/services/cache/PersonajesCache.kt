package services.cache

import config.Config
import models.Personaje


class PersonajesCache : Cache<Personaje, String> {
    private val cache: MutableMap<String, Personaje> = mutableMapOf()


    override fun put(key: String, value: Personaje) {
        if (cache.size >= Config.cacheSize && !cache.containsKey(key)) {
            val firstKey = cache.keys.first()
            cache.remove(firstKey)
        }
        cache[key] = value
    }

    override fun get(key: String): Personaje? {
        return cache[key]
    }

    override fun remove(key: String): Personaje? {
        return cache.remove(key)
    }

    override fun clear() {
        cache.clear()
    }
}