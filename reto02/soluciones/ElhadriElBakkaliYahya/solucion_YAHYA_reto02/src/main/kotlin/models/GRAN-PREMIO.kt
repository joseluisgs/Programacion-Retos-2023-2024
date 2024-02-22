package models

import java.time.LocalTime


class `GRAN-PREMIO`:Metereologia {

    override var lluvia: Boolean=false
    override var contador: Int=0

    private val circuito:Array<Array<Piloto?>> = Array(8){Array(10){null} }

    private val max= MaxVerstapen()
    private val checo= Checo()
    private val alonso= Alonso()
    private val stroll= Stroll()
    private val hamilton= Hamilton()
    private val russel= Russel()
    private val sainz= Sainz()
    private val leclerc= LeClerc()

    /**
     * Esta seri la funcion principal, e sdecir la de la simulacion, en ella ejecutamos todas las funciones
     * @see posicinamientoInicial
     * @see imprimirCircuito
     * @see moverTodosPilotos
     * @see pitstop
     * @see acciones
     * @see accidentes
     * @see comprobarMeterologia
     * @see comprobarVueltas
     * @see posicionarPilotos
     * @see todosTerminan
     * @see imprimirCircuito
     * @see informe
     * @see cambioTiempos
     * @see ordenar
     */

    fun inicializarPremio(){
        posicionarPilotosInicio()
        imprimirCircuito()
        println()
        val vectorPilotos:Array<Piloto> = arrayOf(max,alonso,checo,hamilton,leclerc,russel,sainz,stroll)
        do {
            moverTodosPilotos()

            pitstop()

            acciones()

            accidentes()

            comprobarMeterologia()
            comprobarVueltas()

            posicionarPilotos()

            println()

            if (!todosTerminan()){
                imprimirCircuito()
            }

            Thread.sleep(1000)
            contador++
        }while (!todosTerminan())
        informe()

        cambioTiempos()
        ordenar(vectorPilotos)

    }

    /**
     * Esta funcion agrupamos todos los cambio de tiempos de los pilotos
     * @see cambiar
     */
    private fun cambioTiempos() {
        cambiar(alonso)
        cambiar(checo)
        cambiar(hamilton)
        cambiar(leclerc)
        cambiar(max)
        cambiar(russel)
        cambiar(sainz)
        cambiar(stroll)
    }

    /**
     * Esta funcion lo que hace es cambiar el tiempo final de cada uno de los pilotos que a sufrido dnf
     * @param piloto
     *
     */
    private fun cambiar(piloto: Piloto) {
        if (piloto.isDnf){
            piloto.tiempoFinal= LocalTime.now()
        }

    }

    /**
     *Esta funcion es la que comprueba la meterologia cada vez que el contado este en 6, y en caso de que sea 6 hace un random
     * y si el random sale true hace un pitstop
     * @see pitstop
     * @see lluvia
     */
    private fun comprobarMeterologia(){
        if (contador==6){
            lluvia=lluvia()
            if (lluvia){
                println("ESTA EMPEZZANDO A LLOVER CAMBIO DE NEUMATICOS")
                pitstop()
            }
            contador=0
        }
    }

    /**
     * esta funcion nos ordena a los pilotos segun el orden de llegada a meta
     * @see imprimirArray
     * @param arrayPersonas
     */
    private fun ordenar(arrayPersonas: Array<Piloto>) {

            for (i in 0 until arrayPersonas.size) {
                for (j in 0 until arrayPersonas.size - 1) {
                    if (arrayPersonas[j].tiempoFinal.isAfter(arrayPersonas[j + 1].tiempoFinal) ) {
                        var aux = arrayPersonas[j]
                        arrayPersonas[j]= arrayPersonas[j + 1]
                        arrayPersonas[j + 1] = aux!!
                    }
                }
            }
        imprimirArray(arrayPersonas)

    }

    /**
     * esta funcion nos impime el array una vez ordenado segun los tiempos finales y nos imprime por pantalla los tres primeros
     * @param arrayPersonas
     */
    private fun imprimirArray(arrayPersonas: Array<Piloto>) {
        println("EL PODIO QUEDARIA ASI:\uD83C\uDFC6")
        var indice=0
        for (i in arrayPersonas.indices){
            if (arrayPersonas[i].isDnf){
                indice=indice
            }
            else if (indice+1<=3){
                when(indice+1){
                    1->println("${indice+1}º\uD83E\uDD47-${arrayPersonas[i].nombre}")
                    2->println("${indice+1}º\uD83E\uDD48-${arrayPersonas[i].nombre}")
                    3->println("${indice+1}º\uD83E\uDD49-${arrayPersonas[i].nombre}")
                }
                indice++
            }

        }
    }

    /**
     * Esta es la funcion que agrupa el infome individual de cada uno de los pilotos
     * @see infomePiloto
     */
    private fun informe() {
        println("LA CARRERA HA FINALIZADO")
        println("LOS TIEMPOS FINALESS QUEDARIAS ASI:")
        println()
        infomePiloto(max)
        Thread.sleep(500)
        infomePiloto(alonso)
        Thread.sleep(500)
        infomePiloto(checo)
        Thread.sleep(500)
        infomePiloto(hamilton)
        Thread.sleep(500)
        infomePiloto(leclerc)
        Thread.sleep(500)
        infomePiloto(russel)
        Thread.sleep(500)
        infomePiloto(sainz)
        Thread.sleep(500)
        infomePiloto(stroll)
    }

    /**
     * Esta funcion nos imprime los tiempos de inicio de cada piloto y el tiempo en caso de que no haya sido descalificado
     * @param piloto
     */
    private fun infomePiloto(piloto: Piloto) {
        println("-${piloto.nombre} SUS TIEMPOS HAN SIDO:")
        println("   INICIO: ${piloto.tiempoInicio}")
        if (piloto.isTerminada) println("   FINAL: ${piloto.tiempoFinal}")
        else println("  FINAL: ${piloto.nombre} HA SUFRIDO DNF")
    }

    /**
     * esta funcion agrupa el conjunto de ver si el piloto a dado las 3 vueltas
     * @see comprobarPiloto
     */
    private fun comprobarVueltas() {
        alonso.comprobarPiloto()
        checo.comprobarPiloto()
        hamilton.comprobarPiloto()
        leclerc.comprobarPiloto()
        max.comprobarPiloto()
        russel.comprobarPiloto()
        sainz.comprobarPiloto()
        stroll.comprobarPiloto()
    }




    /**
     * agrupa el conjunto de todos los posicinamientos iniciales
     * @see posicinamientoInicial
     */
    private fun posicionarPilotosInicio() {
        max.posicinamientoInicial(circuito)
        alonso.posicinamientoInicial(circuito)
        checo.posicinamientoInicial(circuito)
        hamilton.posicinamientoInicial(circuito)
        leclerc.posicinamientoInicial(circuito)
        russel.posicinamientoInicial(circuito)
        sainz.posicinamientoInicial(circuito)
        stroll.posicinamientoInicial(circuito)
    }



    /**
     * agrupa el conjunto de las acciones segun cada escuderia
     * @see accionesRedBull
     * @see accionesAstonMartin
     * @see accionesMercedes
     * @see accionesFerrari
     */
    private fun acciones() {
        max.accionesRedBull()
        checo.accionesRedBull()

        alonso.accionesAstonMartin()
        stroll.accionesAstonMartin()

        accionesMercedes(hamilton)
        accionesMercedes(russel)

        accionesFerrari(sainz)
        accionesFerrari(leclerc)
    }








    /**
     * agrupa el conjunto de las funciones de accidente
     * @see accidente
     */
    private fun accidentes() {
        accidente(alonso)
        accidente(checo)
        accidente(hamilton)
        accidente(leclerc)
        accidente(max)
        accidente(russel)
        accidente(sainz)
        accidente(stroll)
    }



    /**
     * agrupa el conjunto de pitstop de todos
     * @see pitStopPiloto
     */
    private fun pitstop() {
        pitStopPiloto(alonso)
        pitStopPiloto(checo)
        pitStopPiloto(hamilton)
        pitStopPiloto(leclerc)
        pitStopPiloto(max)
        pitStopPiloto(russel)
        pitStopPiloto(sainz)
        pitStopPiloto(stroll)
    }



    /**
     * comprueba que todos esten terminado
     * @see acabada
     * @return true si todos han terminado
     * @return false si alguno de ellos no a termiando
     */
    private fun todosTerminan():Boolean{
        if (
            (alonso.acabada())&&
            (checo.acabada())&&
            (hamilton.acabada())&&
            (leclerc.acabada())&&
            (max.acabada())&&
            (russel.acabada())&&
            (sainz.acabada())&&
            (stroll.acabada())
        )return true
        return false
    }




    private fun moverTodosPilotos() {
        anularPosiciones()

        max.moverPiloto()
        checo.moverPiloto()

        moverAstonMartin(alonso)
        moverAstonMartin(stroll)

        moverPiloto(hamilton)
        moverPiloto(russel)

        moverFerrari(sainz)
        moverFerrari(leclerc)

    }






    /**
     * agrupa las anulaciones
     * @see anularPiloto
     */
    private fun anularPosiciones() {
        max.anularPiloto(circuito)
        checo.anularPiloto(circuito)
        alonso.anularPiloto(circuito)
        stroll.anularPiloto(circuito)
        hamilton.anularPiloto(circuito)
        russel.anularPiloto(circuito)
        sainz.anularPiloto(circuito)
        leclerc.anularPiloto(circuito)
    }



    /**
     * imimimos el array posicionando a los jugadores
     */
    private fun imprimirCircuito() {

        for (i in circuito.indices){
            for (j in circuito[i].indices){
                if (j==0){
                    when(i){
                        max.fila-> print("MV ")
                        checo.fila-> print("CH ")
                        alonso.fila-> print("AL ")
                        stroll.fila-> print("ST ")
                        hamilton.fila-> print("HA ")
                        russel.fila-> print("RU ")
                        sainz.fila-> print("SZ ")
                        leclerc.fila-> print("LE ")
                    }
                }
                if (j!=9){
                    when(circuito[i][j]){
                        max-> print("[V]")
                        checo-> print("[C]")
                        alonso-> print("[A]")
                        stroll-> print("[S]")
                        hamilton-> print("[H]")
                        russel-> print("[R]")
                        sainz-> print("[Z]")
                        leclerc-> print("[L]")
                        null-> print("[ ]")
                    }
                }else{
                    when(circuito[i][j]){
                        max-> print("[V] \uD83C\uDFC1\u200B")
                        checo-> print("[C] \uD83C\uDFC1\u200B")
                        alonso-> print("[A] \uD83C\uDFC1\u200B")
                        stroll-> print("[S] \uD83C\uDFC1\u200B")
                        hamilton-> print("[H] \uD83C\uDFC1\u200B")
                        russel-> print("[R] \uD83C\uDFC1\u200B")
                        sainz-> print("[Z] \uD83C\uDFC1\u200B")
                        leclerc-> print("[L] \uD83C\uDFC1\u200B")
                        null-> print("[ ] \uD83C\uDFC1\u200B")
                    }

                }

            }
            println()
        }
    }


    private fun posicionarPilotos() {
        max.posicionamiento(circuito)
        checo.posicionamiento(circuito)

        alonso.posicionamiento(circuito)
        stroll.posicionamiento(circuito)

        hamilton.posicionamiento(circuito)
        russel.posicionamiento(circuito)

        sainz.posicionamiento(circuito)
        leclerc.posicionamiento(circuito)

    }


}

   //private operator fun LocalDate.minus(tiempoFinal: LocalDate?): LocalDate {
      //  return this - tiempoFinal
   //}
