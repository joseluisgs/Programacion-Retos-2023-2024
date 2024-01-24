package org.example.models
/**
 * Clase base para representar a un piloto en la carrera.
 *
 * @property nombre Nombre del piloto.
 * @property probabilidadAccidente Probabilidad de sufrir un accidente en la carrera.
 * @property columna Posición actual del piloto en el circuito.
 * @property vuelta Número de vuelta en la que se encuentra el piloto.
 */
open class Piloto(
    var nombre: String,
    private val probabilidadAccidente: Int,
    var columna: Int = 0,
    var vuelta: Int = 0
) {
    var moverse = 0
    var continuar = 0
    var vueltasTotales = 3
    var sufrirAccidente = false
    var vueltaRapida = false

    /**
     * Realiza un pit stop, determinando si el piloto se detiene o continúa.
     */
    open fun realizarPitStop() {
        continuar = (1..3).random()
        moverse = 0
    }

    /**
     * Simula un accidente con base en la probabilidad predefinida.
     *
     * @return Verdadero si el piloto sufre un accidente, falso en caso contrario.
     */
    open fun accidente(): Boolean {
        if (!sufrirAccidente) {
            val num = (1..500).random()
            if (num <= probabilidadAccidente) {
                sufrirAccidente = true
            }
        }
        return sufrirAccidente
    }

    /**
     * Método específico para simular la acción de los pilotos durante una vuelta.
     *
     * @param carrera Matriz que representa el estado del circuito.
     * @param vuelta Número de vuelta actual.
     */
    open fun moverPilotos(carrera: Array<Array<Piloto?>>, vuelta: Int) {
        // Definición de probabilidades y movimientos posibles.
        val probabilidadVueltaRapida = 2
        val probabilidadMalaEstrategia = 7
        val probabilidadErrorMercedes = 8
        val probabilidadProblemasFiabilidad = 10

        val movimientoNormal = 1
        val movimientoVueltaRapida = 2

        // Toma una decisión aleatoria.
        val decision = (1..100).random()

        // Realiza acciones basadas en la decisión.
        when {
            decision <= probabilidadVueltaRapida && this is RedBull -> {
                println("$nombre (RedBull) ha realizado una vuelta rápida!")

                // Calcula la nueva posición después de una vuelta rápida.
                val nuevaPosicion = (columna + movimientoVueltaRapida) % 10

                // Verifica si la nueva posición está disponible.
                if (carrera[vuelta][nuevaPosicion] == null) {
                    // Actualiza la posición en la matriz del circuito.
                    carrera[vuelta][columna] = null
                    columna = nuevaPosicion
                    carrera[vuelta][columna] = this
                } else {
                    println("$nombre (RedBull) no puede realizar la vuelta rápida debido a que la posición está ocupada.")
                }
            }
            decision <= probabilidadMalaEstrategia && (this is AstonMartin || this is Ferrari) -> {
                println("$nombre ha cometido una mala estrategia y se detendrá 2 segundos.")
                moverse = 2
            }
            decision <= probabilidadErrorMercedes && this is Mercedes -> {
                println("$nombre ha cometido un error y saldrá el safety car, retrocediendo una posición.")
                val nuevaPosicion = (columna - 1 + 10) % 10

                // Verifica si la nueva posición está disponible.
                if (carrera[vuelta][nuevaPosicion] == null || carrera[vuelta][nuevaPosicion] == this) {
                    // Actualiza la posición en la matriz del circuito.
                    carrera[vuelta][columna] = null
                    columna = nuevaPosicion
                    carrera[vuelta][columna] = this
                } else {
                    println("$nombre no puede retroceder debido a que la posición está ocupada por otro piloto.")
                }
            }
            decision <= probabilidadProblemasFiabilidad && (this is Ferrari) -> {
                println("$nombre ha sufrido problemas de fiabilidad y queda fuera de la carrera (DNF).")
                sufrirAccidente = true
            }
            else -> {
                // Calcula la nueva posición después de un movimiento normal.
                val nuevaPosicion = (columna + movimientoNormal) % 10

                // Verifica si la nueva posición está disponible.
                if (carrera[vuelta][nuevaPosicion] == null) {
                    // Actualiza la posición en la matriz del circuito.
                    carrera[vuelta][columna] = null
                    columna = nuevaPosicion
                    carrera[vuelta][columna] = this
                } else {
                    println("$nombre no puede avanzar a la posición $nuevaPosicion debido a que está ocupada.")
                }
            }
        }
    }

    /**
     * Método para simular la acción general de un piloto en una vuelta.
     *
     * @param carrera Matriz que representa el estado del circuito.
     * @param vuelta Número de vuelta actual.
     */
    open fun accion(carrera: Array<Array<Piloto?>>, vuelta: Int) {
        if (!sufrirAccidente) {
            // Verifica si el piloto debe continuar o detenerse.
            if (continuar == 0) {
                // Realiza la acción de moverse durante la vuelta.
                moverPilotos(carrera, vuelta)

                // Simula un accidente después de realizar la acción.
                accidente()
            } else {
                // Reducción del contador de tiempo para detenerse.
                continuar -= 1
            }
        }
    }
}