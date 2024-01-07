package models

import kotlin.random.Random

// Constantes para la cantidad de Dementores y Horrocruxes en el juego
const val NUDEMENTORES = 6
const val NUMHORROCRUXES = 7

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

    // Instancia de Harry
    private val Harry = Harry()

    /**
     * Inicialización del juego Azkaban.
     * Coloca a Harry en la posición inicial y genera la prisión.
     */
    init {
        prision[Harry.fila][Harry.columna] = Harry
        iniPrision()
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
     * Método para inicializar la prisión generando personajes y enemigos.
     */
    private fun iniPrision() {
        generarDementores()
        generarVoldemort()
        generarBellatrix()
        generarHorrocruxes()
        generarHermione()
        generarMcGonagall()
        generarRon()
    }

    /**
     * Método para generar a Harry en una posición específica (no utilizado actualmente).
     */
    private fun generarHarry() {
        for (i in prision.indices) {
            for (j in prision[i].indices) {
                prision[0][0] = Harry()
            }
        }
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
            cadena = readLine().orEmpty().trim().uppercase()
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

        } while (Harry.vida > 0)
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
        }
    }

    /**
     * Acción cuando Harry se encuentra con un Dementor. Pierde 10 puntos de vida.
     */
    private fun accionDementor() {
        println("¡Dementor en frente! Harry pierde 10 puntos de vida.")
        Harry.vida -= 10
    }

    /**
     * Acción cuando Harry se encuentra con Voldemort. Pierde 70 puntos de vida y Voldemort se reubica.
     */
    private fun accionVoldemort() {
        println("¡Voldemort apareció! Harry pierde 70 puntos de vida y Voldemort se reubica.")
        Harry.vida -= 70
        generarVoldemort()
    }

    /**
     * Acción cuando Harry se encuentra con Bellatrix. Pierde 30 puntos de vida y Bellatrix se reubica.
     */
    private fun accionBellatrix() {
        println("¡Bellatrix apareció! Harry pierde 30 puntos de vida y Bellatrix se reubica.")
        Harry.vida -= 30
        generarBellatrix()
    }

    /**
     * Acción cuando Harry se encuentra con Ron. Ron puede curarlo.
     */
    private fun accionRon() {
        println("¡Ron te encontró! Ron puede curarte.")
        if (Random.nextFloat() < 0.3) {
            println("Ron cometió un error al intentar curarte. Pierdes 10 puntos de vida.")
            Harry.vida -= 10
        } else {
            println("Ron te curó 20 puntos de vida.")
            Harry.vida += 20
        }
        generarRon()
    }

    /**
     * Acción cuando Harry se encuentra con Hermione. Hermione cura 30 puntos de vida.
     */
    private fun accionHermione() {
        println("¡Hermione te encontró! Hermione cura 30 puntos de vida.")
        Harry.vida += 30
        generarHermione()
    }

    /**
     * Acción cuando Harry se encuentra con McGonagall. McGonagall cura 70 puntos de vida.
     */
    private fun accionMcGonagall() {
        println("¡McGonagall te encontró! McGonagall cura 70 puntos de vida.")
        Harry.vida += 70
        generarMcGonagall()
    }

    /**
     * Acción cuando Harry se encuentra con un Horrocruxe. Destruye el Horrocruxe.
     */
    private fun accionHorrocruxe() {
        println("¡Horrocruxe encontrado! Harry destruye el Horrocruxe.")
        Harry.destruirHorrocruxe()
    }

}
