package org.example.models

/**
 * Clase que representa al piloto Sainz que compite para el equipo Aston Martin y Ferrari.
 *
 * @param i Índice de la fila inicial del piloto en la parrilla de salida.
 * @param j Índice de la columna inicial del piloto en la parrilla de salida.
 */
class Sainz(i: Int, j: Int) : Piloto("Sainz", 10, i, j), AstonMartin, Ferrari {
    /**
     * Método que simula la acción general de un piloto Sainz en una vuelta de la carrera.
     * Realiza la acción común a todos los pilotos y luego ejecuta acciones específicas de Sainz.
     *
     * @param carrera Matriz que representa la pista de la carrera.
     * @param vuelta Número de la vuelta en la que se realiza la acción.
     */
    override fun accion(carrera: Array<Array<Piloto?>>, vuelta: Int) {
        super.accion(carrera, vuelta)
        realizarPitStop()
        escuderia()
    }

    /**
     * Método específico de Sainz que simula la elección de una acción basada en la escudería.
     * Puede ser llamado durante la acción de un piloto en una vuelta de la carrera.
     */
    private fun escuderia() {
        val random = (1..15).random()
        when (random) {
            1 -> problemasConfiabilidad()
            2 -> malaEstrategia()
            // Puedes agregar más casos según sea necesario
        }
    }

    /**
     * Método específico de Sainz que simula problemas de confiabilidad.
     * Puede ser llamado durante la elección de acciones basadas en la escudería.
     */
    override fun problemasConfiabilidad() {
        this.sufrirAccidente = true
        println("Problemas de Confiabilidad: $nombre queda fuera de la carrera")
    }

    /**
     * Método específico de Sainz que simula una mala estrategia.
     * Puede ser llamado durante la elección de acciones basadas en la escudería.
     */
    override fun malaEstrategia() {
        val prov = (1..100).random()
        if (prov <= 2) {
            moverse = 2
            println("Mala estrategia: $nombre se detiene por 2 segundos")
        }
    }
}