package models

class Camarero (val nombre: String, var fila: Int, val columna: Int): IntegrantesRestaurante {

    fun moverMesa(){
        while(fila != 1) {
            fila--
        }
    }

    fun moverCocina(){
        while (fila != 5){
            fila++
        }
    }

     var atendido = false
    var pedidoEntregado =false
}