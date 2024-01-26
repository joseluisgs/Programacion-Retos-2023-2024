package org.example.models


import java.time.LocalTime


class SpaFrancorChamps {

    private var circuito: Array<Array<Parrilla?>> = Array(8) {  Array<Parrilla?>(10){null}  }

    private var maxmax = MaxMax()
    private var checo = ElChapo()
    private var padre = MiPadreTuPadreSuPadreNuestroPadre()
    private var stomp = Enchufao()
    private var crymilton = Crymilton()
    private var russell = Russell()
    private var carlinhos = Carlinhos()
    private var leclerc = Monegasco()

    private fun initCircuito() {
        for(i in circuito.indices){
            for(j in circuito[i].indices){
                if(j==0)
                when(i){
                    maxmax.fila -> circuito[i][j]=maxmax
                    checo.fila -> circuito[i][j]=checo
                    padre.fila -> circuito[i][j]=padre
                    stomp.fila -> circuito[i][j]=stomp
                    crymilton.fila -> circuito[i][j]=crymilton
                    russell.fila -> circuito[i][j]=russell
                    carlinhos.fila -> circuito[i][j]=carlinhos
                    leclerc.fila -> circuito[i][j]=leclerc
                }

            }
        }


    }



    fun carrera() {
        var tiempoFinalMax = maxmax.final
        var tiempoFinalCheco = checo.final
        var tiempoFinalPadre = padre.final
        var tiempoFinalStroll = stomp.final
        var tiempoFinalCrymilton = crymilton.final
        var tiempoFinalRussell = russell.final
        var tiempoFinalSainz = carlinhos.final
        var tiempoFinalLeclerc = leclerc.final

        var cont = 0

        println("Bienvenidos al GP de Belgica en el circuito de Spa-Francorchamps")

        println("Los pilotos se colocan en la parrilla de salida")

        initCircuito()
        imprimirCircuito()

        do {


            println()




            comprobarVuelta(maxmax)
            if (maxmax.vuelta < 3) {
                if (maxmax.magia()) {
                    println("Volvemos a ver volar a Max sin que se pueda hacer nada.")
                    pilotar(maxmax)
                }
            }
            comprobarVuelta(checo)
            if (checo.vuelta < 3) {
                if (checo.magia()) {
                    println("Volvemos a ver volar a Checo sin que se pueda hacer nada.")
                    pilotar(checo)
                }
            }
            comprobarVuelta(padre)
            if (padre.vuelta < 3) {
                if (padre.malaEstrategia()) {
                    println("Pero que es ese extraño movimiento por parte de los de Silverstone sobre el Nano")
                    espera(padre, 2)
                }
            }
            comprobarVuelta(stomp)
            if (stomp.vuelta < 3) {
                if (stomp.malaEstrategia()) {
                    println("Pero que es ese extraño movimiento por parte de los de Silverstone sobre Lance")
                    espera(stomp, 2)
                }
            }
            comprobarVuelta(crymilton)
            if (crymilton.vuelta < 3) {
                if (crymilton.calentada()) {
                    println("Volvemos a ver como Crymilton se calienta y provoca la salida de un virtual Safety Car")
                    retroceder(crymilton)
                }
            }
            comprobarVuelta(russell)
            if (russell.vuelta < 3) {
                if (russell.calentada()) {
                    println("Volvemos a ver como Russel se calienta y provoca la salida de un virtual Safety Car")
                    retroceder(russell)
                }
            }
            comprobarVuelta(carlinhos)
            if (carlinhos.vuelta < 3) {
                if (carlinhos.malaEstrategia()) {
                    println("Otra vez que el ingeniero de Carlos mira la telemetria al reves")
                    espera(carlinhos, 2)
                }
            }
            if (carlinhos.vuelta < 3) {
                if (carlinhos.falloFiabilidad()) {
                    println("Vaya que chorprecha Ferrari tiene que volver a parar a Carlos por problemas con su coche")
                    descalificacion(carlinhos)
                }
            }
            comprobarVuelta(leclerc)
            if (leclerc.vuelta < 3) {
                if (leclerc.malaEstrategia()) {
                    println("Otra vez que Leclerc decide arriesgar demasiado para su estrategia")
                    espera(leclerc, 2)
                }
            }
            if (leclerc.vuelta < 3) {
                if (leclerc.falloFiabilidad()) {
                    println("Vaya que chorprecha Ferrari tiene que volver a parar a Leclerc por problemas con su coche")
                    descalificacion(leclerc)
                }
            }


            Thread.sleep(100)
            if (cont == 0 &&
                (maxmax.vuelta == 1 ||
                        checo.vuelta == 1 ||
                        padre.vuelta == 1 ||
                        stomp.vuelta == 1 ||
                        crymilton.vuelta == 1 ||
                        russell.vuelta == 1 ||
                        carlinhos.vuelta == 1 ||
                        leclerc.vuelta == 1)
            ) {
                println("Comenzamos la segunda vuelta")
                cont++
            } else if (cont == 1 &&
                (maxmax.vuelta == 2 ||
                        checo.vuelta == 2 ||
                        padre.vuelta == 2 ||
                        stomp.vuelta == 2 ||
                        crymilton.vuelta == 2 ||
                        russell.vuelta == 2 ||
                        carlinhos.vuelta == 2 ||
                        leclerc.vuelta == 2)
            ) {
                println("Comenzamos la ultima vuelta")
                cont++
            }

            imprimirCircuito()


            //if(todosCompletaronVuelta(maxmax, checo, padre, stomp, crymilton, russell, carlinhos, leclerc)){
            //break
            //}

        } while (!todosCompletaronVuelta(maxmax, checo, padre, stomp, crymilton, russell, carlinhos, leclerc))

        when {
            maxmax.vuelta == 3 -> {
                tiempoFinalMax = maxmax.final
            }

            checo.vuelta == 3 -> {
                tiempoFinalCheco = checo.final
            }

            padre.vuelta == 3 -> {
                tiempoFinalPadre = padre.final
            }

            stomp.vuelta == 3 -> {
                tiempoFinalStroll = stomp.final
            }

            crymilton.vuelta == 3 -> {
                tiempoFinalCrymilton = checo.final
            }

            russell.vuelta == 3 -> {
                tiempoFinalRussell = russell.final
            }

            carlinhos.vuelta == 3 -> {
                tiempoFinalSainz = carlinhos.final
            }

            leclerc.vuelta == 3 -> {
                tiempoFinalLeclerc = leclerc.final
            }

        }
        if (maxmax.dnf) {
            println("Max no ha podido terminar la carrera debido a un accidente")
        } else {
            println("El tiempo final de ${maxmax.nombre} es: $tiempoFinalMax + ${maxmax.totalPits} segundos en pitstops")
        }
        if (checo.dnf) {
            println("Checo no ha podido terminar la carrera debido a un accidente")
        } else {
            println("El tiempo final de ${checo.nombre} es: $tiempoFinalCheco + ${checo.totalPits} segundos en pitstops")
        }
        if (padre.dnf) {
            println("Nuestro padre no ha logrado la 33 debido a un accidente")
        } else {
            println("El tiempo final de ${padre.nombre} es: $tiempoFinalPadre + ${padre.totalPits} segundos en pitstops")
        }
        if (stomp.dnf) {
            println("Stroll no ha podido terminar la carrera debido a un accidente")
        } else {
            println("El tiempo final de ${stomp.nombre} es: $tiempoFinalStroll + ${stomp.totalPits} segundos en pitstops")
        }
        if (crymilton.dnf) {
            println("Crymilton no ha podido terminar la carrera debido a un accidente")
        } else {
            println("El tiempo final de ${crymilton.nombre} es: $tiempoFinalCrymilton + ${crymilton.totalPits} segundos en pitstops")
        }
        if (russell.dnf) {
            println("Russell no ha podido terminar la carrera debido a un accidente")
        } else {
            println("El tiempo final de ${russell.nombre} es: $tiempoFinalRussell + ${russell.totalPits} segundos en pitstops")
        }
        if (carlinhos.dnf) {
            println("Carlos no ha podido terminar la carrera debido a una alcantarilla")
        } else {
            println("El tiempo final de ${carlinhos.nombre} es: $tiempoFinalSainz + ${carlinhos.totalPits} segundos en pitstops")
        }
        if (leclerc.dnf) {
            println("Leclerc no ha podido terminar la carrera debido a un incidente")
        } else {
            println("El tiempo final de ${leclerc.nombre} es: $tiempoFinalLeclerc + ${leclerc.totalPits} segundos en pitstops")
        }
    }
    private fun descalificacion(piloto: Parrilla) {
        piloto.dnf=true

    }


    private fun pilotar(piloto: Parrilla) {
        val filaAnterior = piloto.fila
        val columnaAnterior = piloto.columna
        var parada = 0
        if(piloto.dnf){
            circuito[piloto.fila][piloto.columna] = piloto
            piloto.vuelta=3
        }else{
            if (piloto.vuelta == 3) {
                circuito[piloto.fila][piloto.columna] = piloto
            } else {

                piloto.columna += 1

                if (piloto.columna == 5) {
                    println("Pitstop: ${piloto.nombre} está haciendo el pitstop")
                    when (piloto.vuelta) {
                        0 -> {
                            println("Duracion del pitstop ${piloto.pit1}")
                            do {
                                pitstop(piloto, piloto.pit1)
                                parada++
                            } while (parada < piloto.pit1)
                        }

                        1 -> {
                            println("Duracion del pitstop ${piloto.pit2}")
                            do {
                                pitstop(piloto, piloto.pit2)
                                parada++
                            } while (parada < piloto.pit2)
                        }

                        2 -> {
                            println("Duracion del pitstop ${piloto.pit3}")
                            do {
                                pitstop(piloto, piloto.pit3)
                                parada++
                            } while (parada < piloto.pit3)
                        }
                    }
                    println("Pitstop: ${piloto.nombre} ha terminado pitstop")
                }

                if (piloto.columna > 9) {
                    piloto.columna = 0
                    piloto.vuelta++
                }
                circuito[filaAnterior][columnaAnterior] = null
                circuito[piloto.fila][piloto.columna] = piloto
            }
        }

    }

fun retroceder(piloto: Parrilla){
    val filaAnterior = piloto.fila
    val columnaAnterior = piloto.columna
        if(piloto.columna!=0 && piloto.columna!=1)
        piloto.columna -= 2

        if (piloto.columna == 0) {
            piloto.columna = 0
        }else if(piloto.columna == 1){
            piloto.columna = 0
        }

        circuito[filaAnterior][columnaAnterior] = null
        circuito[piloto.fila][piloto.columna] = piloto

    }


    private fun espera(piloto: Parrilla, duracion: Int) {

        circuito[piloto.fila][piloto.columna] = piloto

        var tiempoTranscurrido = 0L
        while (tiempoTranscurrido < duracion * 100) {

            Thread.sleep(100)

            tiempoTranscurrido += 100
        }

    }

    private fun pitstop(piloto: Parrilla, duracion: Int) {

        piloto.columna = 5
        espera(piloto, duracion)
        circuito[piloto.fila][piloto.columna] = null
        piloto.columna = 6
    }


    fun comprobarVuelta(piloto: Parrilla): Boolean {
        if (piloto.vuelta < 3) {
            pilotar(piloto)
            return false
        } else if (piloto.vuelta == 3) {
            circuito[piloto.fila][piloto.columna] = piloto
            return true
        } else {
            return true
        }
    }

    fun todosCompletaronVuelta(vararg pilotos: Parrilla): Boolean {
        for (piloto in pilotos) {
            if (piloto.vuelta < 3) {
                return false
            }
        }
        return true
    }

    private fun imprimirCircuito(){
        for(i in circuito.indices){
            for(j in circuito[i].indices){

                when(circuito[i][j]){
                    maxmax ->
                        if(i==maxmax.fila && j==maxmax.columna) {
                            print("[MV]")
                        }
                    checo ->

                        print ("[SP]")
                    padre ->

                        print ("[FA]")
                    stomp ->

                        print ("[LS]")
                    crymilton ->

                        print ("[LH]")
                    russell ->

                        print ("[GR]")
                    carlinhos ->

                        print ("[CS]")
                    leclerc ->

                        print ("[CL]")
                    null -> print("[  ]")
                }
            }
            println()
        }
    }
}