package org.example.models

open class Ferrari: Parrilla() {

    fun malaEstrategia(): Boolean{
        var posCagarla=15
        if((1..100).random() < posCagarla){
            return true
            println("Otra vez que intentan estrategias fuera de Monza")
        }else{
            return false
        }
    }

    fun falloFiabilidad(): Boolean{
        var posCagarla=25
        if((1..100).random() < posCagarla){
            return true
            println("Vaya que chorprecha Ferrari tiene que volver a parar por problemas con su coche")
        }else{
            return false
        }

    }


}