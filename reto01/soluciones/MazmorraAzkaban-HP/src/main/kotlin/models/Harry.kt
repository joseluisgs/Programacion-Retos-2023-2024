package models

/**
 * Clase que representa al personaje principal del juego, Harry.
 *
 * @property vida Puntos de vida de Harry.
 * @property fila Fila actual de la posición de Harry.
 * @property columna Columna actual de la posición de Harry.
 */
class Harry(
    var vida: Int = 100,
    var fila: Int = 0,
    var columna: Int = 0
) {
    // Propiedades privadas adicionales para el control interno de Harry
    private var tamPrision = 6
    private val prision = Array(tamPrision) { arrayOfNulls<Any?>(tamPrision) }
    private var horrocruxesDestruidos = 0

    /**
     * Método para mover a Harry hacia arriba en la prisión.
     *
     * @param prision Matriz que representa la prisión.
     */
    fun moverArriba(prision: Array<Array<Any?>>) {
        if (fila > 0) {
            prision[fila][columna] = null
            fila--
            prision[fila][columna] = this
            println("Harry se movió hacia arriba. Nueva posición: (${fila + 1}, ${columna + 1})")
        } else {
            println("No puedes moverte hacia arriba, estás en el límite superior.")
        }
    }

    /**
     * Método para mover a Harry hacia abajo en la prisión.
     *
     * @param prision Matriz que representa la prisión.
     */
    fun moverAbajo(prision: Array<Array<Any?>>) {
        if (fila < tamPrision - 1) {
            prision[fila][columna] = null
            fila++
            prision[fila][columna] = this
            println("Harry se movió hacia abajo. Nueva posición: (${fila + 1}, ${columna + 1})")
        } else {
            println("No puedes moverte hacia abajo, estás en el límite inferior.")
        }
    }

    /**
     * Método para mover a Harry hacia la izquierda en la prisión.
     *
     * @param prision Matriz que representa la prisión.
     */
    fun moverIzquierda(prision: Array<Array<Any?>>) {
        if (columna > 0) {
            prision[fila][columna] = null
            columna--
            prision[fila][columna] = this
            println("Harry se movió hacia la izquierda. Nueva posición: (${fila + 1}, ${columna + 1})")
        } else {
            println("No puedes moverte hacia la izquierda, estás en el límite izquierdo.")
        }
    }

    /**
     * Método para mover a Harry hacia la derecha en la prisión.
     *
     * @param prision Matriz que representa la prisión.
     */
    fun moverDerecha(prision: Array<Array<Any?>>) {
        if (columna < tamPrision - 1) {
            prision[fila][columna] = null
            columna++
            prision[fila][columna] = this
            println("Harry se movió hacia la derecha. Nueva posición: (${fila + 1}, ${columna + 1})")
        } else {
            println("No puedes moverte hacia la derecha, estás en el límite derecho.")
        }
    }

    /**
     * Método para destruir un Horrocruxe. Incrementa el contador de Horrocruxes destruidos.
     */
    fun destruirHorrocruxe() {
        horrocruxesDestruidos++
        println("Horrocruxe destruido. Horrocruxes restantes: ${NUMHORROCRUXES - horrocruxesDestruidos}")
    }
}
