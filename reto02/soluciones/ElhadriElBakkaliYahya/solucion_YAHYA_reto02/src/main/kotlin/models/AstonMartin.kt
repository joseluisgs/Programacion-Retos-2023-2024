package models

abstract class AstonMartin: Equipos() {

    var isMalaEstrategia=false
    var tiempoMalaEstrategia=0
    fun malaEstrategia():Boolean{
        if ((0..100).random()<2)return true
        return false
    }
}