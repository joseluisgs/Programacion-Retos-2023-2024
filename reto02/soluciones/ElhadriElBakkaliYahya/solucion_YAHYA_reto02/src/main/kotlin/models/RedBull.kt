package models

abstract class RedBull: Equipos() {
    var isRapida=false
     fun vueltaRapida():Boolean{
        if ((0..100).random()<2)return true
        return false
    }
}