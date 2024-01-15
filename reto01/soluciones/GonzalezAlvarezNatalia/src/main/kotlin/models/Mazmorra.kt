package org.example.models

/**
 * Clase que representa todas las acciones del juego
 *
 * @param TAM_MAZMORRA tamaño de filas y columnas de la matriz cuadrada
 * @param NUM_PERSONAJE unidades de cada personaje a posicionar en el tablero
 * @param NUM_DEMENTORES unidades de dementores a posicionar en el tablero
 * @param NUM_HORROCRUXES unidades de horrocruxes a posicionar en el tablero
 */

private const val TAM_MAZMORRA = 6
private const val NUM_PERSONAJE = 1
private const val NUM_DEMENTORES = 6
private const val NUM_HORROCRUXES = 7

class Mazmorra{
    /**
     * @param mazmorra matriz bidimensional que representa el tablero
     * @param potter variable que llama a la clase Potter()
     * @param horrocrux variable que llama a la clase Horrocrux()
     * @param dementor variable que llama a la clase Dementor()
     * @param direccion variable que llama a la clase Direccion()
     * @param posicion variable que almacena la posición de Potter()
     */
    private val mazmorra : Array<Array<Any?>> = Array(TAM_MAZMORRA){arrayOfNulls<Any>(TAM_MAZMORRA)}
    private val potter : Potter = Potter(100)
    private val horrocrux : Horrocrux = Horrocrux()
    private val dementor : Dementor = Dementor(10)

    private val direccion : Direccion = Direccion()
    private var posicion = posicionHarry(mazmorra)

    /**
     * Función que inicializa la matriz posicionando los diferentes elementos en ella.
     *
     * @author Natalia González
     * @see posicionAleatoria
     * @return matriz con los personajes posicionados aleatoriamente
     * @since 1.0-SNAPSHOT
     */
    fun inicializarMazmorra() : Array<Array<Any?>>{
        repeat(NUM_PERSONAJE){
            posicionAleatoria(Bellatrix(30), mazmorra)
        }
        repeat(NUM_DEMENTORES){
            posicionAleatoria(Dementor(10), mazmorra)
        }
        repeat(NUM_PERSONAJE){
            posicionAleatoria(Granger(30), mazmorra)
        }
        repeat(NUM_HORROCRUXES){
            posicionAleatoria(Horrocrux(), mazmorra)
        }
        repeat(NUM_PERSONAJE){
            posicionAleatoria(McGonagall(70), mazmorra)
        }
        repeat(NUM_PERSONAJE){
            posicionAleatoria(Ron(20, 10), mazmorra)
        }
        repeat(NUM_PERSONAJE){
            posicionAleatoria(Voldemort(70), mazmorra)
        }
        return mazmorra
    }

    /**
     * Función que asigna una posición aleatoria al elemento que se le indique
     *
     * @param item elemento que se posiciona aleatoriamente dentro de la matriz
     * @param mazmorra matriz bidimensional
     * @author Natalia González
     * @return matriz con las posiciones aleatorias
     * @since 1.0-SNAPSHOT
     */
    fun posicionAleatoria(item: Any?, mazmorra: Array<Array<Any?>>) : Array<Array<Any?>> {
        var columna : Int
        var fila : Int
        do {
            fila = (0 until TAM_MAZMORRA).random()
            columna = (0 until TAM_MAZMORRA).random()
        } while (mazmorra[fila][columna] != null)
        mazmorra[fila][columna] = item
        return mazmorra
    }

    /**
     * Función que asigna a una celda de la matriz la clase Potter.
     *
     * @param mazmorra matriz bidimensional
     * @author Natalia González
     * @return vector de la posición de la clase Potter
     * @since 1.0-SNAPSHOT
     */
    fun posicionHarry(mazmorra: Array<Array<Any?>>) : Array<Int> {
        val fila : Int = 0
        val columna : Int = 0

        for (i in mazmorra.indices){
            for (j in mazmorra[i].indices){
                mazmorra[fila][columna] = Potter(100)
            }
        }
        return arrayOf(fila,columna)
    }

    /**
     * Función que imprime la matriz con todos los diferentes elementos posicionados dentro en ella.
     *
     * Si la celda ya ha sido visitada por el jugador se muestra el elemento indicado, si no un signo de interrogación.
     *
     * @param mazmorra matriz bidimensional
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    private fun imprimirMazmorra(mazmorra: Array<Array<Any?>>){
        for (fila in mazmorra.indices){
            for (columna in mazmorra[fila].indices){
                if (mazmorra[fila][columna] is Potter) {
                    print("[👓]")
                }else if (mazmorra[fila][columna] == Celda.VISITED){
                    when (mazmorra[fila][columna]) {
                        is Bellatrix -> print("[🃏]")
                        is Dementor -> print("[👻]")
                        is Granger -> print("[👩🏻]")
                        is Horrocrux -> print("[🟡]")
                        is McGonagall -> print("[👵🏻]")
                        is Ron -> print("[👨🏻‍🦰]")
                        is Voldemort -> print("[💀]")
                        else -> print("[👣]")
                    }
                }else{print("[❓]")}
            }
            println()
        }
    }

    /**
     * Función que permite al jugador moverse por la matriz.
     *
     * @param mazmorra matriz bidimensional
     * @param posicion variable que almacena la posición de Potter()
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    fun movernos(mazmorra: Array<Array<Any?>>, posicion: Array<Int>){
        do {
            println("¿Hacia dónde quieres moverte?")
            println("W-arriba, S-abajo, D-derecha, A-izquierda")
            val opcion : String = readln().toUpperCase()

            val nuevaPosicion = direccion.mover(opcion, this.posicion)

            if (posicionValida(nuevaPosicion, mazmorra)) {
                mazmorra[this.posicion[0]][this.posicion[1]] = null

                mostrarCelda(mazmorra)

                this.posicion[0] = nuevaPosicion[0]
                this.posicion[1] = nuevaPosicion[1]

                accionPersonajes()

                mazmorra[this.posicion[0]][this.posicion[1]] = Potter(100)

            }else{
                mazmorra[this.posicion[0]][this.posicion[1]] = Potter(100)
                println("No te puedes mover hacia ese sentido.")
            }
        }while(!posicionValida(posicion, mazmorra))
    }

    /**
     * Función que marca la celda por la que ha pasado el jugador como visitada.
     *
     * @param mazmorra matriz bidimensional
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    fun mostrarCelda(mazmorra: Array<Array<Any?>>){
        if (mazmorra[this.posicion[0]][this.posicion[1]] == null){
            mazmorra[this.posicion[0]][this.posicion[1]] = Celda.VISITED
        }
    }

    /**
     * Función que verifica que la posición a la cual se quiere mover el jugador es válida dentro de la matriz.
     *
     * @param mazmorra matriz bidimensional
     * @param posicion variable que almacena la posición de Potter()
     * @author Natalia González
     * @return true si la posición es válida, false si no lo es
     * @since 1.0-SNAPSHOT
     */
    fun posicionValida(posicion: Array<Int>, mazmorra: Array<Array<Any?>>): Boolean {
        return !((posicion[1] < 0) || (posicion[1] > (mazmorra.size - 1)) || (posicion[0] < 0) || (posicion[0] > (mazmorra.size - 1)))
    }

    /**
     * Función que realiza diferentes acciones dependiendo de la clase que ocupe la celda en la que se posiciona el jugador.
     *
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    fun accionPersonajes() {
        when (mazmorra[this.posicion[0]][this.posicion[1]]) {
            null -> {
                println("Debo destruir los 7 horrocruxes👓")
            }
            is Horrocrux -> {
                horrocrux.total++
                println("¡Has destruido un Horrocrux!🟡")
            }
            is Voldemort -> {
                val voldemort : Voldemort = Voldemort(70)
                voldemort.atacar(potter)
                moverPersonajeAleatorio(item = Voldemort(70))
                println("Voldemort te ha hecho -${voldemort.daño} de daño y se ha movido a una nueva posición.💀")
            }
            is Bellatrix -> {
                val bellatrix : Bellatrix = Bellatrix(30)
                bellatrix.atacar(potter)
                moverPersonajeAleatorio(item = Bellatrix(30))
                println("Bellatrix te ha hecho -${bellatrix.daño} de daño y se ha movido a una nueva posición.🃏")
            }
            is Dementor -> {
                var decision : String
                do {
                    println("¿Qué quieres hacer?")
                    println("A-atacar D-defenderte")
                    decision = readln().toUpperCase()
                }while (decision!="A" && decision!="D")
                if (decision == "A") {
                    if ((0..100).random() <= 60) {
                        println("¡Has eliminado a un Dementor!")
                        dementor.total++
                    } else {
                        dementor.atacar(potter)
                        moverPersonajeAleatorio(item = Dementor(10))
                        println("El Dementor te ha hecho -${dementor.daño} de daño y se ha movido a una nueva posición.👻")
                    }
                } else if (decision == "D") {
                    moverPersonajeAleatorio(item = Dementor(10))
                    println("Te has defendido del Dementor y este se ha movido a una nueva posición.")
                }
            }
            is McGonagall -> {
                val mcGonagall = McGonagall(70)
                if (potter.vida < 100) {
                    mcGonagall.curar(potter)
                    println("McGonagall te da +${mcGonagall.cura} de cura.👵🏻")
                }else{
                    moverPersonajeAleatorio(item = McGonagall(70))
                    println("McGonagall no puede curarte. Se ha movido a una nueva posición.🧹")
                }
            }
            is Granger -> {
                val granger = Granger(30)
                if (potter.vida < 100) {
                    granger.curar(potter)
                    println("Hermione te da +${granger.cura} de cura.👩🏻")
                }else{
                    moverPersonajeAleatorio(item = Granger(30))
                    println("Hermione no puede curarte. Se ha movido a una nueva posición.🧹")
                }
            }
            is Ron -> {
                val ron = Ron(20,10)
                if (potter.vida<100){
                    if ((0..100).random() <= 30) {
                        ron.fallar(potter)
                        println("Ron se ha confundido de hechizo y te ha hecho -${ron.numFallo} de daño.")
                    }else{
                        ron.curar(potter)
                        println("Ron te da +${ron.cura} de cura.👨🏻‍🦰")
                    }
                }else{
                    moverPersonajeAleatorio(item = Ron(20,10))
                    println("Ron no puede curarte. Se ha movido a una nueva posición.🧹")
                }
            }
        }
    }

    /**
     * Función que asigna una posición aleatoria al elemento que se le indique.
     *
     * @param item elemento que se posiciona aleatoriamente dentro de la matriz
     * @author Natalia González
     * @return vector con la posición nueva
     * @since 1.0-SNAPSHOT
     */
    fun moverPersonajeAleatorio(item: Any?): Array<Int> {
        var nuevaFila: Int
        var nuevaColumna: Int
        do {
            nuevaFila = (0 until TAM_MAZMORRA).random()
            nuevaColumna = (0 until TAM_MAZMORRA).random()
        } while (mazmorra[nuevaFila][nuevaColumna] != null && mazmorra[nuevaFila][nuevaColumna] != Celda.VISITED)
        mazmorra[nuevaFila][nuevaColumna] = item
        return arrayOf(nuevaFila, nuevaColumna)
    }

    /**
     * Función que imprime un informe sobre la vida actual del jugador, los dementores muertos y los horrocruxes recogidos.
     *
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    private fun informe() {
        println("Vida: ${potter.vida}")
        println("Dementores muertos: ${dementor.total}")
        println("Horrocruxes destruidos: ${horrocrux.total}")
    }

    /**
     * Función que simula el desarrollo del juego.
     *
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    fun accionJuego(){
        println("¡Bienvenido a la Mazmorra Encantada!")
        println("Destruye los 7 horrocruxes")

        inicializarMazmorra()

        posicionHarry(mazmorra)

        imprimirMazmorra(mazmorra)

        informe()

        do {
            movernos(mazmorra, posicion)

            accionPersonajes()

            println()

            imprimirMazmorra(mazmorra)

            informe()

        }while (horrocrux.total != 7 && potter.vida > 0)

        if (horrocrux.total == 7){
            println("🌟¡Has logrado destruir los 7 horrocruxes!🌟")
        } else if (potter.vida == 0){
            println("☠️¡No has sobrevivido a la Mazmorra Encantada!☠️")
        }
    }
}