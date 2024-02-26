package org.example.models.personajes

/**
 * Clase abstracta que representa un personaje en el restaurante.
 */
abstract class Personaje(
    var fila: Int,
    var columna: Int,
    var continuar: Int
) {

    /**
     * Método abstracto que posiciona al personaje en el restaurante.
     *
     * @param restaurante El restaurante en el que se encuentra el personaje.
     * @return Un array con la posición del personaje en el restaurante.
     */
    abstract fun posicionar(restaurante: Array<Array<Any?>>): Array<Int>
}