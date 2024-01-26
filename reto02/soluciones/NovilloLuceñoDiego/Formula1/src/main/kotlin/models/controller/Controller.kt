package org.example.models.controller

import models.Track
import org.example.models.Pilot
import org.example.models.brands.AstorMartin
import org.example.models.brands.Ferrari
import org.example.models.brands.Mercedes
import org.example.models.brands.RedBull

const val CHANCEFOROVERCAST = 35

/**
 * Se encarga de manejar el funcionamiento del programa
 */
class Controller private constructor(
    private val track: Track,
    private val participatingpilots : Array<Pilot>)
{
    companion object{
        /**
         * @return un objeto "Controller" para poder utilizar sus metodos
         * Crea a los jugadores que serán parte de la carrera y una matriz "Track"
         */
        fun creategameinstance() : Controller{
            val participatingpilots = createplayers()
            val track = Track()
            track.updatetrack(participatingpilots)
            return Controller(track, participatingpilots)
        }

        /**
         * @return un array lleno de pilotos
         */
        private fun createplayers() : Array<Pilot>{
            val participatingpilots = Array<Pilot>(8) { RedBull("DummyPilot",0) }

            participatingpilots[0] = RedBull("Max",5)
            participatingpilots[1]  = RedBull("Checo",10)
            participatingpilots[2]  = AstorMartin("Aston",5)
            participatingpilots[3] = AstorMartin("Stroll",20)
            participatingpilots[4] = Mercedes("Hamilton", 10)
            participatingpilots[5] = Mercedes("Mercedes", 10)
            participatingpilots[6] = Ferrari("Sainz",10)
            participatingpilots[7] = Ferrari("LeClerk", 20)
            return participatingpilots
        }
    }

    /**
     * Un array que guarda a cada piloto a medida que terminan
     */
    private val rankedpilots = Array<Pilot?>(8) { null }

    /**
     * Recorre el array y si no estan descualificados, llama a el metodo "move()"
     * Cuando termina llama a la función "updatetrack()"
     * @see Pilot.move
     */
    private fun makeallofthemmove(){
        for (i in participatingpilots){
            if (!i.state.dnf && !i.done) i.move()
        }
        track.updatetrack(participatingpilots)
    }

    /**
     * Una funcion que "para" la carrera si lueve por una cantidad random, entre 1 a tres segundos
     */
    private fun checkforweather(){
        if ((0..100).random() <= CHANCEFOROVERCAST){
            val randomdelay = (1..3).random()
            println("The race has to stop for $randomdelay second/s, due to the weather forecast")
            println("After $randomdelay second/s, the race continues...")
        }
    }

    /**
     * La unica funcion que se llama en main para simular la carrera
     * Llama a las funciones:
     * @see makeallofthemmove
     * @see checkfornewfinishes
     * @see checkforweather
     * @see everyonesdone
     * @see calculatednfs es para saber si todos han sido descualificados antes de que haya ganado uno
     * Si no ha terminado ninguno, imprime un mensaje, sino llama a la funcion:
     * @see printranking
     */
    fun playaslongastheycan(){
        var time = 0
        do {
            println("------------------------------------")
            if (time != 0 && time % 6 == 0) checkforweather()
            track.printmap()
            makeallofthemmove()
            checkfornewfinishes()
            time++
        }while (everyonesdone())

        println("------------------------------------")
        if (calculatednfs() == 8) println("Everyone´s been disqualified...")
        else printranking()
    }

    /**
     * Imprime a cada jugador que ha terminado la carrera (que es raro), con su posicion y el tiempo
     */
    private fun printranking() {
        var rank = 1
        for (i in rankedpilots){
            if (i != null){
                println("$rank ${i.name}, they finished at : ${i.finishtime} seconds")
                rank++
            }
        }
    }

    /**
     * @return un boolean si la cantidad de dnfs es igual a la cantidad de nulos en la lista de
     * ganadores
     * @see calculatednfs
     */
    private fun everyonesdone(): Boolean {
        val amountofdnfs = calculatednfs()
        var amountofnulls = 0
        for (i in rankedpilots){
            if (i == null) amountofnulls++
        }
        return amountofdnfs != amountofnulls
    }

    /**
     * @return la cantidad de dnfs en el array de pilotos
     */
    private fun calculatednfs(): Int {
        var result = 0
        for (i in participatingpilots) if(i.state.dnf) result++
        return result
    }

    /**
     * @param target es el piloto que estas buscando en el array de pilotos que han ganado
     * Recorre el array y devuelve true si ya está en la lista, sino devuelve false
     */
    private fun itsbeenaccountedfor(target : Pilot): Boolean {
        for (i in rankedpilots){
            if (i != null && i === target) return true
        }
        return false
    }

    /**
     * Busca en el array de pilotos participantes y si no están en la lista ya, los pone
     */
    fun checkfornewfinishes(){
        for (i in participatingpilots){
            if ((i.position == 9 && i.laps == 2) && !itsbeenaccountedfor(i)) putthemonthelist(i)
        }
    }

    /**
     * Recorre el array y cuando encuentra un nulo, pone el piloto ahí
     */
    private fun putthemonthelist(newfinish : Pilot) {
        buscador@ for (i in rankedpilots.indices){
            if (rankedpilots[i] == null){
                rankedpilots[i] = newfinish
                break@buscador
            }
        }
    }
}