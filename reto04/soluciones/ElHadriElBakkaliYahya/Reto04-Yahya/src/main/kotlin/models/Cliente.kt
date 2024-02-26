package models

class Cliente(val nombre:String, var mesa:Mesa?) {

    var marcado=false
    var cuenta=0
    var menu:Menu?=null

    var sentado=false
    var notaPedida=false
    var comidaEnMesa=false

    var terminado=false
    private var tiempoEspera=0

    fun tiempoDeEspera(){

            tiempoEspera++
            if (tiempoEspera == 15) println("Donde esta mi menu")
            if (tiempoEspera == 20) println("DONDE C*** ESTA MI MENU")

    }
    private var tiempoComida=5
    fun comer(){
        println("CLIENTE ${nombre} COMIENDO")
        tiempoComida--
        if (tiempoComida==0)terminado=true
    }
    fun seleccionMenu():Int{
        return (1..5).random()
    }
}