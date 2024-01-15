package org.example.models

/**
 * Clase que representa al personaje Lord Voldemort
 * @property nombre Nombre del personaje
 * @property danio Puntos de vida que quita el personaje cuando ataca
 * @property icono Icono que representará visualmente el personaje en la mazmorra
 */
class Voldemort(
    nombre:String="Lord Voldemort",
    danio:Int = 70
) : Enemigo (nombre,danio){

    override val icono:String = "🧛🏽‍♂️"

}