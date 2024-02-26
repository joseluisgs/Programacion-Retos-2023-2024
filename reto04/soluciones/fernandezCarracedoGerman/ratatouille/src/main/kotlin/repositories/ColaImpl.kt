package org.example.repositories

class ColaImpl<T>: Cola<T> {

    private val cola:ArrayDeque<T> = ArrayDeque()

    override fun queue(elem: T) {
        cola.addLast(elem)
    }

    override fun dequeue(): T {
        return cola.removeFirst()
    }

    override fun isEmpty(): Boolean {
        return cola.isEmpty()
    }

    override fun size(): Int {
        return cola.size
    }

    override fun first(): T {
        return cola.first()
    }

    override fun get(index: Int): T {
        return cola.get(index)
    }

    override fun toList():List<T>{
        return cola.toList()
    }

    override fun remove(elem:T) {
        cola.remove(elem)
    }
}