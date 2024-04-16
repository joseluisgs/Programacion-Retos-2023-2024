package models

open class Heroe(
    nombre: String,
    habilidad: String,
    ataque : Int,
    edad : Int,
    arma : String
) : Personaje(nombre,habilidad,ataque,edad,arma)