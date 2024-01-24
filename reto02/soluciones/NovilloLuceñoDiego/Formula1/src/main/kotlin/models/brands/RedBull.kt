package org.example.models.brands

import org.example.models.Pilot
import org.example.models.State

/**
 * @see Pilot, la interfaz que implementa, de la que saca su estado y algunas de las funciones que
 * utiliza la funcion "move()"
 * Esta clase introduce la funcion "canboost" que es especifica de los pilotos "RedBull"
 */
class RedBull(override var name: String, override val failurerate: Int) : Pilot {

    override val state = State(0)
    override var position = 1
    override var laps = 0
    override var finishtime: Int = 0
    override var done = false

    /**
     * @see Pilot.move
     * Introduce el metodo "canboost()", dependiendo de si lo pueden activar, puede avanzar 1 o 2
     * posiciones, y si están parados baja el "downtime"
     * @see checkforaccidents para saber si van a tener un accidente
     * @see checkforpitstop para saber si van a tener que hacer un pitstop
     */
    override fun move() {
        checkforaccidents()
        if (!state.dnf) checkforpitstop()
        if (!state.dnf || state.downtimeleft != 0 || !done){
            if(canboost()){
                println("$name was able to trigger a boost!")
                advance(2)
            }
            else advance()
        }
        if(state.downtimeleft != 0) state.downtimeleft--
        if (!done) finishtime++
    }

    /**
     * @return un boolean dependiendo si puede o no activar el "boost"
     */
    private fun canboost() : Boolean { return (0..100).random() <= 10 }
}