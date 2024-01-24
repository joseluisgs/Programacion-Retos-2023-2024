package models

import kotlin.random.Random

/**
 * Interfaz que define las propiedades y métodos comunes para los pilotos en el circuito de carreras.
 */
interface Piloto {

    /**
     * El nombre del piloto.
     */
    val nombre: String

    /**
     * La probabilidad de accidente del piloto.
     */
    val probabilidadAccidente: Int

    /**
     * El tiempo acumulado del piloto en la carrera.
     */
    var tiempo: Int

    /**
     * Inicializa la posición del piloto en la parrilla de salida.
     *
     * @param parrilla La matriz que representa la parrilla de salida.
     */
    fun inicializarPiloto(parrilla: Array<Array<Piloto?>>)

    /**
     * Avanza la posición del piloto en el circuito y gestiona eventos como pit stops y accidentes.
     *
     * @param parrilla La matriz que representa la posición de los pilotos en el circuito.
     */
    fun avanzarPosicion(parrilla: Array<Array<Piloto?>>) {
        for (i in parrilla.indices) {
            for (j in parrilla[i].indices) {
                if (parrilla[i][j] == this) {
                    if (j == 4) {
                        val tiempoPitStop = hacerPitStop()
                        tiempo += tiempoPitStop.toInt() / 1000
                    }
                    if (Random.nextInt(100) < probabilidadAccidente) {
                        println("$nombre ha tenido un accidente desastroso y queda fuera de la carrera.")
                        parrilla[i][j] = null
                    } else {
                        if (j < parrilla[i].size - 1 && parrilla[i][j + 1] == null) {
                            parrilla[i][j] = null
                            parrilla[i][j + 1] = this
                        } else if (j == parrilla[i].size - 1) {
                            parrilla[i][j] = null
                            parrilla[i][(j + 1) % parrilla[i].size] = this
                        }
                    }
                    tiempo += 1
                    break
                }
            }
        }
    }

    /**
     * Realiza un pit stop con una duración aleatoria entre 1 y 3 segundos.
     *
     * @return La duración del pit stop en milisegundos.
     */
    private fun hacerPitStop(): Long {
        val pausa = (1..3).random() * 1000L
        println("$nombre realiza un pit stop con una duración de ${pausa / 1000} segundos.")
        Thread.sleep(pausa)
        return pausa
    }
}
