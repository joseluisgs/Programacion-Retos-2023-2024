package models

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 * @property nombre heredado de personajes
 * @property puntosVida vida que suman los amigos a harry al encontrarse
 */
abstract class Amigos (override val nombre: String = "", open val puntosVida: Int ): Personajes(nombre){


    /**
     * @author Mario de Domingo
     * @version 1.0-SNAPSHOT
     * @return ayudar a harry
     */
    open fun ayudar(){
        println("Has Encontrado a $nombre y te ayudara sumandote $puntosVida puntos de vida")
    }
}