package org.example.services.cache.base

import org.example.services.cache.errors.CacheError
import com.github.michaelbull.result.Result
interface Cache<K, T> {
    fun get(key: K): Result<T, CacheError>
    fun put(key: K, value: T): Result<T, Nothing>
    fun remove(key: K): Result<T, CacheError>
    fun clear(): Result<Unit, Nothing>
}