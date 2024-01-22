package models

import java.time.LocalTime

interface Piloto {
    val nombre:String

    var tiempoInicio: LocalTime
    var tiempoFinal: LocalTime
    var tiempoFinalComprobada:Boolean


    val fila:Int
    var columna:Int
    fun moverPiloto(){
        columna++
    }

    var vuelta:Int
    var isTerminada:Boolean

    var isDnf:Boolean
    val dnf:Int
    var accidente:Boolean
    fun accidenteF():Boolean

    var tiempoPitstop:Int
    fun pitStop():Int{
        var tiempo=0
        if (columna==4){
            tiempo=(1..3).random()
            return tiempo
        }
        return tiempo
    }
}