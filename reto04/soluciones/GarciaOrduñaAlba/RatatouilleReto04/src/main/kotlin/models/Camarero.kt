package org.example.models
/**
 * La clase Camarero representa al personal encargado de servir platos y atender a las mesas en el restaurante.
 * @property j La posición horizontal del camarero en la matriz del restaurante.
 * @property i La posición vertical del camarero en la matriz del restaurante.
 * @property direccionMesa Indica la dirección del movimiento del camarero hacia las mesas.
 * @property mover Contador utilizado para determinar el movimiento del camarero.
 * @property veces Contador que registra el número de veces que el camarero realiza una acción en su posición.
 */
class Camarero(val j: Int, var i: Int) {
    private var direccionMesa = true // Indica la dirección del movimiento del camarero hacia las mesas
    private var mover: Int = 0 // Contador utilizado para determinar el movimiento del camarero
    var veces: Int = 0 // Contador que registra el número de veces que el camarero realiza una acción en su posición

    /**
     * Lleva los platos pedidos por los clientes a la mesa correspondiente.
     * @param mesa La mesa a la que se llevan los platos.
     */
    fun servirPlatos(mesa: Mesa) {
        val platos = mesa.platosPedidos.joinToString(", ")
        println("El camarero está llevando los platos a la mesa ${mesa.numero + 1}: $platos")
        mesa.platosPedidos = emptyList()
    }

    /**
     * Realiza la acción del camarero en su posición, que puede ser moverse o enfrentarse a una situación con una rata.
     */
    fun accion() {
        when {
            mover == 0 -> {
                if (i >= 6) {
                    direccionMesa = true
                    println("El camarero está recogiendo la comida🥞")
                    mover = 1
                } else if (i <= 1) {
                    direccionMesa = false
                }
                moverse()
            }
            else -> mover--
        }
    }

    /**
     * Mueve al camarero hacia arriba o abajo, dependiendo de la dirección de la mesa y enfrenta la situación de la rata.
     */
    private fun moverse() {
        i = if (direccionMesa) {
            if (i == 3) rata()
            i - 1
        } else {
            if (i == 3) rata()
            i + 1
        }
    }

    /**
     * Simula la situación en la que una rata aparece y asusta al camarero, haciendo que deje caer la comida.
     */
    private fun rata() {
        val random = (1..5).random()
        if (random == 1) {
            println("¡Una rata ha aparecido y ha asustado al camarero🐀🐀!")
            println("El camarero ha dejado caer la comida y tiene que volver a traerla❌")
            direccionMesa = false
        }
    }
}