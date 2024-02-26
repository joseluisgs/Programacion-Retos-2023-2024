package org.example.models

import org.example.models.order.Order
import java.util.*

class Kitchen {
    val chef = Chef()
    private val platesReady : MutableMap<UUID,Order> = mutableMapOf()

    /**
     * Devuelve un plato si está listo y lo borra de la lista de platos listos, sino devuelve null
     */
    fun takeOrder(uuid: UUID) : Order? {
        return platesReady[uuid].also { platesReady.remove(uuid) }
    }

    /**
     * Si el chef ha terminado con un pedido, lo añade a la lista, sino le baja el tiempo
     */
    fun checkForNewFinishedOrders(){
        if (chef.busyWith != null){
            if (chef.timeLeft == 0){
                platesReady[chef.busyWith!!.uuid] = chef.busyWith!!
                chef.busyWith = null
            }else chef.timeLeft--
        }
    }

}