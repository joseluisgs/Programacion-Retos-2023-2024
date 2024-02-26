package org.example.models

class Cocineros(val fila: Int, val columna: Int, var tiempoPreparacion: Int = 3): Colocarse {
    override fun colocar( restaurante: Array<Array<Any?>>): Array<Array<Any?>> {
        restaurante[this.fila][this.columna] = this
        return restaurante
    }

    fun prepararPlato(camarero: Camareros){
       if(camarero.comandaRecibida && camarero.fila == 4){
           println("Marchando la comanda")
           if(tiempoPreparacion != 0){
               tiempoPreparacion--
           }
           if(tiempoPreparacion == 0){
               println("Plato listo")
               camarero.platoRecibido = true
               tiempoPreparacion = 3
           }
       }
    }
}