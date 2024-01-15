package org.example.models

/**
 * Clase que representa al personaje Bellatrix Lestrange
 * @property nombre Nombre del personaje
 * @property danio Puntos de vida que quita el personaje cuando ataca
 * @property icono Icono que representará visualmente el personaje en la mazmorra
 */
class Bellatrix(
    nombre:String = "Bellatrix Lestrange",
    danio:Int = 30
) : Enemigo (nombre,danio){

    override val icono:String = "👸🏻"

}