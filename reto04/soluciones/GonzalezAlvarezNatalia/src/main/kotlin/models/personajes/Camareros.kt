package org.example.models.personajes

/**
 * Interfaz que representa los camareros del restaurante.
 */
interface Camareros {

    /**
     * Método que mueve al camarero en el restaurante.
     *
     * @param restaurante El restaurante en el que se encuentra el camarero.
     */
    fun mover(restaurante: Array<Array<Any?>>)

    /**
     * Método que simula que el camarero se cae en el restaurante.
     *
     * @param restaurante El restaurante en el que se encuentra el camarero.
     */
    fun caer(restaurante: Array<Array<Any?>>)

    /**
     * Método que simula que el camarero pide un plato en el restaurante.
     *
     * @param restaurante El restaurante en el que se encuentra el camarero.
     */
    fun pedirPlato(restaurante: Array<Array<Any?>>)

    /**
     * Método que verifica si la posición del camarero es válida en el restaurante.
     *
     * @param restaurante El restaurante en el que se encuentra el camarero.
     * @return `true` si la posición es válida, `false` en caso contrario.
     */
    fun posicionValida(restaurante: Array<Array<Any?>>): Boolean
}