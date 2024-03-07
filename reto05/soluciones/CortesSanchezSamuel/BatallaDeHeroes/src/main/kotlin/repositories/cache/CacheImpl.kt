package repositories.cache

import config.Config.cacheSize

class CacheImpl<T,KEY> : Cache<T,KEY> {

    private val cache = mutableMapOf<KEY,T>()

    override fun get(key: KEY): T? {
        return cache[key]
    }

    override fun put(key: KEY, value: T) {
        if(cache.size >= cacheSize && !cache.containsKey(key)){
            val firstKey = cache.keys.first()
            cache.remove(firstKey)
        }
        cache[key] = value
    }

    override fun remove(key: KEY) {
        cache.remove(key)
    }

    override fun clear() {
        cache.clear()
    }

}