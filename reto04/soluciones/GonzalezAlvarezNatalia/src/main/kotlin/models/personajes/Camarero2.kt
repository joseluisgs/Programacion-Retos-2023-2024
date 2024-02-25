package org.example.models.personajes

import org.lighthousegames.logging.logging

/**
 * Clase que representa al camarero 2 en el restaurante.
 *
 * @property fila La fila en la que se encuentra el camarero.
 * @property columna La columna en la que se encuentra el camarero.
 * @property continuar El número de segundos que para el camarero.
 */
class Camarero2(
    fila: Int = 4,
    columna: Int = 1,
    continuar: Int = 0
) : Personaje(fila, columna, continuar), Camareros {

    /**
     * Variable que indica si el camarero está en movimiento.
     */
    private var movimiento: Boolean = true

    /**
     * Método que mueve al camarero en el restaurante.
     */
    private fun moverCamarero() {
        if (movimiento) {
            fila--
            if (fila == 1) movimiento = false
        } else {
            fila++
            if (fila == 4) movimiento = true
        }
    }

    /**
     * Método que mueve al camarero en el restaurante.
     *
     * @param restaurante El restaurante en el que se encuentra el camarero.
     */
    override fun mover(restaurante: Array<Array<Any?>>) {
        if (posicionValida(restaurante)) {
            restaurante[fila][columna] = null
            moverCamarero()
            restaurante[fila][columna] = this
        }
    }

    /**
     * Método que simula que el camarero se cae en el restaurante.
     *
     * @param restaurante El restaurante en el que se encuentra el camarero.
     */
    override fun caer(restaurante: Array<Array<Any?>>) {
        if (posicionValida(restaurante)) {
        }
    }

    /**
     * Método que simula que el camarero pide un plato en el restaurante.
     *
     * @param restaurante El restaurante en el que se encuentra el camarero.
     */
    override fun pedirPlato(restaurante: Array<Array<Any?>>) {
        continuar = 5
        logging().info { "El camarero pide el plato" }
    }

    /**
     * Método que verifica si la posición del camarero es válida en el restaurante.
     *
     * @param restaurante El restaurante en el que se encuentra el camarero.
     * @return `true` si la posición es válida, `false` en caso contrario.
     */
    override fun posicionValida(restaurante: Array<Array<Any?>>): Boolean {
        return fila in restaurante.indices && columna in restaurante[fila].indices
    }

    /**
     * Método que posiciona al camarero en el restaurante.
     *
     * @param restaurante El restaurante en el que se encuentra el camarero.
     * @return Un array con la posición del camarero en el restaurante.
     */
    override fun posicionar(restaurante: Array<Array<Any?>>): Array<Int> {
        for (i in restaurante.indices) {
            for (j in restaurante[i].indices) {
                restaurante[fila][columna] = this
            }
        }
        return arrayOf(fila, columna)
    }
}