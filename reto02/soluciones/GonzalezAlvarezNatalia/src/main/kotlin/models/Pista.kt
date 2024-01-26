package org.example.models

import java.time.LocalTime

/**
 * Clase que representa todas las acciones del juego
 *
 * @param TAM_FILAS tamaño de filas de la matriz
 * @param TAM_COLUMNAS tamaño de columnas de la matriz
 */

private const val TAM_FILAS = 8
private const val TAM_COLUMNAS = 10

class Pista {
    /**
     * @param pista matriz que representa el tablero
     * @param piloto variable que llama a la clase Piloto()
     * @param max variable que llama a la clase Max()
     * @param checo variable que llama a la clase Checo()
     * @param alonso variable que llama a la clase Alonso()
     * @param stroll variable que llama a la clase Stroll()
     * @param hamilton variable que llama a la clase Hamilton()
     * @param russell variable que llama a la clase Russell()
     * @param sainz variable que llama a la clase Sainz()
     * @param leclerc variable que llama a la clase Leclerc()
     */
    private val pista : Array<Array<Any?>> = Array(TAM_FILAS) {arrayOfNulls(TAM_COLUMNAS)}

    private val piloto = Piloto(0,0,"")
    private val max = Max("Max",0,0)
    private val checo = Checo("Checo",1,0)
    private val alonso = Alonso("Alonso",2,0)
    private val stroll = Stroll("Stroll",3,0)
    private val sainz = Sainz("Sainz",4,0)
    private val leclerc = Leclerc("Leclerc",5,0)
    private val hamilton = Hamilton("Hamilton",6,0)
    private val russell = Russell("Russell",7,0)

    /**
     * Función que posiciona a los diferentes pilotos en la matriz.
     *
     * @author Natalia González
     * @see piloto.posicionar
     * @since 1.0-SNAPSHOT
     */
    private fun posicionarPilotos(){
        piloto.posicionar(pista, max)
        piloto.posicionar(pista, checo)
        piloto.posicionar(pista, alonso)
        piloto.posicionar(pista, stroll)
        piloto.posicionar(pista, hamilton)
        piloto.posicionar(pista, russell)
        piloto.posicionar(pista, sainz)
        piloto.posicionar(pista, leclerc)
    }

    /**
     * Función que mueve a los pilotos por la matriz.
     *
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    private fun moverPilotos(){
        if (posicionValida()) {
            piloto.mover(pista, max)
            piloto.mover(pista, checo)
            piloto.mover(pista, alonso)
            piloto.mover(pista, stroll)
            piloto.mover(pista, hamilton)
            piloto.mover(pista, russell)
            piloto.mover(pista, sainz)
            piloto.mover(pista, leclerc)
        }
    }

    /**
     * Función que verifica que la posición a la cual se va a mover el piloto es válida dentro de la matriz.
     *
     * @author Natalia González
     * @return true si la posición es válida, false si no lo es
     * @since 1.0-SNAPSHOT
     */
    fun posicionValida() : Boolean{
        return piloto.i in pista.indices && piloto.j in pista.indices
    }

    /**
     * Función que imprime la matriz con todos los diferentes pilotos posicionados dentro en ella.
     *
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    private fun imprimirPista(){
        val arrayNombres = arrayOf("MAX", "CHE", "ALO", "STR", "SAI", "LEC", "HAM", "RUS")
        for (i in pista.indices){
            print(arrayNombres[i])
            print(" | ")
            for (j in pista[i].indices){
                when(pista[i][j]){
                    null -> print("[ ]")
                    is Max -> print("[🔵]")
                    is Checo -> print("[🟢]")
                    is Alonso -> print("[🔴]")
                    is Stroll -> print("[🟤]")
                    is Sainz -> print("[🟡]")
                    is Leclerc -> print("[🟣]")
                    is Hamilton -> print("[🟠]")
                    is Russell -> print("[⚪]")
                    Estado.DESCALIFICADO -> print("[💀]")
                    //else-> print("[ ]")
                }
            }
            print("🏁")
            println()
        }
    }

    /**
     * Función que realiza diferentes acciones dependiendo del piloto que ocupe la celda.
     *
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    private fun acciones(){
        if (posicionValida() && piloto.continuar==0) {
            max.vueltaRapida(pista)
            checo.vueltaRapida(pista)
            sainz.vueltaRapida(pista)
            leclerc.vueltaRapida(pista)
            alonso.malaEstrategia(pista)
            stroll.malaEstrategia(pista)
            hamilton.cometerError(pista)
            russell.cometerError(pista)
            if ((0..100).random() < 25) {
                val eliminado = (0..100).random()

                if (pista[sainz.i][sainz.j] == Sainz("Sainz", 4, 0) && (leclerc.problemaFiabilidad(pista) || eliminado < 50)) {
                    sainz.problemaFiabilidad(pista)
                }else if (pista[leclerc.i][leclerc.j] == Leclerc("Leclerc", 4, 0) && (sainz.problemaFiabilidad(pista) || eliminado < 50)){
                    leclerc.problemaFiabilidad(pista)
                }
            }
        }
    }

    /**
     * Función que descalifica al piloto dependiendo del porcentaje que se dé.
     *
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    private fun accidente(){
        if ((0..100).random() < 1) {
            piloto.descalificarPiloto(pista, max)
        }
        if ((0..100).random() < 2) {
            piloto.descalificarPiloto(pista, checo)
        }
        if ((0..100).random() < 1) {
            piloto.descalificarPiloto(pista, alonso)
        }
        if ((0..100).random() < 4) {
            piloto.descalificarPiloto(pista, stroll)
        }
        if ((0..100).random() < 2) {
            piloto.descalificarPiloto(pista, hamilton)
        }
        if ((0..100).random() < 3) {
            piloto.descalificarPiloto(pista, russell)
        }
        if ((0..100).random() < 2) {
            piloto.descalificarPiloto(pista, sainz)
        }
        if ((0..100).random() < 4) {
            piloto.descalificarPiloto(pista, leclerc)
        }
    }

    /**
     * Función que para el movimiento de los pilotos para hacer un cambio de ruedas si se da el porcentaje indicado.
     *
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    private fun llover(){
        if ((0..100).random() < 35) {
            piloto.cambiarNeumaticos(max)
            piloto.cambiarNeumaticos(checo)
            piloto.cambiarNeumaticos(alonso)
            piloto.cambiarNeumaticos(stroll)
            piloto.cambiarNeumaticos(hamilton)
            piloto.cambiarNeumaticos(russell)
            piloto.cambiarNeumaticos(sainz)
            piloto.cambiarNeumaticos(leclerc)
        }
    }

    /**
     * Función que simula el desarrollo del juego.
     *
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    fun simular(){
        println("Bienvenido a Formula 1🏎️🏁")

        posicionarPilotos()
        imprimirPista()


        var contador = 0

        println()
        do {
            acciones()
            if (contador%3 == 0) {llover()}

            moverPilotos()
            accidente()


            imprimirPista()

            println()

            contador++
            println(LocalTime.now())

            println()

            Thread.sleep(1000)
        }while (!terminada())
    }

    /**
     * Función que determina si un piloto ha terminado la carrera
     *
     * @param piloto piloto asignado
     * @return true si el piloto terminó la carrera, false si no
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    private fun terminada():Boolean{
        if(piloto.terminado(max)&&
            piloto.terminado(alonso)&&
            piloto.terminado(checo)&&
            piloto.terminado(leclerc)&&
            piloto.terminado(hamilton)&&
            piloto.terminado(russell)&&
            piloto.terminado(sainz)&&
            piloto.terminado(stroll)
            )return true
        return false
    }


}