package org.example.models

import org.example.models.order.Order
import java.util.LinkedList
import java.util.Queue

data class Chef (
    var busyWith : Order? = null,
    var timeLeft : Int = 0
){
    private var queue : Queue<Order> = LinkedList()

    /**
     * Si no está ocupado con ningún pedido, lo empieza ha hacer, sino lo pone en una cola
     */
    fun addWorkIfPossible(order: Order) {
        if (busyWith == null){
            busyWith = order
            timeLeft = 2
        }
        else queue.add(order)
    }

    /**
     * Mira en la cola cuando ha terminado algo, lo empieza a hacer y lo borra
     */
    fun tryToPrepareaQueuedPlate(){
        if (queue.isNotEmpty() && busyWith == null){
            busyWith = queue.peek()
            timeLeft = 2
            queue.remove()
        }
    }
}