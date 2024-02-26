package org.example.models

/**
 * Clase Comanda
 * Una comanda está compuesta por un número de cliente y el número de menu que solicita
 * Tiene además, un contador de tiempo restante de cocinado y si se ha desperdiciado por algún motivo
 */
private const val TIEMPO_COCINADO_COMANDA = 3

class Comanda private constructor(
    val numCliente:Int,
    val numMenu:Int
) {
    var tiempoRestanteCocinar:Int = 0
    var perdida:Boolean = false  // si se ha desperdiciado por algún motivo

    // accesible solo para leer
    var id: Int
        private set

    // campo calculado, accesible solo para leer
    var terminada:Boolean = false
        get() = tiempoRestanteCocinar == 0
        private set

    init {
        this.id = ++contador
        tiempoRestanteCocinar = TIEMPO_COCINADO_COMANDA
    }

    companion object {
        private var contador = 0

        /**
         * Las comandas se crearán a través de este método, y no mediante el constructor de la clase
         */
        fun crearComanda(numCliente:Int, numMenu:Int):Comanda {
            return Comanda(numCliente,numMenu)
        }
    }

    override fun toString(): String {
        return "Comanda nº $id => Cliente $numCliente | Menú $numMenu"
    }

}