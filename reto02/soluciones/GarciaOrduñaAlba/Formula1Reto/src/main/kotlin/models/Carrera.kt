package org.example.models

import java.time.LocalTime
/**
 * Clase principal que representa una carrera de Fórmula 1.
 * Contiene la lógica principal para la inicialización, ejecución y finalización de la carrera.
 *@author Alba
 * @version 4.0
 * @property columna Número de columnas en la matriz del circuito.
 * @property fila Número de filas en la matriz del circuito.
 * @property carrera Matriz que representa el circuito con pilotos en posiciones específicas.
 * @property pilotos Array de pilotos que participan en la carrera.
 * @property vueltasTotales Número total de vueltas que se deben completar en la carrera.
 * @property vueltas Contador de vueltas realizadas durante la carrera.
 */
class Carrera {
    private var columna: Int = 10
    private var fila: Int = 8

    private var carrera: Array<Array<Piloto?>> = Array(fila) { Array(columna) { null } }
    var pilotos: Array<Piloto> = arrayOf(
        ChecoPerez(0, 0,carrera), FernandoAlonso(0, 0), Hamilton(0, 0), LanceStroll(0, 0),
        LeClerc(0, 0), MaxVerstappen(0, 0), Russel(0, 0), Sainz(0, 0)
    )
    private var vueltasTotales: Int = 3
    private var vueltas: Int = 0
    val piloto=Piloto("",0,0,0)

    /**
     * Coloca los pilotos en sus posiciones iniciales en la matriz del circuito.
     */
    private fun colocarPilotos() {
        for (i in pilotos.indices) {
            carrera[i][pilotos[i].columna] = pilotos[i]
        }
    }

    /**
     * Avanza los pilotos en el circuito y actualiza la matriz del circuito con las nuevas posiciones.
     */
    private fun avanzarPilotos() {
        // Matriz temporal para almacenar las nuevas posiciones de los pilotos.
        val posicionActual = Array(fila) { Array(columna) { null as Piloto? } }

        // Itera sobre cada posición en la matriz del circuito.
        for (i in 0 until fila) {
            for (j in 0 until columna) {
                val piloto = carrera[i][j]

                // Verifica si hay un piloto en la posición y si no ha sufrido un accidente.
                if (piloto != null && !piloto.accidente()) {
                    // Realiza la acción del piloto en esa posición para la vuelta actual.
                    piloto.accion(carrera, i)

                    // Calcula la nueva posición en la siguiente columna.
                    val nuevaPosicion = (j + 1) % columna

                    // Realiza un pit stop si el piloto está en la posición específica.
                    if (j == 4 && i == 0) {
                        piloto.realizarPitStop()
                    }

                    // Actualiza la posición en la matriz temporal si la columna ha cambiado.
                    if (piloto.columna != nuevaPosicion) {
                        posicionActual[i][nuevaPosicion] = piloto
                    }

                    // Verifica si el piloto ha completado una vuelta al llegar a la última columna.
                    if (j == columna - 1 && nuevaPosicion == 0) {
                        if (i == fila - 1) {
                            if (piloto.vuelta < piloto.vueltasTotales) {
                                vueltas++
                                println("Vueltas:$vueltas")
                            }
                        }
                    }
                }
            }
        }

        // Actualiza la matriz del circuito con las nuevas posiciones de los pilotos.
        carrera = posicionActual

        // Filtra los pilotos que no han sufrido accidentes y no han completado todas las vueltas.
        pilotos = pilotos.filter { !it.accidente() && it.vuelta < it.vueltasTotales }.toTypedArray()
    }
    /**
     * Muestra la configuración actual del circuito con los pilotos en sus respectivas posiciones.
     */
    private fun mostrarCircuito() {
        for (fila in carrera.indices) {
            for (columna in carrera[0].indices) {
                val piloto = carrera[fila][columna]

                // Utiliza un when para asignar un emoji según el tipo de piloto en la posición.
                when (piloto) {
                    is ChecoPerez -> print("[🟡]")
                    is FernandoAlonso -> print("[🔵]")
                    is Hamilton -> print("[🔴]")
                    is LanceStroll -> print("[🟣]")
                    is LeClerc -> print("[🟠]")
                    is MaxVerstappen -> print("[🟢]")
                    is Russel -> print("[🟤]")
                    is Sainz -> print("[⚪]")

                    else -> print("[  ]")
                }
            }
            print("🏁")
            println()
        }
        println()
    }

    /**
     * Comprueba las condiciones meteorológicas y realiza acciones en consecuencia.
     */
    private fun comprobarMeteorología() {
        // Genera un número aleatorio para simular la probabilidad de lluvia.
        val probabilidadLluvia = (0..100).random()

        // Comprueba si la probabilidad de lluvia es menor o igual al 35%.
        if (probabilidadLluvia <= 35) {
            println("Ha empezado a llover... 🌧️🌧️ Toca cambiar ruedas!!🛞")
            realizarPitStop()
        } else {
            println("Ha salido el sol!!☀️☀️")
        }
    }

    /**
     * Actualiza la posición actual de los pilotos en la matriz del circuito.
     */
    private fun actualizarPosicionActual() {
        // Matriz temporal para almacenar las nuevas posiciones de los pilotos.
        val nuevaPosicionActual = Array(fila) { Array(columna) { null as Piloto? } }

        // Itera sobre cada posición en la matriz del circuito.
        for (i in 0 until fila) {
            for (j in 0 until columna) {
                val piloto = carrera[i][j]

                // Verifica si hay un piloto en la posición y si no ha sufrido un accidente.
                if (piloto != null && !piloto.accidente()) {
                    // Actualiza la posición en la matriz temporal con la nueva columna del piloto.
                    nuevaPosicionActual[i][piloto.columna] = piloto
                }
            }
        }
    }
    /**
     * Realiza un pit stop si el piloto está en la posición específica.
     */

    private fun realizarPitStop() {
        val tiempoPitStop = (1..3).random()

        if (tiempoPitStop == 0) {
            avanzarPilotos()
            println("PitStop terminado")
            actualizarPosicionActual()
        } else {
            Thread.sleep(tiempoPitStop * 1000L)
        }
    }

    /**
     * Inicia la simulación de la carrera.
     */
    fun iniciarCarrera() {
        // Coloca los pilotos en sus posiciones iniciales.
        colocarPilotos()

        // Muestra la configuración inicial del circuito.
        mostrarCircuito()

        // Bucle mientras no se hayan completado todas las vueltas.
        while (vueltas < vueltasTotales) {
            // Avanza los pilotos en el circuito.
            avanzarPilotos()

            // Muestra la configuración actualizada del circuito.
            mostrarCircuito()

            //  realizar acciones.
            for (piloto in pilotos) {
                // Realiza la acción del piloto en la vuelta actual.
                piloto.accion(carrera, vueltas)

                // Verifica si el piloto ha sufrido un accidente.
                piloto.accidente()

                // Realiza un pit stop si es necesario.
                piloto.realizarPitStop()
            }

            // Comprueba las condiciones meteorológicas en la vuelta 2.
            if (vueltas == 2) {
                comprobarMeteorología()
            }

            // Espera 1 segundo antes de la siguiente iteración.
            Thread.sleep(1000)

            // Muestra la hora actual.
            println(LocalTime.now())
        }

        // Muestra un mensaje indicando que la carrera ha sido completada.
        println("Carrera completada después de $vueltasTotales vueltas.")
    }
}