package org.example.models

/**
 * Clase que representa al personaje Harry Potter
 * @property nombre Nombre del personaje
 * @property probabilidadHechizo Probabilidad de acertar al lanzar un hechizo
 * @property probabilidadSerAtacado Probabilidad de ser atacado por un enemigo
 * @property icono Icono que representará visualmente el personaje en la mazmorra
 */
class Harry_Potter(
    nombre:String="Harry Potter",
    var puntosVida:Int = 100,
    var probabilidadHechizo:Int = 60,
    var probabilidadSerAtacado: Int = 50
): Personaje(nombre) {

    override val icono:String = "👨🏻‍🎓"

    fun atacar():Boolean{
        return ((0..100).random()<probabilidadHechizo)
    }

    fun defenderse(){
        println("Has tenido suerte. Te escabulles y no te ven...")
    }
}