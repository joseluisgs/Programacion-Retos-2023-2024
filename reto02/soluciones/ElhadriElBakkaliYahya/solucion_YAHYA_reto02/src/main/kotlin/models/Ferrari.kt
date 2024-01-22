package models

abstract class Ferrari: Equipos() {

    var isMalaEstrategia=false
    var isProblemasDeFiabilidad=false
    var tiempoMalaEstrategia=0
    fun malaEstrategia():Boolean{
        if ((0..100).random()<2)return true
        return false
    }

    fun problemasFiabilidad():Boolean{
        if ((0..100).random()<3)return true
        return false
    }
}