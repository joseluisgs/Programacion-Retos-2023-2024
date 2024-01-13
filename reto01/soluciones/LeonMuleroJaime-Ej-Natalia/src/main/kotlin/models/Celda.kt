package models

/**
 * Clase con las celdas que rellenan el tablero
 * @property isVisited Marca si la celda ha sido visitada
 * @property personaje Personaje que ocupa la casilla. Si no hay es null
 * @author Jaime Leon Mulero
 * @since 1.0.0
 */
open class Celda (
    var isVisited: Boolean = false,
    var personaje: Any? = null
) {
}