package models

import kotlin.random.Random

// Constantes para la cantidad de Dementores y Horrocruxes en el juego
const val NUDEMENTORES = 6
var NUMHORROCRUXES = 7

/**
 * Clase principal que representa el juego Azkaban.
 *
 * @property tamPrision Tamaño de la prisión.
 * @property numEnemigos Número de enemigos en la prisión.
 */
class Azkaban(
    private val tamPrision: Int = 6,
    private val numEnemigos: Int = 6
) {
    // Matriz que representa la prisión
    private val prision = Array(tamPrision) { arrayOfNulls<Any?>(tamPrision) }

    // Instancia de objetos
    private val Harry = Harry()
    private val Dementor = Dementor()
    private val Voldemort = Voldemort()
    private val Bellatrix = Bellatrix()
    private val Ron = Ron()
    private val McGonagall = McGonagall()
    private val Hermione = Hermione()
    private val Horrocruxe = Horrocruxe()

    /**
     * Inicialización del juego Azkaban.
     * Coloca a Harry en la posición inicial y genera la prisión.
     */
    init {
        Harry.generarHarry(prision)
        iniPrision()
    }
    /**
     * Genera los personajes de la matriz
     */
    fun iniPrision(){
        generarDementores()
        generarVoldemort()
        generarBellatrix()
        generarRon()
        generarHermione()
        generarMcGonagall()
        generarHorrocruxes()
    }

    /**
     * Método para imprimir el estado actual de la prisión.
     */
    fun printMap() {
        for (i in prision.indices) {
            for (j in prision[i].indices) {
                when (prision[i][j]) {
                    is Harry -> print("[  🧑🏻‍⚖️  ]")
                    is Dementor -> print("[  💀  ]")
                    is Horrocruxe -> print("[  ⌛  ]")
                    is Voldemort -> print("[  👨🏻‍🦲  ]")
                    is Bellatrix -> print("[  👩🏻‍🦱  ]")
                    is Ron -> print("[  🧍🏻  ]")
                    is Hermione -> print("[  🧍🏻‍♀️  ]")
                    is McGonagall -> print("[  👩🏻‍🏫  ]")
                    else -> print("[       ]")
                }
            }
            println()
        }
    }

    /**
     * Método para obtener la entrada de tecla del usuario.
     *
     * @return Cadena que representa la tecla introducida por el usuario.
     */
    private fun inputTecla(): String {
        var cadena = ""
        val inputRegex = Regex("[WASD]")
        var correcto = false

        do {
            cadena = readln().trim().uppercase()
            if (!inputRegex.matches(cadena)) {
                println("El formato no coincide, introduce para moverte (W/A/S/D)")
                correcto = false
            }

        } while (!inputRegex.matches(cadena))

        return cadena
    }

    /**
     * Método para permitir al jugador mover a Harry en la dirección especificada por el usuario.
     * Realiza acciones y actualiza el estado del juego.
     */
    fun moverHarry() {
        do {
            println("Introduce en qué dirección quieres que se mueva Harry (W/A/S/D)")
            val teclaIntro = inputTecla()
            when (teclaIntro) {
                "W" -> Harry.moverArriba(prision)
                "A" -> Harry.moverIzquierda(prision)
                "S" -> Harry.moverAbajo(prision)
                "D" -> Harry.moverDerecha(prision)
            }
            acciones()
            printMap()

            if (Harry.vida > 0){
                println("Harry tiene ${Harry.vida} puntos de vida")
            }else{
                println("Harry ha perdido todos sus puntos de vida")
                println("HARRY HA MUERTO")
            }

            if (NUMHORROCRUXES > 0){
                println("Número total de horrocruxes restantes $NUMHORROCRUXES")
            }else{
                println("HAS GANADO, HAS DESTRUIDO TODOS LOS HORROCRUXES")
            }


        } while (Harry.vida > 0 && NUMHORROCRUXES > 0)
    }
    /**
     * Realiza acciones basadas en la celda actual en la que se encuentra Harry.
     */
    private fun acciones() {
        val celdaActual = prision[Harry.fila][Harry.columna]

        when (celdaActual) {
            is Dementor -> accionDementor()
            is Voldemort -> accionVoldemort()
            is Bellatrix -> accionBellatrix()
            is Ron -> accionRon()
            is Hermione -> accionHermione()
            is McGonagall -> accionMcGonagall()
            is Horrocruxe -> accionHorrocruxe()
            else ->prision[Harry.fila][Harry.columna]=Harry
        }
    }
    /**
     * Realiza la accion de Harry cuando encuentra un horrocruxe
     */
    private fun accionHorrocruxe() {
        Horrocruxe.encuentroHorrocruxe()
        NUMHORROCRUXES -= 1
        prision[Harry.fila][Harry.columna]=Harry
    }

    /**
     * Realiza la accion de Harry cuando se encuentra con McGonagall
     */
    private fun accionMcGonagall() {
        McGonagall.curar()
        Harry.vida += 70
        prision[Harry.fila][Harry.columna]=Harry
    }

    /**
     * Realiza la accion de Harry cuando se encuentra con Hermione
     */
    private fun accionHermione() {
        Hermione.curar()
        Harry.vida += 30
        prision[Harry.fila][Harry.columna]=Harry
    }

    /**
     * Acción cuando Harry se encuentra con Ron. Ron puede curarlo.
     */
    private fun accionRon() {
        println("¡Ron te encontró! Ron puede curarte.")
        val randomValue = Random.nextInt(10)
        if (randomValue < 3) {
            println("Ron cometió un error al intentar curarte. Pierdes 10 puntos de vida.")
            Harry.vida -= 10
        } else {
            println("Ron te curó 20 puntos de vida.")
            Harry.vida += 20
        }
        prision[Harry.fila][Harry.columna]=Harry

    }

    /**
     * Realiza la accion de Harry cuando se encuentra con Bellatrix
     */
    private fun accionBellatrix() {
        Bellatrix.atacar()
        Harry.vida -= 30
        prision[Harry.fila][Harry.columna]=Harry
        generarBellatrix()
    }

    /**
     * Realiza la accion de Harry cuando se encuentra con Voldemort
     */
    private fun accionVoldemort() {
        Voldemort.atacar()
        Harry.vida -= 70
        prision[Harry.fila][Harry.columna]=Harry
        generarVoldemort()
    }
    /**
     * Realiza la accion de Harry cuando se encuentra con un dementor
     */
    private fun accionDementor() {
        Dementor.atacar()
        Harry.vida -= 10
        prision[Harry.fila][Harry.columna]=Harry
    }

    /**
     * Método para generar Dementores en posiciones aleatorias.
     */
    private fun generarDementores() {
        var cont = 0

        while (cont < NUDEMENTORES) {
            val newFil = (0 until tamPrision).random()
            val newCol = (0 until tamPrision).random()

            for (i in prision.indices) {
                for (j in prision[i].indices) {
                    if (prision[newFil][newCol] == null) {
                        prision[newFil][newCol] = Dementor()
                        cont++
                    }
                }
            }
        }
    }
    /**
     * Método para generar a Voldemort en una posición aleatoria.
     */
    private fun generarVoldemort() {
        val newFil = (0 until tamPrision).random()
        val newCol = (0 until tamPrision).random()
        for (i in prision.indices) {
            for (j in prision[i].indices) {
                if (prision[newFil][newCol] == null) {
                    prision[newFil][newCol] = Voldemort()
                }
            }
        }
    }

    /**
     * Método para generar a Bellatrix en una posición aleatoria.
     */
    private fun generarBellatrix() {
        val newFil = (0 until tamPrision).random()
        val newCol = (0 until tamPrision).random()
        for (i in prision.indices) {
            for (j in prision[i].indices) {
                if (prision[newFil][newCol] == null) {
                    prision[newFil][newCol] = Bellatrix()
                }
            }
        }
    }

    /**
     * Método para generar Horrocruxes en posiciones aleatorias.
     */
    private fun generarHorrocruxes() {
        var cont = 0

        while (cont < NUMHORROCRUXES) {
            val newFil = (0 until tamPrision).random()
            val newCol = (0 until tamPrision).random()

            for (i in prision.indices) {
                for (j in prision[i].indices) {
                    if (prision[newFil][newCol] == null) {
                        prision[newFil][newCol] = Horrocruxe()
                        cont++
                    }
                }
            }
        }
    }

    /**
     * Método para generar a McGonagall en una posición aleatoria.
     */
    private fun generarMcGonagall() {
        val newFil = (0 until tamPrision).random()
        val newCol = (0 until tamPrision).random()
        for (i in prision.indices) {
            for (j in prision[i].indices) {
                if (prision[newFil][newCol] == null) {
                    prision[newFil][newCol] = McGonagall()
                }
            }
        }
    }

    /**
     * Método para generar a Hermione en una posición aleatoria.
     */
    private fun generarHermione() {
        var newFil: Int
        var newCol: Int

        do {
            newFil = (0 until tamPrision).random()
            newCol = (0 until tamPrision).random()
        } while (prision[newFil][newCol] != null)

        prision[newFil][newCol] = Hermione()
    }

    /**
     * Método para generar a Ron en una posición aleatoria.
     */
    private fun generarRon() {
        var newFil: Int
        var newCol: Int

        do {
            newFil = (0 until tamPrision).random()
            newCol = (0 until tamPrision).random()
        } while (prision[newFil][newCol] != null)

        prision[newFil][newCol] = Ron()
    }
}