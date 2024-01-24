package org.example.models
private const val PROB_LLUVIA = 35

/**
 * La pista donde se desarrolla el gran premio
 * @property distancia la longitud de la pista
 * @property numPilotos el numero de pilotos y por tanto de filas que tendrá la pista
 */
class Pista (val numPilotos: Int = 8, private val distancia: Int = 10) {
    val pista = Array(numPilotos){ arrayOfNulls<Pilotos>(distancia)}
    private var time: Int = 0
    private val max = Max()
    private val checo = Checo()
    private val alonso = GOAT()
    private val stroll = Stroll()
    private val hamilton = Crymilton()
    private val russel = Russel()
    private val sainz = Sainz()
    private val leClerc = LeClerc()
    private var lloviendo = false
    private val parrilla = arrayOf(max, checo, alonso, stroll, hamilton, russel, leClerc)
    init {
        println("Bienvenidos al Gran premio de DAW")
        println("Si parpadean se lo pierden señores")
        println("🔴🔴🔴🔴🟢🟢🟢🟢")
        inicializarPista(pista)
        printPista(pista)
    }

    /**
     * La carrera
     */
    fun carrera(){
        inicioCrono()
        do{
            printPista(pista)
            moversePilotos()
            paradas()
            if (time % 3000 == 0){
                accidentes()
                eventosCarrera()
            }
            if(!lloviendo){
                metereología()
            }
           Thread.sleep(1000)
            time+=1000
        }while (!finCarrera())
        println("La carrera ha acabado 🏁🏁🏁")
        finCrono()
        podio(parrilla)
    }
    private fun podio(arrayPilotos: Array<Pilotos>) {
        var aux: Long
        for (i in 0 until arrayPilotos.size) {
            for (j in 0 until arrayPilotos.size - i - 1) {
                if (arrayPilotos[j].tiempoCarrera < (arrayPilotos[j + 1].tiempoCarrera) ) {
                     aux = arrayPilotos[j].tiempoCarrera
                    arrayPilotos[j].tiempoCarrera = arrayPilotos[j + 1].tiempoCarrera
                    arrayPilotos[j + 1].tiempoCarrera = aux
                }
            }
        }
        printPodio(arrayPilotos)
    }

    /**
     * Imprime el podio ordenado
     * @param arrayPilotos el array de los pilotos ordenado en función de su tiempo de carrera
     */
    private fun printPodio(arrayPilotos: Array<Pilotos>) {
        println("Este es el podio del Gran Premio de DAW")
        for(i in arrayPilotos.indices){
            when(i){
                0 -> println("🥇 ${arrayPilotos[i].nombre}")
                1 -> println("🥈 ${arrayPilotos[i].nombre}")
                2 -> println("🥉 ${arrayPilotos[i].nombre}")
            }
        }
        if(arrayPilotos[0] is GOAT){ println("Por fin llegó la 33 señores")}
    }

    /**
     * Crono final de cada piloto al acabar la carrera
     */
    private fun finCrono(){
        max.cronoFinal()
        checo.cronoFinal()
        alonso.cronoFinal()
        stroll.cronoFinal()
        hamilton.cronoFinal()
        russel.cronoFinal()
        sainz.cronoFinal()
        leClerc.cronoFinal()
    }

    /**
     * Crono inicial de cada piloto al empezar la carrera
     */
    private fun inicioCrono() {
        max.cronoInicial()
        checo.cronoInicial()
        alonso.cronoInicial()
        stroll.cronoInicial()
        hamilton.cronoInicial()
        russel.cronoInicial()
        sainz.cronoInicial()
        leClerc.cronoInicial()
    }

    /**
     * Comprueba si llueve cada 6 segundos
     */
    private fun metereología(){
        when(time % 6000){
            0 -> lluvia()
        }
    }

    /**
     * Existiendo una probabilidad de lluvia del 35% si se cumple, llueve y obliga a todos los pilotos en pista
     * a realizar un PitStop
     */
    private fun lluvia(){
        if((0..100).random() < PROB_LLUVIA){
            println("🌧️🌧️🌧️")
            println("Antonio el cielo está mas oscuro que Hamilton, van a tener que entrar a boxes")
            mensajesBoxes()
            paradas()
            this.lloviendo = true
        }
    }

    /**
     * Los posibles eventos que pueden sufrir cada piloto
     */
    private fun eventosCarrera(){
        sainz.falloMotor(sainz)
        leClerc.falloMotor(leClerc)
        hamilton.provocarSafetyCar(hamilton, this)
        russel.provocarSafetyCar(russel, this)
        max.vueltaRapida(max, this)
        checo.vueltaRapida(checo, this)
    }

    /**
     * Aviso del ingeniero de pista a los pilotos en caso de lluvia
     */
    private fun mensajesBoxes(){
        max.avisoIngeniero()
        checo.avisoIngeniero()
        sainz.avisoIngeniero()
        leClerc.avisoIngeniero()
        alonso.avisoIngeniero()
        stroll.avisoIngeniero()
        hamilton.avisoIngeniero()
        russel.avisoIngeniero()
    }

    /**
     * Los pitStop de cada piloto
     */
    private fun paradas(){
        max.pitStopPiloto()
        checo.pitStopPiloto()
        leClerc.pitStopPiloto()
        sainz.pitStopPiloto()
        alonso.pitStopPiloto()
        stroll.pitStopPiloto()
        hamilton.pitStopPiloto()
        russel.pitStopPiloto()
    }

    /**
     * Los posibles accidentes de cada piloto
     */
    private fun accidentes(){
        max.comprobarAccidente()
        checo.comprobarAccidente()
        leClerc.comprobarAccidente()
        sainz.comprobarAccidente()
        alonso.comprobarAccidente()
        stroll.comprobarAccidente()
        hamilton.comprobarAccidente()
        russel.comprobarAccidente()
    }

    /**
     * Comprueba que todos los pilotos hayan terminado la carrera
     * @return true si todos los pilotos han acabado, false si no han acabado
     */
    private fun finCarrera(): Boolean {
        if(max.comprobarCarreraAcabada() &&
            checo.comprobarCarreraAcabada() &&
            sainz.comprobarCarreraAcabada() &&
            alonso.comprobarCarreraAcabada() &&
            stroll.comprobarCarreraAcabada() &&
            leClerc.comprobarCarreraAcabada() &&
            hamilton.comprobarCarreraAcabada() &&
            russel.comprobarCarreraAcabada()){
            return true
        }
        return false
    }

    /**
     * Movimiento de los pilotos en la pista
     */
    private fun moversePilotos(){
        max.moverse(pista)
        checo.moverse(pista)
        sainz.estratgia(pista)
        leClerc.estratgia(pista)
        alonso.estratgia(pista)
        stroll.estratgia(pista)
        hamilton.moverse(pista)
        russel.moverse(pista)
    }

    /**
     * Coloca los pilotos en la pista
     * @param pista la pista donde se colocan los pilotos
     * @return la pista con los pilotos colocados
     */
    private fun inicializarPista(pista: Array<Array<Pilotos?>>): Array<Array<Pilotos?>> {
        max.colocarPiloto(pista)
        checo.colocarPiloto(pista)
        alonso.colocarPiloto(pista)
        stroll.colocarPiloto(pista)
        hamilton.colocarPiloto(pista)
        russel.colocarPiloto(pista)
        sainz.colocarPiloto(pista)
        leClerc.colocarPiloto(pista)
        return pista
    }

    /**
     * Imprime las posiciones de cada piloto
     * @param pista la pista en la que se encuentran los pilotos
     */
   private fun printPista(pista: Array<Array<Pilotos?>>){
        for(i in pista.indices){
            for(j in pista[i].indices){
                when(pista[i][j]){
                    is Max -> print("[ 🦁 ]")
                    is Checo -> print("[ 🌮 ]")
                    is GOAT -> print("[ 🐐 ]")
                    is Stroll -> print("[ 💸 ]")
                    is Crymilton -> print("[ 👶🏿 ]")
                    is Russel -> print("[ 💂 ]")
                    is Sainz -> print("[ 🌶️ ]")
                    is LeClerc -> print("[ 😡 ]")
                    else -> print("[  ]")
                }
            }
            println()
        }
        println()
    }
}