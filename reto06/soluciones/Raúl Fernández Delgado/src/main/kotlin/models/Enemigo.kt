package models

class Enemigo (
    nombre: String,
    habilidad: String,
    ataque: Int,
    arma: String,
    edad: Int,
    isDeleted: Boolean? = false
):Personaje(nombre,habilidad,ataque,edad,arma,isDeleted){
    override fun toString(): String {
        return "Enemigo $nombre $edad $ataque"
    }

}