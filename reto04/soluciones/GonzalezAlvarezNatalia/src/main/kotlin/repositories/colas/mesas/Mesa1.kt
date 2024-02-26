package org.example.repositories.colas.mesas

import org.example.models.Cliente
import org.example.repositories.colas.Queue

/**
 * Clase que representa la mesa 1 del restaurante.
 *
 * @property fila Fila en la que se encuentra la mesa.
 * @property columna Columna en la que se encuentra la mesa.
 */
class Mesa1(
    private var fila: Int = 0,
    private val columna: Int = 0
) : Queue<Cliente> {

    /**
     * Lista mutable que almacena a los clientes sentados en la mesa.
     */
    private val mesa1 = mutableListOf<Cliente>()

    /**
     * Método que posiciona a la mesa en el restaurante.
     *
     * @param restaurante Matriz que representa el restaurante.
     * @return Un array con la fila y columna de la mesa.
     */
    fun posicionar(restaurante: Array<Array<Any?>>): Array<Int> {
        for (i in restaurante.indices) {
            for (j in restaurante[i].indices) {
                restaurante[fila][columna] = this
            }
        }
        return arrayOf(fila, columna)
    }

    /**
     * Agrega un cliente a la cola de la mesa.
     *
     * @param element Cliente a agregar a la cola.
     */
    override fun enqueue(element: Cliente) {
        mesa1.add(element)
    }

    /**
     * Retira el cliente al inicio de la cola de la mesa.
     *
     * @return El cliente al inicio de la cola o `null` si la cola está vacía.
     */
    override fun dequeue(): Cliente? {
        return if (mesa1.isNotEmpty())
            mesa1.removeAt(0)
        else null
    }

    /**
     * Devuelve el cliente al inicio de la cola de la mesa sin retirarlo.
     *
     * @return El cliente al inicio de la cola o `null` si la cola está vacía.
     */
    override fun peek(): Cliente? {
        return if (mesa1.isNotEmpty())
            mesa1[0]
        else null
    }

    /**
     * Verifica si la cola de la mesa está vacía.
     *
     * @return `true` si la cola está vacía, `false` en caso contrario.
     */
    override fun isEmpty(): Boolean {
        return mesa1.isEmpty()
    }

    /**
     * Retorna el tamaño de la cola de la mesa.
     *
     * @return El tamaño de la cola.
     */
    override fun size(): Int {
        return mesa1.size
    }
}