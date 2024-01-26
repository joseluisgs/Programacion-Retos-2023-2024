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
        comprobarPiloto(alonso)
        comprobarPiloto(checo)
        comprobarPiloto(hamilton)
        comprobarPiloto(leclerc)
        comprobarPiloto(max)
        comprobarPiloto(russel)
        comprobarPiloto(sainz)
        comprobarPiloto(stroll)
    }

    /**
     * esta funcion comprueba que el piloto hay terminado y nos coloca su tiempo de final para luego poder ordenarlos
     * @param piloto
     */
    private fun comprobarPiloto(piloto: Piloto) {
        if (piloto.isTerminada && !piloto.tiempoFinalComprobada){
            piloto.tiempoFinal= LocalTime.now()
            piloto.tiempoFinalComprobada=true
            //piloto.tiempoFinal=piloto.tiempoInicio-piloto.tiempoFinal
            //piloto.timpoFinal-=piloto.tiempoInicio
        }
    }

    /**
     * agrupa el conjunto de todos los posicinamientos iniciales
     * @see posicinamientoInicial
     */
    private fun posicionarPilotosInicio() {
        posicinamientoInicial(max)
        posicinamientoInicial(alonso)
        posicinamientoInicial(checo)
        posicinamientoInicial(hamilton)
        posicinamientoInicial(leclerc)
        posicinamientoInicial(russel)
        posicinamientoInicial(sainz)
        posicinamientoInicial(stroll)
    }

    /**
     * esta funcion nos coloca al piloto en la matriz segun su columna y su fila
     * @param piloto
     */
    private fun posicinamientoInicial(piloto: Piloto){
        circuito[piloto.fila][piloto.columna]=piloto
        piloto.tiempoInicio= LocalTime.now()
    }

    /**
     * agrupa el conjunto de las acciones segun cada escuderia
     * @see accionesRedBull
     * @see accionesAstonMartin
     * @see accionesMercedes
     * @see accionesFerrari
     */
    private fun acciones() {
        accionesRedBull(max)
        accionesRedBull(checo)

        accionesAstonMartin(alonso)
        accionesAstonMartin(stroll)

        accionesMercedes(hamilton)
        accionesMercedes(russel)

        accionesFerrari(sainz)
        accionesFerrari(leclerc)
    }

    /**
     * mientras que el piloto haya terminado la carrera y que la vuelta no sea rapida
     * hacemos la probabilidad de que sea rapida que es una funcion que es de herencia
     * @param piloto
     *
     */
    private fun accionesRedBull(piloto: RedBull){
        if (!acabada(piloto as Piloto) && !piloto.isRapida  ) {
            piloto.isRapida=piloto.vueltaRapida()
        }
    }

    /**
     * mientras que el piloto haya terminado la carrera y que no este en mala estrategia
     * hacemos la probabilidad de que sea mala estrategia que es una funcion que es de herencia
     * @param piloto
     *
     */
    private fun accionesAstonMartin(piloto:AstonMartin){
        if (!acabada(piloto as Piloto)&& !piloto.isMalaEstrategia){
            piloto.isMalaEstrategia=piloto.malaEstrategia()
            if (piloto.isMalaEstrategia){
                println("${piloto.nombre} A SUFRIDO UNA MALA ESTRATEGIA")
                piloto.tiempoMalaEstrategia=2
            }
        }
    }
    /**
     * mientras que el piloto haya terminado la carrera y que no tenga saftey car
     * hacemos la probabilidad de que sea sayftey car que es una funcion que es de herencia
     * @param piloto
     *
     */
    private fun accionesMercedes(piloto: Mercedes){
        if (!acabada(piloto as Piloto)&& !piloto.isSaftyCar ){
            piloto.isSaftyCar=piloto.safteyCar()
            if (piloto.isSaftyCar){
                println("A ${piloto.nombre} LE SALE EL SAYFTEY CAR")
                if (piloto.columna==0){
                    piloto.columna=9
                    hamilton.vuelta--
                }
                else piloto.columna--
                piloto.isSaftyCar=false
            }
        }
    }
    /**
     * mientras que el piloto haya terminado la carrera y que no este en mala estrategia
     * hacemos la probabilidad de que sea mala estrategia que es una funcion que es de herencia
     * @param piloto
     *
     */
    private fun accionesFerrari(piloto: Ferrari) {
        if (!acabada(piloto as Piloto)&&!piloto.isMalaEstrategia ){
            piloto.isMalaEstrategia=piloto.malaEstrategia()
            if (piloto.isMalaEstrategia){
                println("${piloto.nombre} A SUFRIDO UNA MALA ESTRATEGIA")
                piloto.tiempoMalaEstrategia=2
            }
        }
        if (!acabada(piloto as Piloto) && !piloto.isProblemasDeFiabilidad){
            piloto.isProblemasDeFiabilidad=piloto.problemasFiabilidad()
            if (piloto.isProblemasDeFiabilidad)piloto.isDnf=true
        }
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
     * mientras que no este dnf, hacemos la probabilidad de que lo sea, y si lo es activamos el true en el piloto y imprimimos por pantalla
     * @param piloto
     */
    private fun accidente(piloto: Piloto) {
        if (!piloto.isDnf){
            piloto.isDnf=piloto.accidenteF()
            if (piloto.isDnf) println("${piloto.nombre} HA SUFRIDO UN ACCIDENTE EN SU VUELTA ${piloto.vuelta}")
        }
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
     * siempre que el tiempo de pitstop este en 0 hacemos el pitstop que es un random que duevlve los segundos que tenemso que parar
     * @param piloto
     */
    private fun pitStopPiloto(piloto: Piloto){
        if (piloto.tiempoPitstop==0) {
            piloto.tiempoPitstop = piloto.pitStop()
        }
    }

    /**
     * comprueba que todos esten terminado
     * @see acabada
     * @return true si todos han terminado
     * @return false si alguno de ellos no a termiando
     */
    private fun todosTerminan():Boolean{
        if (
            (acabada(alonso))&&
            (acabada(checo))&&
            (acabada(hamilton))&&
            (acabada(leclerc))&&
            (acabada(max))&&
            (acabada(russel))&&
            (acabada(sainz))&&
            (acabada(stroll))
        )return true
        return false
    }

    /**
     * un jugador termina si esta descalidicado o si a dado tres vueltas
     * @param piloto
     * @return true si esta acabada
     * @return false si no a terminado
     */
    private fun acabada(piloto: Piloto): Boolean {
        if (piloto.isTerminada || piloto.isDnf)return true
        return false
    }


    private fun moverTodosPilotos() {
        anularPosiciones()

        moverPiloto(max)
        moverPiloto(checo)

        moverAstonMartin(alonso)
        moverAstonMartin(stroll)

        moverPiloto(hamilton)
        moverPiloto(russel)

        moverFerrari(sainz)
        moverFerrari(leclerc)

    }

    /**
     * esta funcion s erealiza siempre que el piloto no haya terminado
     * si el piloto tiene tiempo en pitstop no se mueve y se resta un segundo al contador de pitstop
     * si el pitstop esta en 0 se mueve, si la columna es 9 se pone en 0 y se suma una vuelta
     * @param piloto
     */
    private fun moverPiloto(piloto: Piloto){
        if(!acabada(piloto)){
            if (piloto.tiempoPitstop!=0){
                println("${piloto.nombre} ESTA EN PITSTOP")
                piloto.tiempoPitstop--
            }
            if (piloto.tiempoPitstop==0) {
                if (piloto.columna == 9) {
                    piloto.vuelta++
                    piloto.columna = 0
                    when(piloto){
                        is RedBull->piloto.isRapida=false
                    }
                    if (piloto.vuelta>=3)piloto.isTerminada=true
                } else piloto.moverPiloto()
            }
        }
    }
    private fun moverAstonMartin(piloto: AstonMartin){
        if (piloto.tiempoMalaEstrategia>0){
            piloto.tiempoMalaEstrategia--
        }else if (piloto.tiempoMalaEstrategia==0){
            piloto.isMalaEstrategia=false
            moverPiloto(piloto as Piloto)
        }
    }
    private fun moverFerrari(piloto: Ferrari){
        if (piloto.tiempoMalaEstrategia!=0){
            piloto.tiempoMalaEstrategia--
        }else if (piloto.tiempoMalaEstrategia==0) {
            piloto.isMalaEstrategia = false
            moverPiloto(piloto as Piloto)
        }
    }

    /**
     * agrupa las anulaciones
     * @see anularPiloto
     */
    private fun anularPosiciones() {
        anularPiloto(max)
        anularPiloto(checo)
        anularPiloto(alonso)
        anularPiloto(stroll)
        anularPiloto(hamilton)
        anularPiloto(russel)
        anularPiloto(sainz)
        anularPiloto(leclerc)
    }

    /**
     * anulamos las posicioes de los jugadores antes de moverlos
     * @param piloto
     */
    private fun anularPiloto(piloto: Piloto){
        circuito[piloto.fila][piloto.columna]=null
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

    private fun posicionamiento(piloto: Piloto){
        if (piloto.isDnf){
            println("${piloto.nombre} DNF")
        }else{
            if (piloto.vuelta>=3){
                println("${piloto.nombre} HA TERMINADO")
                piloto.isTerminada=true
            }
            else circuito[piloto.fila][piloto.columna]=piloto
        }
    }
    private fun posicionarPilotos() {
        posicionamiento(max)
        posicionamiento(checo)

        posicionamiento(alonso)
        posicionamiento(stroll)

        posicionamiento(hamilton)
        posicionamiento(russel)

        posicionamiento(sainz)
        posicionamiento(leclerc)

    }


}

   //private operator fun LocalDate.minus(tiempoFinal: LocalDate?): LocalDate {
      //  return this - tiempoFinal
   //}
