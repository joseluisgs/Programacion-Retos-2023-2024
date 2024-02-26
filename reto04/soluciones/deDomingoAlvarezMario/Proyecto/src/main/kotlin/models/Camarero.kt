package org.example.models

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 *
 * Clase que representa a un camarero en el restaurante.
 *
 * @property j Posición en la matriz del restaurante en la coordenada x.
 * @property i Posición en la matriz del restaurante en la coordenada y.
 * @property direccionMesa Indica la dirección del movimiento del camarero.
 * @property mover Número de pasos que debe moverse el camarero.
 * @property veces Número de veces que el camarero ha atendido a una mesa.
 */
class Camarero(val j: Int, var i: Int) {
    private var direccionMesa = true
    private var mover: Int = 0
    var veces: Int = 0

    /**
     * Realiza la acción del camarero en el restaurante.
     */
    fun accion() {
        if (mover == 0) {
            if (i >= 5) {
                direccionMesa = true
                println("El camarero está recogiendo el plato")
                mover = 2
            } else if (i <= 1) {
                direccionMesa = false
            }
            moverse()
        } else {
            mover--
        }
    }

    private fun moverse() {
        if (i == 3) rata()
        if (direccionMesa) {
            i--
        } else {
            i++
        }
    }

    private fun rata() {
        val random = (1..5).random()
        // si el random es 1 o 2
        if (random == 1) {
            println("Un ratón🐭 ha aparecido y ha asustado al camarero")
            println("El camarero $i tiene que volver a recoger el plato")
            direccionMesa = false
        }
    }

    /**
     * Atiende por primera vez a una mesa.
     *
     * @param mesa Mesa a la que atiende el camarero.
     */
    fun primeraVezMesa(mesa: Mesa) {
        println("Se ha atendido la mesa nº${mesa.i + 1}")
        mesa.menuAleatorio()
        println(
            """la Mesa ${mesa.i + 1} ha elegido el siguiente Menú: 
            |      - ${mesa.menuMesa[0]}
            |      - ${mesa.menuMesa[1]}
            |      - ${mesa.menuMesa[2]}
            |      """.trimMargin()
        )
    }

    /**
     * Sirve el primer plato a la mesa.
     *
     * @param mesa Mesa a la que sirve el primer plato.
     */
    fun primerPlato(mesa: Mesa) {
        println("La Mesa ${mesa.i + 1} tiene su primer plato: ${mesa.menuMesa[0]}")
    }

    /**
     * Sirve el segundo plato a la mesa.
     *
     * @param mesa Mesa a la que sirve el segundo plato.
     */
    fun segundoPlato(mesa: Mesa) {
        println("La Mesa ${mesa.i + 1} tiene su segundo plato: ${mesa.menuMesa[1]}")
    }

    /**
     * Sirve el postre a la mesa.
     *
     * @param mesa Mesa a la que sirve el postre.
     */
    fun postre(mesa: Mesa) {
        println("La Mesa ${mesa.i + 1} tiene su postre: ${mesa.menuMesa[2]}")
    }
}
