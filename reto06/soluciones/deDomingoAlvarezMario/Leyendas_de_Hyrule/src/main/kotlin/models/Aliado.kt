package org.example.models

class Aliado (
    id: Long,
    nombre: String,
    habilidad: String,
    ataque: Int,
    edad: Int,
    arma: String,
):Personaje(id, nombre,habilidad,ataque,edad,arma){
    override fun toString(): String {
        return "Estudiante(nombre=$nombre, habilidad=$habilidad, ataque=$ataque, edad=$edad, arma=$arma)"
    }
}