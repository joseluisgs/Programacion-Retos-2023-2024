package models

import java.time.LocalDate
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


    fun inicializarPremio(){
        posicionarPilotosInicio()
        imprimirCircuito()
        println()

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
        val vectorPilotos:Array<Piloto> = arrayOf(max,alonso,checo,hamilton,leclerc,russel,sainz,stroll)
        cambioTiempos()
        ordenar(vectorPilotos)

    }

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

    private fun cambiar(piloto: Piloto) {
        if (piloto.isDnf){
            piloto.tiempoFinal= LocalTime.now()
        }

    }

    private fun comprobarMeterologia(){
        if (contador==6){
            lluvia=lluvia()
            if (lluvia){
                println("ESTA EMPEZZANDO A LLOVER CAMBIO DE NEUMATICOS")
                pitstop()
                contador=0
            }
        }
    }

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
    private fun imprimirArray(arrayPersonas: Array<Piloto>) {
        println("EL PODIO QUEDARIA ASI:\uD83C\uDFC6")
        var indice=0
        for (i in arrayPersonas.indices){
            if (arrayPersonas[i].isDnf){
                indice=indice
            }
            else if (indice+1<=3){
                when(indice+1){
                    1->println("${indice+1}º\uD83E\uDD47-${arrayPersonas[indice].nombre}")
                    2->println("${indice+1}º\uD83E\uDD48-${arrayPersonas[indice].nombre}")
                    3->println("${indice+1}º\uD83E\uDD49-${arrayPersonas[indice].nombre}")
                }
                indice++
            }

        }
    }

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

    private fun infomePiloto(piloto: Piloto) {
        println("-${piloto.nombre} SUS TIEMPOS HAN SIDO:")
        println("   INICIO: ${piloto.tiempoInicio}")
        if (piloto.isTerminada) println("   FINAL: ${piloto.tiempoFinal}")
        else println("  FINAL: ${piloto.nombre} HA SUFRIDO DNF")
    }

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
    private fun comprobarPiloto(piloto: Piloto) {
        if (piloto.isTerminada && !piloto.tiempoFinalComprobada){
            piloto.tiempoFinal= LocalTime.now()
            piloto.tiempoFinalComprobada=true
            //piloto.tiempoFinal=piloto.tiempoInicio-piloto.tiempoFinal
            //piloto.timpoFinal-=piloto.tiempoInicio
        }
    }

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
    private fun posicinamientoInicial(piloto: Piloto){
        circuito[piloto.fila][piloto.columna]=piloto
        piloto.tiempoInicio= LocalTime.now()
    }


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
    private fun accionesRedBull(piloto: RedBull){
        if (!acabada(piloto as Piloto) && !piloto.isRapida  ) {
            piloto.isRapida=piloto.vueltaRapida()
        }
    }


    private fun accionesAstonMartin(piloto:AstonMartin){
        if (!acabada(piloto as Piloto)&& !piloto.isMalaEstrategia){
            piloto.isMalaEstrategia=piloto.malaEstrategia()
            if (piloto.isMalaEstrategia){
                println("${piloto.nombre} A SUFRIDO UNA MALA ESTRATEGIA")
                piloto.tiempoMalaEstrategia=2
            }
        }
    }

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

    private fun accidente(piloto: Piloto) {
        if (!piloto.isDnf){
            piloto.isDnf=piloto.accidenteF()
            if (piloto.isDnf) println("${piloto.nombre} HA SUFRIDO UN ACCIDENTE EN SU VUELTA ${piloto.vuelta}")
        }
    }

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
    private fun pitStopPiloto(piloto: Piloto){
        if (piloto.tiempoPitstop==0) {
            piloto.tiempoPitstop = piloto.pitStop()
        }
    }


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
    private fun anularPiloto(piloto: Piloto){
        circuito[piloto.fila][piloto.columna]=null
    }
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
