package models

abstract class Mercedes: Equipos() {

    var isSaftyCar=false
    fun safteyCar():Boolean{
        if ((0..100).random()<3)return true
        return false
    }
}