package org.example.repositories

interface Cola<T> {
    fun queue(elem: T)

    fun dequeue(): T

    fun isEmpty(): Boolean

    fun size(): Int

    fun first(): T

    operator fun get(index: Int): T

    fun toList():List<T>

    fun remove(elem:T)
}
