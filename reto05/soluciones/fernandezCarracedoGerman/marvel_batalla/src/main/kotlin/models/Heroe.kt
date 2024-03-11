package org.example.models

/**
 * Clase Heroe, con los datos que lo representan:
 * @property ID,,Nombre,Edad,Vivo,Villano,Habilidad,PC
 * @property nickName Nick del Héroe
 * @property nombre Nombre del Héroe
 * @property edad Edad del Héroe
 * @property vivo Boolean: true si el Héroe está vivo, false en caso contrario
 * @property villano Boolean: true si el Héroe es un villano, false en caso contrario
 * @property habilidad Habilidad especial del Héroe
 * @property puntosCombate Puntos de combate del Héroe
 */
data class Heroe(
    val id:Int,
    val nickName:String,
    val nombre:String,
    val edad:Int,
    val vivo:Boolean,
    val villano:Boolean,
    val habilidad:String,
    val puntosCombate:Int
)