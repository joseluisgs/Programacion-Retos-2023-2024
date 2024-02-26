package org.example.models

class Camareros(
    var fila: Int,
    val columna: Int,
    var comandaRecibida: Boolean = false,
    var platoRecibido: Boolean = false,
    var platoEntregado: Boolean = false,
    var comensalSatisfecho: Boolean = false,
): Colocarse {
    override fun colocar(restaurante: Array<Array<Any?>>): Array<Array<Any?>> {
        restaurante[this.fila][this.columna] = this
        return restaurante
    }

    private fun moverse(restaurante: Array<Array<Any?>>, mesa: Mesas, cocinero: Cocineros): Array<Array<Any?>> {
        var nuevaFila = this.fila
        if(!mesa.free && this.fila != 1 && !comensalSatisfecho && !comandaRecibida || platoRecibido && !platoEntregado){
            println("Voy a por la mesa 1")
            restaurante[this.fila][this.columna] = null
            nuevaFila--
            this.fila = nuevaFila
            restaurante[fila][columna] = this
            return restaurante
        }else if(comandaRecibida && this.fila != 4 && !platoRecibido || platoEntregado){
            restaurante[this.fila][this.columna] = null
            nuevaFila++
            this.fila = nuevaFila
            restaurante[this.fila][this.columna] = this
            return restaurante
        }
        return restaurante
    }

    fun trabajoCamarero(restaurante: Array<Array<Any?>>, mesa: Mesas, cocinero: Cocineros){
        if(this.fila == 1 && !comandaRecibida) {
            tomarComanda()
        }
        cocinero.prepararPlato(camarero = this)
        if(this.fila == 1 && platoRecibido){
            mesa.recibirMenu(this)
        }
        if(fila == 4 && mesa.free){
            println("Esperando cliente")
        }
        moverse(restaurante, mesa, cocinero)
    }

    private fun tomarComanda(){
        val random = (1..3).random()
        println("Buenas caballero, que desea tomar?")
        println("Quisiera el menu número $random por favor")
        println("Marchando")
        this.comandaRecibida = true
    }
}