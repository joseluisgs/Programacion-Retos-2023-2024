package org.example.models

/**
 * Clase que representa al personaje Ron
 * @property nombre Nombre del personaje
 * @property puntosSanacion Puntos de vida que cura el personaje
 * @property probabilidadFalloCuracion Probabilidad de que Ron se equivoque y reste puntos de vida en vez de curarlos
 * @property puntosSanacionFallo Puntos que restará Ron si falla en la curación
 * @property icono Icono que representará visualmente el personaje en la mazmorra
 */
class Ron (
    nombre:String = "Ron",
    puntosSanacion:Int = 20,
    val probabilidadFalloCuracion:Int = 30,
    val puntosSanacionFallo:Int = -10
) : Amigo (nombre,puntosSanacion){

    override val icono:String = "🧑🏼‍🦰"

    override fun curar(personaje:Harry_Potter){

        if ((0..100).random()<probabilidadFalloCuracion) {
            println("$icono ¡Hola! soy $nombre pero la he cagado y te quito $puntosSanacionFallo de vida! $icono")
            personaje.puntosVida+=puntosSanacionFallo
        } else {
            println("$icono ¡Hola! soy $nombre y te voy a curar $puntosSanacion de daño! $icono")
            personaje.puntosVida+=puntosSanacion
        }
    }
}