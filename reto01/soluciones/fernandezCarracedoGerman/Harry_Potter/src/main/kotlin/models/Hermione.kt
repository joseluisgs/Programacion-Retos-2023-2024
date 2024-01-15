package org.example.models

/**
 * Clase que representa al personaje Herminione
 * @property nombre Nombre del personaje
 * @property puntosSanacion Puntos de vida que cura el personaje
 * @property icono Icono que representará visualmente el personaje en la mazmorra
 */
class Hermione (
    nombre:String = "Hermione",
    puntosSanacion:Int = 30
) : Amigo (nombre,puntosSanacion){

    override val icono:String = "👩🏻"

}