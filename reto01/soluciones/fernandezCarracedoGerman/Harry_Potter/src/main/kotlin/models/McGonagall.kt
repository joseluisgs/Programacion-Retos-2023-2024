package org.example.models

/**
 * Clase que representa al personaje McGonagall
 * @property nombre Nombre del personaje
 * @property puntosSanacion Puntos de vida que cura el personaje
 * @property icono Icono que representará visualmente el personaje en la mazmorra
 */
class McGonagall (
    nombre:String ="McGonagall",
    puntosSanacion:Int = 70
) : Amigo (nombre,puntosSanacion) {

    override val icono:String = "🧙🏽‍♀️"

}