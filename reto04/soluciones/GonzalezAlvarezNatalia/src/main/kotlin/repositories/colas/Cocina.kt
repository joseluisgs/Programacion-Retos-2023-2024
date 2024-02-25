package org.example.repositories.colas

import org.example.models.Menu

/**
 * Clase que representa la cocina del restaurante.
 */
class Cocina : Queue<Menu> {

    /**
     * Lista mutable que almacena los menús en la cola de la cocina.
     */
    private val elementos = mutableListOf<Menu>()

    /**
     * Método que posiciona a la cocina en el restaurante.
     *
     * @param restaurante Matriz que representa el restaurante.
     */
    fun posicionar(restaurante: Array<Array<Any?>>) {
        val fila: Int = 5
        val columna: Int = 1

        for (i in restaurante.indices) {
            for (j in restaurante[i].indices) {
                restaurante[fila][columna] = this
            }
        }
    }

    /**
     * Agrega un menú a la cola de la cocina.
     *
     * @param element Menú a agregar a la cola.
     */
    override fun enqueue(element: Menu) {
        elementos.add(element)
    }

    /**
     * Retira el menú al inicio de la cola de la cocina.
     *
     * @return El menú al inicio de la cola o `null` si la cola está vacía.
     */
    override fun dequeue(): Menu? {
        return if (elementos.isNotEmpty())
            elementos.removeAt(0)
        else null
    }

    /**
     * Devuelve el menú al inicio de la cola de la cocina sin retirarlo.
     *
     * @return El menú al inicio de la cola o `null` si la cola está vacía.
     */
    override fun peek(): Menu? {
        return if (elementos.isNotEmpty())
            elementos[0]
        else null
    }

    /**
     * Verifica si la cola de la cocina está vacía.
     *
     * @return `true` si la cola está vacía, `false` en caso contrario.
     */
    override fun isEmpty(): Boolean {
        return elementos.isEmpty()
    }

    /**
     * Retorna el tamaño de la cola de la cocina.
     *
     * @return El tamaño de la cola.
     */
    override fun size(): Int {
        return elementos.size
    }
}