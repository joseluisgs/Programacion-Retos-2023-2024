package org.example.models.Pilotos

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 * @property emji indica el simbolo/emoji que se mostrara al imprimir la MATRIZ
 * @property nombre es el nombre o apodo de cada piloto
 * @property provAccident
 * @property i inidica en que fila se coloca cada piloto
 * @property j indica en que parte del recorrido esta cada piloto
 * @property vuelta el número de vuelta por el cual va cada piloto
 */
abstract class Pilotos(var emji: String, var nombre: String, private val
provAccident: Int, val i: Int, var j:Int, var vuelta: Int = 0) {
    private var movim = 0
    var moverse = 0
    private var boxes = 3
    var accidente = false

    /**
     * En caso de que no se haya accidentado seguira moviendose
     * y haciendo determinadas acciones segun corresponda
     */
    open fun accion() {
        if(!accidente){
            if (moverse == 0){
                movimiento()
            } else {moverse -= 1}
        }
    }

    /**
     * En caso de pit Stop no se movera entre 1 y 3 segundos
     */
    private fun pitStop(){
        moverse = (1 .. 3).random()
        movim = 0
    }

    /**
     * @return accidente igual a true
     * Esta es la provabilidad de accidente
     */
    fun accidente(){
        if(!accidente){
            val num = (1..500).random()
            if (num <= provAccident){
                accidente = true
                emji = " ☠️ "
                println("$nombre Ha tendo un accidente a los ${100*(j+1)*(vuelta+1)} metros")
            }
        }
    }

    /**
     * Si llueve pit stop por 1 a 3 segs
     */
    fun llueve(){pitStop()}

    /**
     * si cae en la casilla 5 entraen boxes y da un pit stop
     * si cae en la ultima casilla vuelve a la inicial y suma una vuelta
     * si esta en cualquier otra casilla avanza una sola
     */
    fun movimiento() {
        when (j) {
            boxes -> {
                j++
                pitStop()
                println("==> $nombre Ha entrado en 🔩BOXES🔧 ${moverse+1} segs")
            }
            9 -> {
                j = 0
                movim++
                vuelta++
                accidente()
            }
            else -> {
                j++
                movim++
                accidente()
            }
        }
    }

    /**
     * @return fin = true
     */
    fun ganar (){
        println("🏎️${nombre}🏁 Ha 🏅GANADO🏆 en la ${vuelta}ª vuelta al circuito")
    }

    fun estadoActual(){
        if (accidente){
            println("   - $nombre Ha tendo un accidente a los ${100*(j+1)*(vuelta+1)} metros")
        }else if (vuelta == 3){
            println("   - $nombre Ha 🏆GANADO🏅")
        }else{
            println("   - $nombre se ha quedado a los ${100*(j+1)*(vuelta+1)} metros")
        }
    }
}
