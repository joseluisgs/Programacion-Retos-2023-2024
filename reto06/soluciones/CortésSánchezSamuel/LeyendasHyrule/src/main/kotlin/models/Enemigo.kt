package models

open class Enemigo(
    nombre: String,
    habilidad: String,
    ataque : Int,
    edad : Int,
    arma : String
) : Personaje(nombre,habilidad,ataque,edad,arma) {

    fun otorgarNaturalezaMaligna(){
        println(",.,.,.,.,.,.OTORGANDO NATURALEZA MALIGNA,.,.,.,.,.,.")
    }

}