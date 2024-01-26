package models

/**
 * Clase en la cual se desarrolla la carrera
 * @property circuito Matriz del circuito
 * @property verstappen Objeto piloto
 * @property checo objeto piloto
 * @property alonso objeto piloto
 * @property stroll objeto piloto
 * @property hamilton objeto piloto
 * @property russell objeto piloto
 * @property sainz objeto piloto
 * @property leclerc objeto piloto
 * @property arrayPilotos array con todos los pilotos
 */
class Carrera {
    private var circuito: Array<Array<Casilla>> = Array(8) { Array(10) { Casilla() } }

    private val verstappen = MaxVerstappen()
    private val checo = ChecoPerez()
    private val alonso = FernandoAlonso()
    private val stroll = LanceStroll()
    private val hamilton = LewisHamilton()
    private val russell = GeorgeRussell()
    private val sainz = CarlosSainz()
    private val leclerc = CharlesLeclerc()

    private val arrayPilotos = arrayOf(verstappen,checo,alonso,stroll,hamilton,russell,sainz,leclerc)

    /**
     * Función que ejecuta la simulación de la carrera
     * @see placeDrivers
     * @see printCircuito
     * @see moveAllDrivers
     * @see meteorologia
     * @see finalCarrera
     * @see podioDrivers
     * @see imprimirPodio
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    fun simulacion() {
        placeDrivers()
        println("\nPulsa cualquier tecla para comenzar la carrera")
        readln()
        println("Los pilotos se colocan en la parrilla de salida 🏎️")
        printCircuito()
        println()

        var time = 0
        var lluvia = false
        var countDown = 14
        do {
            when (countDown) {
                13 -> println("🔴⚫⚫⚫⚫")
                11 -> println("🔴🔴⚫⚫⚫")
                9 -> println("🔴🔴🔴⚫⚫")
                7 -> println("🔴🔴🔴🔴⚫")
                5 -> println("🔴🔴🔴🔴🔴")
                2 -> println("⚫⚫⚫⚫⚫")
                1 -> println("\n🟢 Comienza la carrera 🟢")
            }
            countDown--
            Thread.sleep(500) //500
        } while (countDown > 0)
        println()

        do {
            printCircuito()
            moveAllDrivers(time)
            if (time != 0 && time % 6 == 0) {
                lluvia = meteorologia(lluvia)
            }
            Thread.sleep(1000) //1000
            time++
            println()
        } while (!finalCarrera())

        val podio = podioDrivers()
        imprimirPodio(podio)
    }

    /**
     * Función que realiza el cambio de meteorologia
     * @param lluvia Boolean
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun meteorologia(lluvia: Boolean): Boolean {
        var lluviaFun = lluvia
        val random = (0..100).random()
        if (random <= 35) {
            if (!lluviaFun) {
                println("\nAparece un clima adverso de lluvia y los pilotos entran a boxes para cambiar los neumáticos 🌧️")
                lluviaFun = true
            } else {
                println("\nEl sol vuelve a asomar entre las nubes y los pilotos entran a boxes para cambiar los neumáticos ⛅")
                lluviaFun = false
            }
            Thread.sleep(3000)
        }
        return lluviaFun
    }

    /**
     * Función que ejecuta el movimiento de todos los pilotos a la vez
     * @param time tiempo
     * @see moveDriver
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun moveAllDrivers(time: Int) {
        moveDriver(verstappen,time)
        moveDriver(checo,time)
        moveDriver(alonso,time)
        moveDriver(stroll,time)
        moveDriver(hamilton,time)
        moveDriver(russell,time)
        moveDriver(sainz,time)
        moveDriver(leclerc,time)
    }

    /**
     * Función que ejecuta el movimiento de cada piloto
     * @param driver piloto
     * @param time tiempo
     * @see getDriverPosition
     * @see condicionesEspeciales
     * @see mensajesVueltas
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun moveDriver(driver: Piloto, time: Int) {
        var posicionDriver = getDriverPosition(driver)
        if (driver.contadorPitStop != 0) {
            driver.contadorPitStop -= 1
        } else {
            condicionesEspeciales(driver)
            posicionDriver = getDriverPosition(driver)
            mensajesVueltas(driver, posicionDriver, time)
            if (!driver.finMovement) {
                movementDriver(driver, posicionDriver)
                if (driver.accidente()) {
                    driver.finMovement = true
                    println("\n$driver ha tenido un accidente quedando fuera de la carrera \uD83D\uDCA5")
                    driver.emoticono = "🔥"
                }
            }
        }
        if (posicionDriver.columna == 4 && driver.contadorPitStop == 0) {
            val tiempoPitStop = (1..3).random()
            driver.contadorPitStop = tiempoPitStop
            println("\n${driver.nombre} entra en boxes durante $tiempoPitStop segundos")
        }
    }

    /**
     * Función que ejecuta las condiciones especiales de cada escuderia
     * @param driver piloto
     * @see getDriverPosition
     * @see movementDriver
     * @see movementDriverMinus
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun condicionesEspeciales(driver: Piloto) {
        var condicion: Boolean
        val posicionDriver = getDriverPosition(driver)
        if (!(posicionDriver.columna == 9 || posicionDriver.columna == 0 || posicionDriver.columna == 3 || posicionDriver.columna == 4 || posicionDriver.columna == 5)) {
            when (driver) {
                is RedBull -> {
                    condicion = driver.vueltaRapida()
                    if (condicion && !driver.finMovement) {
                        println("\n$driver del equipo RedBull ha hecho vuelta rápida 💨")
                        movementDriver(driver, posicionDriver)
                    }
                }
                is AstonMartin -> {
                    condicion = driver.malaEstrategia()
                    if (condicion && !driver.finMovement && driver.contadorPitStop == 0) {
                        println("\n$driver del equipo Aston Martin ha realizado una mala estrategia perdiendo 2 segundos 🕑")
                        driver.contadorPitStop = 2
                    }
                }
                is Mercedes -> {
                    condicion = driver.safetyCar()
                    if (condicion && !driver.finMovement) {
                        println("\n$driver del equipo Mercedes perdió una posición tras el Safety Car \uD83D\uDEA8")
                        movementDriverMinus(driver, posicionDriver)
                    }
                }
                is Ferrari -> {
                    condicion = driver.malaEstrategia()
                    if (condicion && !driver.finMovement && driver.contadorPitStop == 0) {
                        println("\n$driver del equipo Ferrari ha realizado una mala estrategia perdiendo 2 segundos 🕑")
                        driver.contadorPitStop = 2

                    }
                    condicion = driver.problemasFiabilidad()
                    if (condicion && !driver.finMovement) {
                        println("\n$driver del equipo Ferrari tuvo problemas de fiabilidad quedando el coche mecanicamente averiado 🛠️ \uD83D\uDEA8")
                        driver.finMovement = true
                        driver.emoticono = "🛠️"
                    }
                }
            }
        }
    }

    /**
     * Función que ejecuta los mensajes que se envian
     * @param driver piloto
     * @param posicionDriver posicion piloto
     * @param time tiempo
     */
    private fun mensajesVueltas(driver: Piloto, posicionDriver: Posicion, time: Int) {
        if (posicionDriver.columna == 9 && !driver.finMovement) {
            if (driver.vuelta == 3) {
                println("\n$driver ha terminado la carrera en $time segundos 🏁")
                driver.tiempo = time
                driver.meta = true
                driver.finMovement = true
            } else {
                println("\n$driver ha completado la vuelta ${driver.vuelta}")
            }
        }
    }

    /**
     * Funcion que saca la posicion de un piloto
     * @param driver piloto
     * @return Clase posicion con fila y columna
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun getDriverPosition(driver: Piloto): Posicion {
        var posicion = Posicion(0, 0)
        for (fil in circuito.indices) {
            for (col in circuito[fil].indices) {
                if (circuito[fil][col].piloto == driver) {
                    posicion = Posicion(fil, col)
                }
            }
        }
        return posicion
    }

    /**
     * Funcion que posiciona a todos los pilotos en la posicion inicial
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun placeDrivers() {
        circuito[0][0].piloto = verstappen
        circuito[1][0].piloto = checo
        circuito[2][0].piloto = alonso
        circuito[3][0].piloto = stroll
        circuito[4][0].piloto = hamilton
        circuito[5][0].piloto = russell
        circuito[6][0].piloto = sainz
        circuito[7][0].piloto = leclerc
    }

    /**
     * Funcion que imprime el circuito
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun printCircuito() {
        for (i in circuito.indices) {
            print("${arrayPilotos[i].siglas}:  ")
            for (j in circuito[i].indices) {
                when (circuito[i][j].piloto) {
                    verstappen -> print(verstappen.emoticono)
                    checo -> print(checo.emoticono)
                    alonso -> print(alonso.emoticono)
                    stroll -> print(stroll.emoticono)
                    hamilton -> print(hamilton.emoticono)
                    russell -> print(russell.emoticono)
                    sainz -> print(sainz.emoticono)
                    leclerc -> print(leclerc.emoticono)
                    null -> print("◼️")
                }
            }
            print("🏁")
            println()
        }
    }

    /**
     * Funcion que comprueba si todos los pilotos han terminado su movimiento
     * @return Boolean
     * @see getDriverPosition
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun finalCarrera(): Boolean{
        var finCarrera = false

        for (driver in arrayPilotos) {
            val posicionDriver = getDriverPosition(driver)
                if (circuito[posicionDriver.fila][posicionDriver.columna].piloto!!.finMovement) {
                    finCarrera = true
                } else {
                    finCarrera = false
                    break
                }
            }

        return finCarrera
    }

    /**
     * Funcion que ejecuta el movimiento de cada piloto
     * @param driver piloto
     * @param posicionDriver Clase posicion con fila y columna
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun movementDriver(driver: Piloto, posicionDriver: Posicion){
        if (posicionDriver.columna + 1 in 0..9) {
            circuito[posicionDriver.fila][posicionDriver.columna + 1].piloto = driver
            circuito[posicionDriver.fila][posicionDriver.columna].piloto = null
        } else {
            circuito[posicionDriver.fila][0].piloto = driver
            circuito[posicionDriver.fila][posicionDriver.columna].piloto = null
        }
        if (posicionDriver.columna == 9 && !driver.finMovement) driver.vuelta += 1
    }

    /**
     * Funcion que resta una posicion a un piloto
     * @param driver piloto
     * @param posicionDriver Clase posicion con fila y columna
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun movementDriverMinus(driver: Piloto, posicionDriver: Posicion) {
            circuito[posicionDriver.fila][posicionDriver.columna - 1].piloto = driver
            circuito[posicionDriver.fila][posicionDriver.columna].piloto = null
    }

    /**
     * Funcion que recoge todos los pilotos que han llegado a meta y los introduce en un array
     * @see countDnfDrivers
     * @return Array<Piloto?> Devuelve el array de pilotos
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun podioDrivers(): Array<Piloto?> {
        var index = 0
        val dnfDrivers = countDnfDrivers()
        val arrayDrivers = Array<Piloto?> (8 - dnfDrivers) { null }
        for (i in circuito.indices) {
            for (j in circuito[i].indices) {
                if (circuito[i][j].piloto?.meta == true) {
                    arrayDrivers[index] = circuito[i][j].piloto
                    index += 1
                }
            }
        }
        return arrayDrivers
    }

    /**
     * Funcion que cuenta cuantos pilotos han terminado sin llegar a meta
     * @return Int Devuelve el numero de pilotos
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun countDnfDrivers(): Int {
        var contador = 0
        for (i in circuito.indices) {
            for (j in circuito[i].indices) {
                if (circuito[i][j].piloto?.meta == false) {
                    contador += 1
                }
            }
        }
        return contador
    }

    /**
     * Funcion que imprime el podio de pilotos en orden por tiempo
     * @param podio Array de pilotos que han llegado a meta
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun imprimirPodio(podio: Array<Piloto?>) {
        orderPodio(podio)
        println("Resultado final de la carrera: \uD83C\uDFC6\n")
        for (i in podio.indices) {
            println("${i+1}º Posición: ${podio[i]?.nombre}")
            println("Tiempo: ${podio[i]?.tiempo}")
            println()
        }
    }

    /**
     * Funcion que ordena el podio por tiempo
     * @param array Array de pilotos que han llegado a meta sin ordenar
     */
    private fun orderPodio(array: Array<Piloto?>) {
        var aux: Piloto?
        for (i in array.indices) {
            for (j in 0 until array.size - 1) {
                if (array[j]!!.tiempo > array[j + 1]!!.tiempo) {
                    aux = array[j]
                    array[j] = array[j + 1]
                    array[j + 1] = aux
                }
            }
        }
    }
}