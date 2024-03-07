package org.example.models

/**
 * Villano es el tipo de personaje que se crea si el dato de Villano == True
 *
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 */
class Villano(
    val id: Int,
    val nickName: String,
    val nombre: String
) : Personaje(){
    override fun toString(): String {
        return "Estudiante(ID=$id nombre=$nombre, NickName=$nickName)"
    }
}