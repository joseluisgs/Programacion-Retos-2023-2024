package org.example.models

/**
 * Clase que representa al piloto Le Clerc, perteneciente al equipo Aston Martin y con características de Ferrari.
 *
 * @param i Fila inicial en la que se coloca en el circuito.
 * @param j Columna inicial en la que se coloca en el circuito.
 */
class LeClerc(i: Int, j: Int) : Piloto("Le Clerc", 20, i, j), AstonMartin, Ferrari {

    /**
     * Realiza las acciones específicas de Le Clerc durante una vuelta de la carrera.
     *
     * @param carrera Matriz que representa el circuito.
     * @param vuelta Número de la vuelta actual.
     */
    override fun accion(carrera: Array<Array<Piloto?>>, vuelta: Int) {
        super.accion(carrera, vuelta)
        realizarPitStop()
        escuderia()
    }

    /**
     * Simula las acciones específicas relacionadas con la escudería de Le Clerc durante una vuelta.
     * Puede experimentar problemas de confiabilidad o tomar una mala estrategia.
     */
    private fun escuderia() {
        val random = (1..5).random()
        when (random) {
            1 -> problemasConfiabilidad()
            2 -> malaEstrategia()
            // Puedes agregar más casos según sea necesario
        }
    }

    /**
     * Simula problemas de confiabilidad que llevan a Le Clerc a quedar fuera de la carrera.
     */
    override fun problemasConfiabilidad() {
        this.sufrirAccidente = true
        println("Problemas de Confiabilidad: $nombre queda fuera de la carrera")
    }

    /**
     * Simula la posibilidad de que Le Clerc tome una mala estrategia durante una vuelta.
     * Se detendrá por 2 segundos si se cumple la condición.
     */
    override fun malaEstrategia() {
        val prov = (1..100).random()
        if (prov <= 7) {
            moverse = 2
            println("Mala estrategia: $nombre se detiene por 2 segundos")
        }
    }
}