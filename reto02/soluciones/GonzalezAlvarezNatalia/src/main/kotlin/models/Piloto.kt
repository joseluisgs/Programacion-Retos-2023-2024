package org.example.models

/**
 * Clase que representa a los pilotos del juego.
 *
 * @property i número de fila del piloto
 * @property j número de columna del piloto
 * @property nombre nombre del piloto
 * @property vueltas número de vueltas del piloto
 * @property continuar indicador de si los pilotos se mueven o no
 */
open class Piloto(val i: Int, var j:Int, val nombre : String = "", var vueltas : Int = 0,var continuar : Int = 0){

    /**
     * Función que posiciona a los diferentes pilotos en la matriz.
     *
     * @param pista matriz
     * @param piloto piloto asignado
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    fun posicionar(pista : Array<Array<Any?>>, piloto : Piloto){
        for (i in pista.indices){
            for (j in pista.indices){
                pista[piloto.i][piloto.j] = piloto
            }
        }
    }

    /**
     * Función que mueve a los pilotos por la matriz.
     *
     * @param pista matriz
     * @param piloto piloto asignado
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    fun mover(pista: Array<Array<Any?>>, piloto: Piloto){
        if (piloto.continuar==0){
            movernos(pista, piloto)
        }else{piloto.continuar -= 1}
    }

    /**
     * @param carrerasFinalizadas número de carreras acabadas
     */
    var carrerasFinalizadas : Int=0

    /**
     * Función que mueve a los pilotos por la matriz.
     *
     * @param pista matriz
     * @param piloto piloto asignado
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    private fun movernos(pista: Array<Array<Any?>>, piloto: Piloto): Array<Int> {
        when(piloto.j){
            pista[0].size - 1 -> {
                piloto.j = 0
                pista[piloto.i][piloto.j] = piloto
                pista[piloto.i][9] = null

                piloto.vueltas++
                println("Vueltas de ${piloto.nombre}: ${piloto.vueltas}")

                if(piloto.vueltas == 3){
                    pista[piloto.i][9] = piloto
                    pista[piloto.i][piloto.j] = null
                    piloto.continuar=70
                    carrerasFinalizadas++
                }
            }
            4 -> {
                pitStop(pista, piloto)
            }
            else -> {
                pista[piloto.i][piloto.j] = null
                piloto.j++
                pista[piloto.i][piloto.j] = piloto
            }
        }
        return arrayOf(piloto.i, piloto.j)
    }

    /**
     * Función que para el movimiento de los pilotos para hacer un cambio de ruedas si se da el porcentaje indicado.
     *
     * @param piloto piloto asignado
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    fun cambiarNeumaticos(piloto: Piloto){
        if (piloto.continuar==0){
            piloto.continuar=2
            println("Está lloviendo. ${piloto.nombre} ha cambiado sus neumáticos.")
        }
    }

    /**
     * Función que para el movimiento de los pilotos durante unos segundos.
     *
     * @param pista matriz
     * @param piloto piloto asignado
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    private fun pitStop(pista: Array<Array<Any?>>, piloto: Piloto){
        piloto.continuar = (1..3).random()
        pista[piloto.i][piloto.j] = null
        piloto.j++
        pista[piloto.i][piloto.j] = piloto
    }

    /**
     * @param muertos número de pilotos descalificados
     */
    var muertos :Int = 0

    /**
     * Función que descalifica al piloto.
     *
     * @param pista matriz
     * @param piloto piloto asignado
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    fun descalificarPiloto(pista: Array<Array<Any?>>, piloto: Piloto) {
        if (piloto.continuar<=3) {
            piloto.continuar = 70
            muertos++
            pista[piloto.i][piloto.j]=Estado.DESCALIFICADO

            println("muertos: $muertos")
            println("${piloto.nombre} ha sufrido un accidente y ha sido descalificado.")
        }
    }

    /**
     * Función que determina si un piloto ha terminado la carrera
     *
     * @param piloto piloto asignado
     * @return true si la variable continuar del piloto es mayor que tres, false si no
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    fun terminado(piloto: Piloto):Boolean {
        if (piloto.continuar>3)return true
        return false
    }

}