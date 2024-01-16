import models.*
import org.example.models.Harry
import org.example.models.Horrocruxes
import java.util.*
import kotlin.random.Random

/**
 * Clase que representa la Mazmorra donde Harry Potter se aventura.
 * El objetivo es recolectar los Horrocruxes mientras enfrenta diferentes enemigos.
 *
 * @property MAPSIZE Tamaño del mapa de la mazmorra.
 * @property PROB_RECIBIR_ATAQUE Probabilidad de recibir un ataque al enfrentar enemigos.
 * @property mazmorra Representación del mapa de la mazmorra como una matriz.
 * @property harry Instancia de la clase Harry que representa al personaje principal.
 * @property columna Posición actual de la columna de Harry en la mazmorra.
 * @property fila Posición actual de la fila de Harry en la mazmorra.
 */
private const val MAPSIZE = 6
private const val PROB_RECIBIR_ATAQUE = 60

class Mazmorra {
    private var mazmorra: Array<Array<Any?>> = Array(MAPSIZE) { arrayOfNulls(MAPSIZE) }
    private val harry = Harry()
    var columna: Int = 0
    var fila: Int = 0

    /**
     * Inicializa la posición inicial de Harry en la mazmorra.
     */
    private fun posicionHarry() {
        val columna = 0
        val fila = 0
        mazmorra[fila][columna] = harry
    }

    /**
     * Mueve a Harry en la dirección especificada por el usuario.
     *
     * @param usuarioDireccion Dirección a la que se moverá Harry (W, A, S o D).
     */
    fun mover(usuarioDireccion: String) {
        val filaActual = harry.posicion[0]
        val columnaActual = harry.posicion[1]
        var nuevaFila = filaActual
        var nuevaColumna = columnaActual

        when (usuarioDireccion) {
            "W" -> nuevaFila -= 1
            "S" -> nuevaFila += 1
            "D" -> nuevaColumna += 1
            "A" -> nuevaColumna -= 1
        }

        harry.posicion[0] = nuevaFila
        harry.posicion[1] = nuevaColumna

        accionPersonajes(nuevaFila, nuevaColumna)

        mazmorra[nuevaFila][nuevaColumna] = harry
        mazmorra[filaActual][columnaActual] = null
        comprobarCelda(filaActual, columnaActual)

        println("Harry se ha movido a la posición: ($nuevaFila, $nuevaColumna)")
    }

    /**
     * Imprime el estado actual de la mazmorra en la consola.
     *
     * @param mazmorra Representación actual del mapa de la mazmorra.
     */
    private fun imprimirMazmorra(mazmorra: Array<Array<Any?>>) {
        for (i in mazmorra.indices) {
            for (j in mazmorra[i].indices) {
                if(mazmorra[i][j] == Celda.VISTA) {
                    print("[🐾]")
                }else print (when (mazmorra[i][j]) {
                    is Harry -> "[🔦]"
                    is Dementor -> "[❔]"
                    is Horrocruxes -> "[❔]"
                    is Bellatrix -> "[❔]"
                    is Voldemort -> "[❔]"
                    is Hermione -> "[❔]"
                    is Ron -> "[❔]"
                    is McGonagall -> "[❔]"
                    else -> "[❔]"
                })
            }
            println()
        }
    }
    /**
     * Función para comprobar la celda en la posición actual de Harry en la mazmorra.
     * Si la celda está vacía (null), la marca como ocupada utilizando el valor de la enumeración Celda.
     */
    fun comprobarCelda(filaActual:Int, columnaActual: Int){
        if (mazmorra[filaActual][columnaActual] == null) {
            mazmorra[filaActual][columnaActual] = Celda.VISTA

        }
    }

    /**
     * Coloca personajes y objetos aleatorios en la mazmorra.
     *
     * @param mazmorra Representación actual del mapa de la mazmorra.
     */
    private fun colocarPersonajesAleatorios(mazmorra: Array<Array<Any?>>) {
        val personajes = mutableListOf(
            *(Array(6) { Dementor() }),
            *(Array(7) { Horrocruxes() }),
            Voldemort(),
            Bellatrix(),
            Ron(),
            Hermione(),
            McGonagall()
        )
        for (personaje in personajes) {
            var fila: Int
            var columna: Int

            do {
                fila = (0 until MAPSIZE).random()
                columna = (0 until MAPSIZE).random()
            } while (mazmorra[fila][columna] != null)

            mazmorra[fila][columna] = personaje
        }
    }

    /**
     * Realiza la acción del juego según la posición actual de Harry.
     */
    private fun accionJuego() {
        var usuarioDireccion : String
        do {
            println("Elige una dirección: W (Arriba), A (Izquierda), S (Abajo) o D (Derecha).")
            usuarioDireccion = readln().toUpperCase()
        }while (usuarioDireccion!= "W" && usuarioDireccion!= "A" && usuarioDireccion!= "S" && usuarioDireccion!= "D")

        if (usuarioDireccion != null && comprobarLimites(harry.obtenerFila(),harry.obtenerColumna())) {
            mover(usuarioDireccion)
        } else {
            println("No se puede mover")
        }
    }

    /**
     * Verifica si la posición dada está dentro de los límites de la mazmorra.
     *
     * @param fila Fila a verificar.
     * @param columna Columna a verificar.
     * @return `true` si la posición está dentro de los límites, `false` de lo contrario.
     */
    private fun comprobarLimites(fila: Int, columna: Int): Boolean {
        return fila in 0 until MAPSIZE && columna in 0 until MAPSIZE
    }

    /**
     * Realiza la acción correspondiente a la posición actual de Harry en la mazmorra.
     *
     * @param fila Fila actual de Harry.
     * @param columna Columna actual de Harry.
     */
    fun accionPersonajes(fila: Int, columna: Int) {
        when (mazmorra[fila][columna]) {
            is Dementor -> {
                val dementor=Dementor()
                println("Encontramos un Dementor 👻")
                if (Random.nextInt(100) < PROB_RECIBIR_ATAQUE) {
                    println("¡Ataque exitoso! Hemos matado al enemigo ")
                    mazmorra[fila][columna] = null
                } else {
                    harry.salud -= dementor.daño
                    println("El ataque ha fallado. Perdemos 10 puntos de vida. Salud actual: ${harry.salud}")
                    moverEnemigo(fila, columna)

                }
            }
            is Voldemort -> {
                val voldemort = Voldemort()
                println("Encontramos a Voldemort 💀")
                if (Random.nextInt(100) < PROB_RECIBIR_ATAQUE) {
                    println("¡Ataque exitoso! Hemos atacado a Voldemort, huye a otro punto de la mazmorra...")
                    moverEnemigo(fila, columna)
                } else {
                    harry.salud -= voldemort.daño
                    println("El ataque ha fallado. Perdemos 30 puntos de vida. Salud actual: ${harry.salud}. El Voldemort se mueve a otro punto de la mazmorra")
                    moverEnemigo(fila, columna)
                }
            }
            is Horrocruxes -> {
                println("‼️Encontramos un Horrocrux en ($fila, $columna)‼️")
                harry.recogerHorrocrux()
                mazmorra[fila][columna] = null
            }
            is Bellatrix -> {
                val bellatrix = Bellatrix()
                if (Random.nextInt(100) < PROB_RECIBIR_ATAQUE) {
                    println("¡Ataque exitoso! Hemos atacado a Bellatrix en $fila, $columna. Bellatrix huye a otro punto de la mazmorra")
                    moverEnemigo(fila, columna)
                } else {
                    harry.salud -= bellatrix.daño
                    println("El ataque ha fallado. Bellatrix nos quita 20 puntos de vida. Salud actual: ${harry.salud}")
                    moverEnemigo(fila, columna)
                }
            }
            is Hermione -> {
                println("Nuestra amiga Hermione entra en escena 👩🏽")
                val hermione = Hermione()
               harry.salud+=hermione.curar()
            }
            is Ron -> {
                println("Nuestra amigo Ron entra en escena 👨🏻‍🦰")
                val ron = Ron()
                ron.probabilidad()

            }
            is McGonagall -> {
                println("La profesora McGonagall entra en escena 👵🏻")
                val mcGonagall = McGonagall()
                harry.salud+= mcGonagall.curar()
            }
            null -> println()
        }
    }
    /**
     * Mueve a un enemigo a una nueva posición aleatoria en la mazmorra.
     *
     * @param fila Fila actual del enemigo.
     * @param columna Columna actual del enemigo.
     */

    private fun moverEnemigo(fila: Int , columna: Int ) {
        var newFila:Int
        var newColumna :Int
        do {
            newFila = (0 until MAPSIZE).random()
            newColumna = (0 until MAPSIZE).random()
        } while (mazmorra[newFila][newColumna] != null && mazmorra[newFila][newColumna]!= Celda.VISTA)

        mazmorra[fila][columna] = null
        mazmorra[newFila][newColumna] =Voldemort();Bellatrix();Dementor()

    }
    /**
     * Imprime un informe con la salud actual y la cantidad de Horrocruxes recolectados por Harry.
     */
    fun informe(){
        println("Salud actual: ${harry.salud} ❤️‍🩹")
        println("Horrocruxes: ${harry.horrocrux} 👾")
    }
    /**
     * Simula la exploración de la mazmorra por parte de Harry hasta que recolecta todos los Horrocruxes o su salud llega a cero.
     */
    fun simular() {
        posicionHarry()
        colocarPersonajesAleatorios(mazmorra)
        informe()
        imprimirMazmorra(mazmorra)
        do {
            accionJuego()
            accionPersonajes(fila, columna)
            println()
            imprimirMazmorra(mazmorra)
            informe()
        } while (harry.horrocrux != 7 && harry.salud > 0)

        if (harry.horrocrux == 7){
            println("¡Has logrado destruir los 7 horrocruxes!")
        } else if (harry.salud == 0){
            println("¡No has sobrevivido a la Mazmorra!")
        }
    }
}
