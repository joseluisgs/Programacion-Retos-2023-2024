package models

class Enemigo(
    nombre:String,
    habilidad:String,
    ataque:Int,
    edad:Int,
    arma:String,
    isDeleted: Boolean? = false
):Personaje(nombre,habilidad,ataque,edad,arma,isDeleted){
    override fun toString(): String {
        return "Enemigo $nombre - $edad - $ataque~"
    }
}