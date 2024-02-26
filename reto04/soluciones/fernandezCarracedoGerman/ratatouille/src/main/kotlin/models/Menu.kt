package org.example.models

private const val PRECIO_MENU = 10.50

/**
 * Clase Menu.
 * Sus instancias se crean a través del factory MenuFactory
 */

class Menu (
    val primero:String="",
    val segundo:String="",
    val postre:String="",
    val valoracion:Int=5
) {
    // accesible solo para leer
    var precio: Double = PRECIO_MENU
        private set

    // accesible solo para leer
    var id: Int
        private set

    init {
        this.id = ++contador
    }

    companion object {
        private var contador = 0
    }

    override fun toString(): String {
        return "Menú nº $id [$precio € | $valoracion p.] => Primero: $primero | Segundo: $segundo | Postre: $postre"
    }
}