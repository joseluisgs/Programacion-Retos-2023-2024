package org.example.repositories.colas

/**
 * Interfaz genérica que representa una cola.
 *
 * @param T Tipo genérico de los elementos de la cola.
 */
interface Queue<T> {

    /**
     * Agrega un elemento al final de la cola.
     *
     * @param element Elemento a agregar a la cola.
     */
    fun enqueue(element: T)

    /**
     * Retira y retorna el elemento al inicio de la cola.
     *
     * @return El elemento al inicio de la cola o `null` si la cola está vacía.
     */
    fun dequeue(): T?

    /**
     * Devuelve el elemento al inicio de la cola sin retirarlo.
     *
     * @return El elemento al inicio de la cola o `null` si la cola está vacía.
     */
    fun peek(): T?

    /**
     * Verifica si la cola está vacía.
     *
     * @return `true` si la cola está vacía, `false` en caso contrario.
     */
    fun isEmpty(): Boolean

    /**
     * Retorna el tamaño de la cola.
     *
     * @return El tamaño de la cola.
     */
    fun size(): Int
}