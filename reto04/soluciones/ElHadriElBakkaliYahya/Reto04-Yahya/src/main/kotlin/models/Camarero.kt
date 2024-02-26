package models

class Camarero(val nombre:String, var fila:Int, val columna:Int):IntegrantesRestaurante {

    fun moverAMesa(){
        fila--
    }

    fun moverACocina(){

            fila++

    }
    var atendido= false
    var pedidoEntregado=false
}