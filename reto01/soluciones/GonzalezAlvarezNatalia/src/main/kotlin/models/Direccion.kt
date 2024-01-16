package org.example.models

/**
 * Clase que representa las diferentes direcciones en las que se puede mover el jugador.
 */
class Direccion {

    /**
     * Función que mueve al jugador por la matriz del juego.
     *
     * @param posicion variable que almacena la posición de la clase Potter.
     * @param opcion variable que registra la dirección a la que el jugador decidió moverse.
     * @author Natalia González
     * @return vector con la nueva posición del jugador.
     * @since 1.0-SNAPSHOT
     */
    fun mover(opcion: String, posicion: Array<Int>): Array<Int> {

        val nuevaPosicion = arrayOf(posicion[0], posicion[1])

        when (opcion) {
            "W" -> nuevaPosicion[0] = posicion[0] - 1
            "S" -> nuevaPosicion[0] = posicion[0] + 1
            "D" -> nuevaPosicion[1] = posicion[1] + 1
            "A" -> nuevaPosicion[1] = posicion[1] - 1
        }

        return nuevaPosicion

    }
}