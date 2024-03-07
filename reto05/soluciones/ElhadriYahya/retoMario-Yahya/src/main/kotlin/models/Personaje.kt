package models

import kotlinx.serialization.Serializable

@Serializable
data class Personaje(
    val id:Int,
    val nickName:String,
    val nombre:String,
    val edad:Int,
    val vivo:Boolean,
    val villano:Boolean,
    val habilidad:String,
    val puntosCombate:Int
){
    override fun toString(): String {
        return "$id - $nickName/$nombre - $edad - $habilidad- $puntosCombate"
    }
}