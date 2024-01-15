package models

import mundo

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 * @property nombre del personaje/amigo
 * @property danio que inflingen
 */

class Voldemort: Enemigos("Voldemort ☠️", -70) {
    var valor: Int = 0

    /**
     * @author Mario de Domingo
     * @version 1.0-SNAPSHOT
     * @return texto casilla voldemort
     */
    fun casilla() {
        println(
            """Has encontrado a 
                |       ☠️☠️☠️☠️☠️☠️
                |       ☠️VOLDEMORT☠️
                |       ☠️☠️☠️☠️☠️☠️""".trimMargin()
        )
    }

    /**
     * @author Mario de Domingo
     * @version 1.0-SNAPSHOT
     * @return resultado de atacr cuando has conseguido todos los horrocruxe
     */
    fun atacar(){
        println("y sin sus Horocruxes es Mortal ¡Es la hora de Atacar!")
        println()
        val provExito = (1..10).random()
        if (provExito <= 7) {
            println("¡¡¡HAS GANADO!!!")
            mundo.finJuego = true
            valor = 0
        } else {
            println("Has fallado, Voldemort ha escapado y te ha infligido 70 puntos de Daño")
            valor = danio
            mundo.casillaRandom(nombre)
        }
    }

    /**
     * @author Mario de Domingo
     * @version 1.0-SNAPSHOT
     * @return si no has conseguido todos los horrocrux voldemort infrinje 70 de daño
     */
    fun perder(){
        println("Sin haber destruido todos sus horrocruxes es imparable -> 70 puntos de daño.")
        valor = danio
        mundo.casillaRandom(nombre)
    }
}
