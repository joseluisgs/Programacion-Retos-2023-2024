package models

/**
 * Harry, el protagonista y el personaje que controlamos
 * @property nombre Harry
 * @property vida la cantidad de vida que tiene Harry
 */
open class Harry(nombre: String ="Harry", vida: Int = 100) : Aliado(nombre, vida) {
    var fila: Int = 0
    var columna: Int = 0
}