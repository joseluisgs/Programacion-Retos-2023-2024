package org.example.models

/**
 * Clase abstracta que representa personajes enemigos.
 * @property nombre Nombre del personaje
 * @property danio Puntos de vida que quita el personaje cuando ataca
 */
abstract class Enemigo (
    nombre:String = "",
    val danio:Int = 70
) : Personaje (nombre){

    fun atacar (personaje: Harry_Potter){
        println("$icono ¡Jajaja soy $nombre y te hago $danio de daño! $icono")
        personaje.puntosVida-=danio
    }

    fun identificarse(){
        println("$icono ¡Soy $nombre, prepárate a morir! $icono")
    }
}