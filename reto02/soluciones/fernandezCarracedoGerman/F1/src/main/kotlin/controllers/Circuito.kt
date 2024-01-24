package org.example.controllers

import org.example.models.*
import org.lighthousegames.logging.logging
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyles.*
import com.github.ajalt.mordant.rendering.TextAlign.*
import com.github.ajalt.mordant.widgets.Text

private const val CARRILES_CIRCUITO = 8
private const val COLUMNA_FIN_CIRCUITO = 9
private const val COLUMNA_VUELTA_RAPIDA = 5 // (Posición 6 del circuito) Punto donde comprobamos si será una vuelta rápida
private const val COLUMNA_PIT_STOP = 4 // (Posición 5 del circuito) Punto donde los coches entran a boxes
private const val NUM_VUELTAS_CARRERA = 3
private const val NUM_PILOTOS_PARTICIPANTES = 8
private const val PROB_LLUVIA = 35
private const val TIEMPO_COMPROBACION_LLUVIA = 6000 // 6 segundos
private const val PAUSA = 1000

private val log = logging()
private val term= Terminal()

/**
 * Clase que representa el circuito y gestiona la simulación de la carrera
 *
 * @property tiempoCarrera Contador de tiempo de carrera desde su inicio hasta su fin
 * @property pista Array<Array<Piloto>> Matriz que representa la pista del circuito
 * @property pilotosActivos Contador de pilotos que siguen en carrera
 * @property pilotosDescalificados Contador de pilotos descalificados por diferentes circunstancias de carrera
 * @property clima Estado actual del tiempo en el circuito
 * @property listaPilotos Lista que representa la Parrilla de salida con los pilotos participantes
 * @property listaPilotosOrdenadaTiempos Lista con los pilotos ordenada por tiempo de carrera
 * @property max Piloto Max Verstappen
 * @property checo Piloto Checo Pérez
 * @property alonso Piloto Fernando Alonso
 * @property stroll Piloto Lance Stroll
 * @property hamilton Piloto Lewis Hamilton
 * @property russell Piloto George Russell
 * @property sainz Piloto Carlos Sainz
 * @property leclerc Piloto Charles Leclerc
 * @see inicializarCircuito
 * @see carrera
 * @see comprobarCambioClima
 * @see comprobarEfectosEstrategia
 * @see comprobarAccidente
 * @see mostrarPista
 * @see mostrarPodio
 * @see moverPilotos
 */

class Circuito {
    private var tiempoCarrera = 0
    private var pista = Array (CARRILES_CIRCUITO) { arrayOfNulls<Piloto>(COLUMNA_FIN_CIRCUITO) }
    private var pilotosActivos:Int = NUM_PILOTOS_PARTICIPANTES
    private var pilotosDescalificados:Int = 0
    private var clima:Clima = Clima.SECO

    private var max = Piloto("Max Verstappen","VER",5,Posicion(0,0), RedBull())
    private var checo = Piloto("Checo Pérez", "PER",10, Posicion(1,0), RedBull())
    private var alonso = Piloto("Fernando Alonso", "ALO",5,Posicion(2,0), AstonMartin())
    private var stroll = Piloto("Lance Stroll","STR", 20, Posicion(3,0), AstonMartin())
    private var hamilton = Piloto("Lewis Hamilton", "HAM",10, Posicion(4,0), Mercedes())
    private var russell = Piloto("George Russell", "RUS",15, Posicion(5,0),  Mercedes())
    private var sainz = Piloto("Carlos Sainz", "SAI", 10, Posicion(6,0), Ferrari())
    private var leclerc = Piloto("Charles Leclerc", "LEC", 20, Posicion(7,0), Ferrari())

    private var listaPilotos: Array<Piloto> = arrayOf(max,checo,alonso,stroll,hamilton,russell,sainz,leclerc)
    private var listaPilotosOrdenadaTiempos = ordenarListaPilotosTiempos()

    init{
        log.debug {"Inicializando Gran Premio"}

        inicializarCircuito()

        mostrarPista()
    }


    /**
     *  Actualiza el icono para representar al piloto en función de su estado actual en la carrera
     */
    private fun actualizarIconoPiloto(piloto: Piloto) {
        piloto.icono = when(piloto.estado){
            EstadoPiloto.CARRERA -> "🏎️"
            EstadoPiloto.DNF ->"💥"
            EstadoPiloto.META -> "🟢"
            EstadoPiloto.PITSTOP -> "🔧"
            EstadoPiloto.MALA_ESTRATEGIA -> "💩"
            EstadoPiloto.ERROR_SAFETY_CAR  -> "🚨"
            EstadoPiloto.VUELTA_RAPIDA -> "🟣"
        }
    }

    /**
     * Muestra la pista y a los pilotos en su posición actual, con el icono correspondiente a su estado actual de carrera
     * En la parte derecha de la pista se muestra el estado de los pilotos, y sus tiempos de llegada a meta
     */
    private fun mostrarPista() {

        val strTiempoCarrera = if (tiempoCarrera<10000) "0${tiempoCarrera / 1000}" else "${tiempoCarrera / 1000}"

        print("🧱" + "🟥⬜".repeat(COLUMNA_FIN_CIRCUITO / 2) + "🟥🧱\t\t\t\t\t")
        term.println(bold(brightBlue("╔════ ORDEN DE CARRERA ═══════════ [ $strTiempoCarrera sec. ] ═══╗")))

        for (i in 0..<CARRILES_CIRCUITO){
            print("🏁")
            for(j in 0..<COLUMNA_FIN_CIRCUITO){
                val piloto = listaPilotos[i]
                if(piloto.posicion.carril == i && piloto.posicion.columna == j ) {
                    actualizarIconoPiloto(piloto)
                    print(piloto.icono)
                } else {
                    print("⬜")
                }
            }
            print("🏁 ${listaPilotos[i].abrNombre}   V: ${listaPilotos[i].vueltaCarrera}\t\t")
            mostrarLineaTextoPiloto(listaPilotosOrdenadaTiempos[i])
            println()
        }

        print("🧱" + "🟥⬜".repeat(COLUMNA_FIN_CIRCUITO/2) + "🟥🧱\t\t\t\t\t")
        term.println(bold(brightBlue("╚═════════════════════════════════════════════════╝")))
    }

    /**
     * Inicializa el tiempo de carrera, la lista de pilotos y sus tiempos
     */
    private fun inicializarCircuito() {

        tiempoCarrera = 0

        colocarPilotosEnPista()

        inicializarTiemposPilotos()

        log.debug { "Circuito inicializado" }
    }

    /**
     * Coloca a los pilotos en la primera columna de la pista, según su orden en la lista de pilotos
     */
    private fun colocarPilotosEnPista() {
        for (i in 0..<listaPilotos.size) {
            pista[i][0] = listaPilotos[i]
        }
    }

    /**
     * Simulación de la carrera: calcula el movimiento de los pilotos y muestra la pista en función de este,
     * comprueba la climatología, actualiza el tiempo de carrera, el orden de los pilotos, los que siguen activos o
     * han sido descalificados y, cuando todos los pilotos han llegado a la meta o hayan sido descalificados, muestra
     * el podio final
     */
    fun carrera(){
        println()
        term.println(bold(red("🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁")))
        term.println(bold(red("🏁🏁🏁¡¡C O M I E N Z A  L A  C A R R E R A !!🏁🏁🏁")))
        term.println(bold(red("🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁")))

        do {

            log.debug { "INICIO SEGUNDO ${tiempoCarrera/1000} \n" }

            moverPilotos()

            listaPilotosOrdenadaTiempos = ordenarListaPilotosTiempos()

            if (((tiempoCarrera>0) && (tiempoCarrera % TIEMPO_COMPROBACION_LLUVIA == 0)) && comprobarCambioClima()) hacerPitStopClima()

            mostrarPista()

            pilotosActivos = calcularPilotosActivos()
            log.debug { "Pilotos activos: $pilotosActivos || Pilotos DNF: $pilotosDescalificados" }

            Thread.sleep(PAUSA.toLong())

            log.debug { "FIN SEGUNDO ${tiempoCarrera/1000} \n" }
            tiempoCarrera += PAUSA


        } while (pilotosActivos>0)

        mostrarPodio()
    }


    /**
     * Los pilotos hacen un pitstop por cambio en el clima. En función de su estado actual en la carrera, actuarán de una
     * forma u otra: Si ya estaban el el pitstop aprovecharán a cambiar al neumático adecuado al nuevo clima, y si estaban
     * afectados por una estratega anterior, finalizará ya sea buena o mala y entrarán a boxes.
     * Si ya había llegado a meta o había sido descalificado, no hace el pitstop.
     * @see comprobarCambioClima
     */
    private fun hacerPitStopClima() {
        log.debug { "🔧☀️🌧️ ¡¡ Cambio en el tiempo, los pilotos entran para cambiar neumáticos !! 🌧️☀️🔧" }
        for (piloto in listaPilotos){
            when (piloto.estado){
                EstadoPiloto.CARRERA -> {
                    piloto.hacerPitStop()
                }
                EstadoPiloto.VUELTA_RAPIDA -> {
                    finVueltaRapida(piloto)
                    piloto.hacerPitStop()
                    }
                EstadoPiloto.MALA_ESTRATEGIA -> {
                    finMalaEstrategia(piloto)
                    piloto.hacerPitStop()
                    }
                EstadoPiloto.ERROR_SAFETY_CAR -> {
                    finErrorSafetyCar(piloto)
                    piloto.hacerPitStop()
                    }
                EstadoPiloto.PITSTOP -> {
                    log.debug { "🔧☀️🌧️ ${piloto.nombre} ya estaba en el Pit y aprovecha a cambiar neumáticos 🌧️☀️🔧" }
                }
                else -> {} // nada: si está DNF o ha llegado a la meta, no le afecta
            }
        }
    }

    /**
     * Comprueba si se produce un cambio en el clima, en función de la probabilidad de lluvia del circuito.
     * Primero comprobamos si llueve y en función de eso, si ha cambiado el clima
     * @return Boolean Verdadero si el clima ha cambiado
     * @see hacerPitStopClima
     */
    private fun comprobarCambioClima():Boolean {
        val hayLluvia = (0..<100).random() < PROB_LLUVIA
        var hayCambio = false

        if (clima==Clima.LLUVIA && hayLluvia) {
            hayCambio = false
            log.debug { "🌧️ ¡¡ Sigue lloviendo !! 🌧️" }
            return hayCambio
        }
        if (clima==Clima.LLUVIA && !hayLluvia){
            hayCambio = true
            clima = Clima.SECO
            log.debug { "☀️ ¡¡ Cambio de tiempo: Ha dejado de llover !! ☀️" }
            return hayCambio
        }
        if (clima==Clima.SECO && hayLluvia) {
            hayCambio = true
            clima = Clima.LLUVIA
            log.debug { "🌧️ ¡¡ Cambio de tiempo: Ha empezado a llover !! 🌧️" }
            return hayCambio
        }
        if (clima==Clima.SECO && !hayLluvia) {
            hayCambio = false
            log.debug { "☀️ ¡¡ Seguimos con sol !! ☀️" }
            return hayCambio
        }
        return hayCambio
    }

    /**
     * Recorre la lista de pilotos y cuenta los que siguen activos, es decir, no han sido descalificados ni han
     * llegado a meta
     * @return Número de pilotos activos
     */
    private fun calcularPilotosActivos(): Int {
        var cuenta = 0

        for (piloto in listaPilotos)
            if (piloto.estado!=EstadoPiloto.DNF && piloto.estado!=EstadoPiloto.META) cuenta ++

        return cuenta
    }

    /**
     * Inicializa a 0 los tiempos de carrera de todos los pilotos
     */
    private fun inicializarTiemposPilotos() {
        for (piloto in listaPilotos){
            piloto.tiempoCarrera = 0
        }
    }

    /**
     * Para cada piloto, lo coloca (actualiza su atributo posición) tras calcular y actualizar los atributos que
     * sirven para gestionar su avance, retroceso o segundos de parada.
     *
     * Las acciones y comprobaciones a realizar dependen del estado actual del piloto en carrera y de su posición en
     * la pista
     */
    private fun moverPilotos() {
        for (piloto in listaPilotos) {
            when (piloto.estado) {
                EstadoPiloto.CARRERA -> {
                    comprobarHacerPitStop(piloto)
                    comprobarAccidente(piloto)
                    if (piloto.estado!=EstadoPiloto.DNF && piloto.estado!=EstadoPiloto.PITSTOP){
                        comprobarEfectosEstrategia(piloto)
                    }
                }
                EstadoPiloto.PITSTOP -> {
                    comprobarHacerPitStop(piloto) // comprobamos si ya terminó y vuelve a la carrera
                }
                EstadoPiloto.MALA_ESTRATEGIA -> {
                    comprobarFinMalaEstrategia(piloto)
                }
                EstadoPiloto.ERROR_SAFETY_CAR ->{
                    finErrorSafetyCar(piloto)
                }
                EstadoPiloto.VUELTA_RAPIDA ->{
                    finVueltaRapida(piloto)
                }
                else -> {} // no hacemos nada
            }

            // Colocar al piloto en función de su estado, modificado por eventos y estrategias anteriores
            colocarPiloto(piloto)

            // Incrementamos el tiempo de carrera del piloto solo si sigue en la misma
            if (piloto.estado!=EstadoPiloto.META && piloto.estado!=EstadoPiloto.DNF) piloto.tiempoCarrera += PAUSA
        }
    }

    /**
     * Comprobamos si el piloto estaba afectado por la estrategia y la finalizamos volviendo al estado normal de carrera
     * En este caso la estrategia no tiene duración, solo es volver al estado de carrera
     */
    private fun finErrorSafetyCar(piloto: Piloto) {
        if (piloto.estado == EstadoPiloto.ERROR_SAFETY_CAR) {
            piloto.estado = EstadoPiloto.CARRERA
            piloto.avance = 1
            piloto.icono = "🏎️"
            log.debug { "🚨 ${piloto.nombre} Reanuda tras error del equipo y SC 🚨" }
        }
    }

    /**
     * Comprobamos si el piloto estaba afectado por la estrategia y la finalizamos volviendo al estado normal de carrera
     * En este caso la estrategia no tiene duración, solo es volver al estado de carrera
     */
    private fun finVueltaRapida(piloto: Piloto) {
        if (piloto.estado == EstadoPiloto.VUELTA_RAPIDA) {
            piloto.estado = EstadoPiloto.CARRERA
            piloto.avance = 1
            piloto.icono = "🏎️"
        }
    }

    /**
     * Comprobamos si el piloto estaba afectado por la estrategia y la finalizamos volviendo al estado normal de carrera
     * En este caso la estrategia tiene duración, por lo que comprobaremos además si ha pasado el tiempo necesario para
     * que finalice
     */
    private fun comprobarFinMalaEstrategia(piloto: Piloto) {
        if (piloto.estado == EstadoPiloto.MALA_ESTRATEGIA) {
            piloto.segundosStop --
            if (piloto.segundosStop < 0){
                finMalaEstrategia(piloto)
            }
        }
    }

    /**
     * Comprobamos si el piloto estaba afectado por la estrategia y la finalizamos volviendo al estado normal de carrera
     * En este caso la estrategia no tiene duración, solo es volver al estado de carrera
     */
    private fun finMalaEstrategia(piloto: Piloto) {
        piloto.estado = EstadoPiloto.CARRERA
        piloto.segundosStop = 0
        piloto.icono = "🏎️"
        log.debug { "💩 ${piloto.nombre} Reanuda tras mala estrategia del equipo 💩" }
    }

    /**
     * Comprobamos si el piloto finalizó la parada en boxes (si ha pasado el tiempo necesario) y tiene que volver al
     * estado normal de carrera.
     * @see terminarPitStop
     */
    private fun comprobarHacerPitStop(piloto: Piloto) {
        if (piloto.estado == EstadoPiloto.PITSTOP) {
            piloto.segundosStop --
            if (piloto.segundosStop < 0){
                terminarPitStop(piloto)
            }
        } else {
            if (piloto.posicion.columna == COLUMNA_PIT_STOP) {
                piloto.hacerPitStop()
            }
        }
    }

    /**
     * Finalizamos el pitstop volviendo al estado normal de carrera
     * En este caso la estrategia no tiene duración, solo es volver al estado de carrera
     * @see comprobarHacerPitStop
     */
    private fun terminarPitStop(piloto: Piloto) {
        piloto.estado = EstadoPiloto.CARRERA
        piloto.segundosStop = 0
        piloto.icono = "🏎️"
        log.debug { "🔧 ${piloto.nombre} sale de boxes 🔧" }
    }

    /**
     * Comprobamos si el piloto tiene un accidente
     */
    private fun comprobarAccidente(piloto: Piloto) {
        if (piloto.tieneAccidente()) {
            piloto.provocarAccidente()
            pilotosDescalificados ++
        }
    }

    /**
     * Comprobamos para un piloto si la/s estrategia/s de su equipo tiene/n efecto
     */
    private fun comprobarEfectosEstrategia(piloto: Piloto) {
        when (val equipo = piloto.equipo){
            is RedBull ->
                //  Solo se puede hacer una vuelta rápida por vuelta, y se comprueba pasada la mitad de la vuelta
                if (piloto.posicion.columna == COLUMNA_VUELTA_RAPIDA) {
                    if ((0..<100).random() < equipo.probVueltaRapida) equipo.vueltaRapida(piloto)
                }
            is AstonMartin -> if ((0..<100).random() < equipo.probMalaEstrategia) equipo.malaEstrategia(piloto)
            is Mercedes -> if ((0..<100).random() < equipo.probErrorEquipo) equipo.errorEquipo(piloto)
            is Ferrari -> {
                if ((0..<100).random() < equipo.probMalaEstrategia) equipo.malaEstrategia(piloto)
                if ((0..<100).random() < equipo.probProblemaFiabilidad) {
                    equipo.problemaFiabilidad(piloto)
                    pilotosDescalificados++
                }
            }
        }
    }

    /**
     * Actualiza la Posición del piloto en función de su avance, posición actual y segundos de "penalización" por las
     * estrategias que haya sufrido
     */
    private fun colocarPiloto(piloto: Piloto) {

        val moverPiloto:Boolean = (
            piloto.estado == EstadoPiloto.CARRERA
            || piloto.estado == EstadoPiloto.VUELTA_RAPIDA
            || piloto.estado == EstadoPiloto.ERROR_SAFETY_CAR)

        if (moverPiloto) {

            val colInicial = piloto.posicion.columna
            piloto.posicion.columna += piloto.avance

            if (piloto.posicion.columna < 0 && piloto.avance < 0) {
                // Volvemos hacia atrás en mi carril y vuelvo a la vuelta anterior
                // pero solo si no era justo en la salida de la carrera
                if (piloto.vueltaCarrera > 1) {
                    piloto.posicion.columna = COLUMNA_FIN_CIRCUITO + piloto.avance
                    piloto.vueltaCarrera --
                } else { // estaba en la salida, me quedo ahí
                    piloto.posicion.columna = 0
                }
            }

            if (piloto.posicion.columna == COLUMNA_FIN_CIRCUITO) {
                // Final de vuelta, empiezo nueva pero solo si no era la última
                if (piloto.vueltaCarrera <= NUM_VUELTAS_CARRERA) {
                    // el piloto avanza su avance actual menos lo que ya había recorrido hasta la última columna
                    piloto.posicion.columna = 0 + piloto.avance - (COLUMNA_FIN_CIRCUITO - colInicial)
                }

                piloto.vueltaCarrera ++

                if (piloto.vueltaCarrera > NUM_VUELTAS_CARRERA) {
                    log.debug { "🏁 ¡¡${piloto.nombre} llega a la meta!! 🏁" }
                    // ok pilotosActivos--
                    piloto.estado = EstadoPiloto.META
                }
            }
        }
    }

    /**
     * Mostramos el podio (solo los 3 primeros y si no están descalificados)
     * Si todos los pilotos están descalificados, no aparecerá el podio
     */
    private fun mostrarPodio() {
        term.println(bold(brightBlue("🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁")))
        term.println(bold(brightBlue("🏁🏁🏁  ¡¡ F I N     D E    C A R R E R A !!  🏁🏁🏁")))
        term.println(bold(brightBlue("🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁🏁")))

        if (NUM_PILOTOS_PARTICIPANTES-pilotosDescalificados == 0) {
            term.print(Text((red)("# TODOS LOS PILOTOS HAN SIDO DESCALIFICADOS #"), align = CENTER, width = 45))
            return
        }

        term.println(bold(brightBlue("╔════════ P O D I U M ════════╗")))
        if (listaPilotosOrdenadaTiempos[0].estado==EstadoPiloto.META){
            term.print(Text((brightYellow)("║ 1.- ${listaPilotosOrdenadaTiempos[0].abrNombre} "), align = LEFT, width = 20))
            term.print(Text((brightYellow)("${listaPilotosOrdenadaTiempos[0].tiempoCarrera / 1000} sec."), align = LEFT, width = 10))
            term.println(Text((brightYellow)("║"), align = LEFT, width = 1))
        }

        if (listaPilotosOrdenadaTiempos[1].estado==EstadoPiloto.META){
            term.print(Text((brightCyan)("║ 2.- ${listaPilotosOrdenadaTiempos[1].abrNombre} "), align = LEFT, width = 20))
            term.print(Text((brightCyan)("${listaPilotosOrdenadaTiempos[1].tiempoCarrera / 1000} sec."), align = LEFT, width = 10))
            term.println(Text((brightCyan)("║"), align = LEFT, width = 1))
        }

        if (listaPilotosOrdenadaTiempos[2].estado==EstadoPiloto.META){
            term.print(Text((brightMagenta)("║ 3.- ${listaPilotosOrdenadaTiempos[2].abrNombre} "), align = LEFT, width = 20))
            term.print(Text((brightMagenta)("${listaPilotosOrdenadaTiempos[2].tiempoCarrera / 1000} sec."), align = LEFT, width = 10))
            term.println(Text((brightMagenta)("║"), align = LEFT, width = 1))
        }

        term.println(bold(brightBlue("╚═════════════════════════════╝")))

    }

    /**
     * Muestra una línea con datos del piloto según su estado de carrera
     */
    private fun mostrarLineaTextoPiloto(piloto: Piloto) {
        when (piloto.estado){
            EstadoPiloto.DNF->{
                term.print(Text((red)("║  ${piloto.nombre} "), align = LEFT, width = 20))
                term.print(Text((red)("${piloto.equipo.nombre} "), align = LEFT, width = 20))
                term.print(Text((red)("DNF"), align = LEFT, width = 10))
                term.print(Text((red)("║"), align = LEFT))
            }
            EstadoPiloto.META -> {
                term.print(Text((green)("║  ${piloto.nombre} "), align = LEFT, width = 20))
                term.print(Text((green)("${piloto.equipo.nombre} "), align = LEFT, width = 20))
                term.print(Text((green)("${piloto.tiempoCarrera / 1000} sec."), align = LEFT, width = 10))
                term.print(Text((green)("║"), align = LEFT, width = 1))
            }
            else -> {
                term.print(Text((black)("║  ${piloto.nombre} "), align = LEFT, width = 20))
                term.print(Text((black)("${piloto.equipo.nombre} "), align = LEFT, width = 20))
                term.print(Text((black)(" "), align = LEFT, width = 10))
                term.print(Text((black)("║"), align = LEFT, width = 1))
            }
        }

    }

    /**
     * Copia una lista de pilotos origen sobre otra destino
     */
    private fun copiarLista(origen:Array<Piloto>,destino:Array<Piloto>):Array<Piloto>{
        for (i in origen.indices){
            destino[i] = origen[i]
        }
        return destino
    }

    /**
     * Ordena la lista de pilotos
     */
    private fun ordenarListaPilotosTiempos(): Array<Piloto> {
        var lista: Array<Piloto> = arrayOf(max,checo,alonso,stroll,hamilton,russell,sainz,leclerc)
        lista=copiarLista(listaPilotos,lista)

        var aux: Piloto
        for (i in 0 until lista.size) {
            for (j in 0 until lista.size - 1) {
                val tiempoAnterior = lista[j].tiempoCarrera
                val tiempoPosterior = lista[j + 1].tiempoCarrera
                if (tiempoAnterior > tiempoPosterior) {
                    aux = lista[j]
                    lista[j] = lista[j + 1]
                    lista[j + 1] = aux
                }
            }
        }

        return lista
    }
}

