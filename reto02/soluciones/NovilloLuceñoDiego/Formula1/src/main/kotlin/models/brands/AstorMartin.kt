package org.example.models.brands

import org.example.models.Pilot
import org.example.models.State

/**
 * @see Pilot, la interfaz que implementa, de la que saca su estado y algunas de las funciones que
 * utiliza la funcion "move()"
 * Esta clase introduce el metodo "badtechniqueshows()" que es especifica de la clase "AstonMartin"
 */
class AstorMartin(override var name: String, override val failurerate: Int) : Pilot {

    override val state = State( 0)
    override var position = 1
    override var laps = 0
    override var finishtime: Int = 0
    override var done = false

    /**
     * @see Pilot.move
     * Introduce el metodo "badtechniqueshows()", dependiendo de si se activa, puede
     * fallar y quedarse parado entre 1 y 3 segundos.
     * Si están parados baja el "downtime"
     * @see checkforaccidents para saber si van a tener un accidente
     * @see checkforpitstop para saber si van a tener que hacer un pitstop
     */
    override fun move() {
        checkforaccidents()
        if (!state.dnf) checkforpitstop()
        if (!state.dnf || state.downtimeleft != 0 || !done){
            if (badtechniqueshows() ){
                println("$name messed up, slowing them down")
                state.downtimeleft += (1..3).random()
            }else advance()
        }
        if(state.downtimeleft != 0) state.downtimeleft--
        if (!done) finishtime++
    }

    /**
     * @return un boolean dependiendo si falla o no
     */
    private fun badtechniqueshows() : Boolean { return (0..100).random() <= 15 }
}