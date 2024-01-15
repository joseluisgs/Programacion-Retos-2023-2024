package models

const val MAP_SIZE = 6
private const val NUM_DEMENTOR = 6
private const val NUM_HORROCRUXES = 7

/**
 * Clase Mazmorra donde se ejecuta el juego
 * @author Jaime Leon Mulero
 * @since 1.0.0
 * @see exploracion
 */
class Mazmorra: RandomPlace {
    private val mapa: Array<Array<Celda>> = Array(MAP_SIZE) { Array(MAP_SIZE) { Celda() } }

    private val harry: Harry = Harry()

    private val horrocruxesRestantes: Int
        get() = getHorrocruxesLeft()

    /**
     * Función donde se ejecuta en tiempo real el programa
     * @author Jaime Leon Mulero
     * @since 1.0.0
     * @see placeInitialPlayers
     * @see printHiddenMap
     * @see moveHarry
     * @see printMap
     */
    fun exploracion() {
        placeInitialPlayers()

        do {
            printHiddenMap()
            //println()   //Línea para pruebas
            //printMap()   //Línea para pruebas
            println(harry)
            println("Horrocruxes restantes: $horrocruxesRestantes")
            moveHarry()
            println()
        } while (harry.vida > 0 && horrocruxesRestantes > 0)

        if (horrocruxesRestantes <= 0) {
            println("Has encontrado todos los Horrocruxes y has escapado de la mazmorra")
        } else {
            println("Voldermort, Bellatrix y los Dementores te han derrotado y has muerto")
            println("~ GAME OVER ~")
        }
        println()
        printMap()

    }

    /**
     * Función que imprime el mapa con todos los objetos visibles
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun printMap() {
        for (fil in mapa.indices) {
            for (col in mapa[fil].indices) {
                when (mapa[fil][col].personaje) {
                    is Harry -> print("[ 🧙🏻‍♂️ ]")
                    is Voldermort -> print("[ 👺 ]")
                    is Bellatrix -> print("[ 😈 ]")
                    is Dementor -> print("[ 🧌 ]")
                    is Profesora -> print("[ 👩🏻‍🏫 ]")
                    is Hermione -> print("[ 👧🏽 ]")
                    is Ron -> print("[ 👦🏽 ]")
                    is Horrocruxes -> print("[ 💎 ]")
                    else -> print("[ ▪️ ]")
                }
            }
            println()
        }
    }

    /**
     * Funcion que imprime el mapa que ve el jugador con los objetos necesarios para la partida
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun printHiddenMap() {
        for (fil in mapa.indices) {
            for (col in mapa[fil].indices) {
                if (mapa[fil][col].personaje is Harry) {
                    print("[ 🧙🏻‍♂️ ]")
                } else if (mapa[fil][col].isVisited){
                    print("[ 👣 ]")
                } else {
                    print("[ ▪️ ]")
                }
            }
            println()
        }
    }

    /**
     * Funcion que coloca todos los objetos en el trablero por primera vez
     * @see selectRandomPlace
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun placeInitialPlayers() {
        mapa[0][0].personaje = Harry()

        val posicionVoldemort = selectRandomPlace(mapa)
        mapa[posicionVoldemort.fila][posicionVoldemort.columna].personaje = Voldermort()

        val posicionBellatrix = selectRandomPlace(mapa)
        mapa[posicionBellatrix.fila][posicionBellatrix.columna].personaje = Bellatrix()

        val posicionProfesora = selectRandomPlace(mapa)
        mapa[posicionProfesora.fila][posicionProfesora.columna].personaje = Profesora()

        val posicionHermione = selectRandomPlace(mapa)
        mapa[posicionHermione.fila][posicionHermione.columna].personaje = Hermione()

        val posicionRon = selectRandomPlace(mapa)
        mapa[posicionRon.fila][posicionRon.columna].personaje = Ron()

        repeat(NUM_DEMENTOR) {
            val posicionDementor = selectRandomPlace(mapa)
            mapa[posicionDementor.fila][posicionDementor.columna].personaje = Dementor()
        }

        repeat(NUM_HORROCRUXES) {
            val posicionHorrocruxes = selectRandomPlace(mapa)
            mapa[posicionHorrocruxes.fila][posicionHorrocruxes.columna].personaje = Horrocruxes()
        }
    }

    /**
     * Funcion que cuenta el numero de horrocruxes restantes
     * @return Numero de horrocruxes restantes
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun getHorrocruxesLeft(): Int {
        var contador = 0
        for (fil in mapa.indices) {
            for (col in mapa[fil].indices) {
                if (mapa[fil][col].personaje is Horrocruxes) contador++
            }
        }
        return contador
    }

    /**
     * Funcion que realiza el moviemiento del objeto Harry
     * @author Jaime Leon Mulero
     * @since 1.0.0
     * @see getHarryPosition
     * @see askNextMovement
     * @see checkMovement
     */
    private fun moveHarry() {
        val posHarry: Posicion = getHarryPosition()
        var movimiento: String

        do {
            movimiento = askNextMovement()
            checkMovement(movimiento, posHarry)
        } while (mapa[posHarry.fila][posHarry.columna].personaje != null)
    }

    /**
     * Funcion que comprueba el contenido de la celda a la cual nos vamos a mover
     * @param movimiento Tecla de dirección (WASD)
     * @param posHarry Posicion (fila y columna) donde se encuentra Harry antes del movimiento
     * @author Jaime Leon Mulero
     * @since 1.0.0
     * @see accion
     */
    private fun checkMovement(movimiento: String, posHarry: Posicion) {
        when (movimiento) {
            "w" -> if ((posHarry.fila - 1) in 0..5 && posHarry.columna in 0..5) {
                accion(posHarry.fila - 1,posHarry.columna)
                mapa[posHarry.fila][posHarry.columna].personaje = null
                mapa[posHarry.fila][posHarry.columna].isVisited = true
            }

            "a" -> if (posHarry.fila in 0..5 && (posHarry.columna - 1) in 0..5) {
                accion(posHarry.fila,posHarry.columna - 1)
                mapa[posHarry.fila][posHarry.columna].personaje = null
                mapa[posHarry.fila][posHarry.columna].isVisited = true
            }

            "s" -> if ((posHarry.fila + 1) in 0..5 && posHarry.columna in 0..5) {
                accion(posHarry.fila + 1,posHarry.columna)
                mapa[posHarry.fila][posHarry.columna].personaje = null
                mapa[posHarry.fila][posHarry.columna].isVisited = true
            }

            "d" -> if (posHarry.fila in 0..5 && (posHarry.columna + 1) in 0..5) {
                accion(posHarry.fila,posHarry.columna + 1)
                mapa[posHarry.fila][posHarry.columna].personaje = null
                mapa[posHarry.fila][posHarry.columna].isVisited = true

            }
        }
    }

    /**
     * Funcion que realiza la acción dependiendo del contenido de la celda
     * @param fila
     * @param columna
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun accion(fila: Int, columna: Int) {
        when (mapa[fila][columna].personaje) {
            is Voldermort -> {
                val voldermort = Voldermort()
                voldermort.ataque(mapa, harry, "Voldermort")
                mapa[fila][columna].personaje = Harry()
            }
            is Bellatrix -> {
                val bellatrix = Bellatrix()
                bellatrix.ataque(mapa, harry, "Bellatrix" )
                mapa[fila][columna].personaje = Harry()
            }
            is Dementor -> {
                val dementor = Dementor()
                dementor.ataque(mapa, harry, "Dementor")
                mapa[fila][columna].personaje = Harry()
            }
            is Profesora -> {
                val profesora = Profesora()
                profesora.curar(harry)
                mapa[fila][columna].personaje = Harry()
            }
            is Hermione -> {
                val hermione = Hermione()
                hermione.curar(harry)
                mapa[fila][columna].personaje = Harry()
            }
            is Ron -> {
                val ron = Ron()
                ron.curar(harry)
                mapa[fila][columna].personaje = Harry()
            }
            is Horrocruxes -> {
                println("Te has encontrado un Horrocrux y lo destruyes, encuentra el resto para escapar de la mazmorra")
                mapa[fila][columna].personaje = Harry()
            }
            null -> mapa[fila][columna].personaje = Harry()
        }
    }

    /**
     * Funcion con la cual preguntamos el siguiente movimiento
     * @author Jaime Leon Mulero
     * @since 1.0.0
     * @return Devuelve la posición donde ir (WASD)
     */
    private fun askNextMovement(): String {
        var movimiento: String
        do {
            println("\nIntroduce el siguiente movimiento (WASD)")
            movimiento = readln().lowercase()
        } while (movimiento != "w" && movimiento != "a" && movimiento != "s" && movimiento != "d")
        return movimiento
    }

    /**
     * Función que saca la posición de Harry en el trablero
     * @return Devuelve la posicion en el objeto Posicion con fila y columna
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    private fun getHarryPosition(): Posicion {
        var posicion = Posicion(0,0)
        for (fil in mapa.indices) {
            for (col in mapa[fil].indices) {
                if (mapa[fil][col].personaje is Harry) {
                    posicion = Posicion(fil,col)
                }
            }
        }
        return posicion
    }
}