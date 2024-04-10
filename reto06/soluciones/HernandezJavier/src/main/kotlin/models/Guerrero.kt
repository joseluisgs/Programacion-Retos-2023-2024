package org.example.models

class Guerrero(
    nombre:String,
    habilidad:String,
    ataque:Int,
    edad:Int,
    arma:String,
    isDeleted: Boolean? = false
):Personaje(nombre,habilidad,ataque,edad,arma,isDeleted) {
    override fun toString(): String {
        return "Guerrero (nombre: $nombre, edad: $edad, ataque: $ataque, habilidad: $habilidad, arma: $arma)"
    }
}