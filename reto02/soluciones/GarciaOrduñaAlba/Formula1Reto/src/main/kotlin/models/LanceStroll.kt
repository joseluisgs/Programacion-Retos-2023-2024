package org.example.models

/**
 * Clase que representa al piloto Lance Stroll, perteneciente al equipo Aston Martin.
 *
 * @param i Fila inicial en la que se coloca en el circuito.
 * @param j Columna inicial en la que se coloca en el circuito.
 */
class LanceStroll(i: Int, j: Int) : Piloto("Lance Stroll", 20, i, j), AstonMartin {

    /**
     * Realiza las acciones específicas de Lance Stroll durante una vuelta de la carrera.
     *
     * @param carrera Matriz que representa el circuito.
     * @param vuelta Número de la vuelta actual.
     */
    override fun accion(carrera: Array<Array<Piloto?>>, vuelta: Int) {
        super.accion(carrera, vuelta)
        realizarPitStop()
        malaEstrategia()
    }

    /**
     * Simula la posibilidad de que Lance Stroll tome una mala estrategia durante una vuelta.
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