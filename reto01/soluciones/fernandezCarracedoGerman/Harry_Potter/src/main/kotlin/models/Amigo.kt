package org.example.models

/**
 * Clase abstracta que representa personajes amigos.
 * @property nombre Nombre del personaje
 * @property puntosSanacion Puntos de vida que cura el personaje
 */
abstract class Amigo (
    nombre:String = "",
    val puntosSanacion:Int = 70,
): Personaje(nombre){

    open fun curar(personaje: Harry_Potter) {
        println("$icono ¡Hola! soy $nombre y te voy a curar $puntosSanacion de daño! $icono")
        personaje.puntosVida += puntosSanacion
    }

}