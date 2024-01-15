package org.example.models

/**
 * Clase que representa a un personaje Dementor
 * @property nombre Nombre del personaje
 * @property danio Puntos de vida que quita el personaje cuando ataca
 * @property icono Icono que representará visualmente el personaje en la mazmorra
 */
class Dementor(
    nombre:String = "Dementor",
    danio:Int = 10
) : Enemigo (nombre,danio){

    override val icono:String = "🩻"

}