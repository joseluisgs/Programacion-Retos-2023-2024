package models

class Cocina(val fila:Int, val columna:Int):IntegrantesRestaurante {
    private var tiempoCocina=3
    var cocinado=false

    fun cocinar(camarero: Camarero) {
        println(" PEDIDO EN COCINA")
        tiempoCocina--

        if (tiempoCocina==0){
            cocinado=true
            println("${camarero.nombre} PLATO LISTO")
        }
    }
    fun resetCocina(){
        tiempoCocina=3
        cocinado=false
    }
}