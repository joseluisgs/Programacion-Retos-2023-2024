package services.cache

interface Cache<T, KEY> {
    fun put(key: KEY, value: T)
    fun get(key: KEY): T?
    fun remove(key: KEY): T?
    fun clear()
}