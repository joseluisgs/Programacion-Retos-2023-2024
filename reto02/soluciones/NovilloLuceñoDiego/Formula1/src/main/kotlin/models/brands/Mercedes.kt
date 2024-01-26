package org.example.models.brands

import org.example.models.Pilot
import org.example.models.State

/**
 * @see Pilot, la interfaz que implementa, de la que saca su estado y algunas de las funciones que
 * utiliza la funcion "move()"
 * Esta clase introduce el metodo "safetycaractivates" que es especifica de la clase "Mercedes"
 */
class Mercedes (override var name: String, override val failurerate: Int) : Pilot {

    override val state = State( 0)
    override var position = 1
    override var laps = 0
    override var finishtime: Int = 0
    override var done = false

    /**
     * @see Pilot.move
     * Introduce el metodo "safetycaractivates()", dependiendo de si se activa, puede
     * fallar y retroceder una posicion.
     * Si están parados baja el "downtime"
     * @see checkforaccidents para saber si van a tener un accidente
     * @see checkforpitstop para saber si van a tener que hacer un pitstop
     */
    override fun move() {
        checkforaccidents()
        if (!state.dnf) checkforpitstop()
        if (!state.dnf || state.downtimeleft != 0 || !done){
            if (safetycaractivates()){
                println("The safety car activated...making $name go back a position")
                advance(-1)
            }else advance()
        }
        if(state.downtimeleft != 0) state.downtimeleft--
        if (!done) finishtime++
    }

    /**
     * @return un boolean dependiendo si se activa o no
     */
    private fun safetycaractivates() : Boolean { return (0..100).random() <= 15 }
}