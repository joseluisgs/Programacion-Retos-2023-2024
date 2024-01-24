package org.example.models.brands

import org.example.models.Pilot
import org.example.models.State

/**
 * @see Pilot, la interfaz que implementa, de la que saca su estado y algunas de las funciones que
 * utiliza la funcion "move()"
 * Esta clase introduce los metodos "reliabilityfails" y "lackofexperienceshows()" que son
 * especificas de la clase "Ferrari"
 */
class Ferrari(override var name: String, override val failurerate: Int) : Pilot {

    override val state = State( 0)
    override var position = 1
    override var laps = 0
    override var finishtime: Int = 0
    override var done = false

    /**
     * @see Pilot.move
     * Introduce los metodos "reliabilityfails" y "lackofexperienceshows()", dependiendo de si
     * se activa, puede, o fallar y retroceder una posicion o ser descualificado y si
     * Si están parados baja el "downtime"
     * @see checkforaccidents para saber si van a tener un accidente
     * @see checkforpitstop para saber si van a tener que hacer un pitstop
     */
    override fun move() {
        checkforaccidents()
        if (!state.dnf) checkforpitstop()
        if (!state.dnf || state.downtimeleft != 0 || !done){
            when{
                reliabilityfails() -> {
                    println("$name messed up, disqualifying him...")
                    state.dnf = true
                }
                lackofexperienceshows() -> {
                    if (position != 0 && laps != 1){
                        println("$name made a mistake, making them go back")
                        advance(-1)
                    }
                }
                else -> advance()
            }
        }
        if(state.downtimeleft != 0) state.downtimeleft--
        if (!done) finishtime++
    }

    /**
     * @return un boolean dependiendo si falla o no
     */
    private fun reliabilityfails() : Boolean { return (0..100).random() <= 25 }

    /**
     * @return un boolean dependiendo si falla o no
     */
    private fun lackofexperienceshows() : Boolean { return (0..100).random() <= 15 }
}