package org.example.services.cache.base

import PersonajeDto

interface Cache<T, KEY> {
    fun put(key: KEY, value: PersonajeDto)
    fun get(key: KEY): T?
    fun remove(key: KEY): T?
    fun clear()
}