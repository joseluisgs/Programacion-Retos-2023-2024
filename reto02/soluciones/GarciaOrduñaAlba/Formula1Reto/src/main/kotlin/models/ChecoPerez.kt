package org.example.models
/**
 * Clase que representa al piloto Checo Perez, perteneciente al equipo RedBull.
 *
 * @param i Fila inicial en la que se encuentra el piloto en el circuito.
 * @param j Columna inicial en la que se encuentra el piloto en el circuito.
 * @param carrera Matriz que representa el estado del circuito.
 */
class ChecoPerez(
    i: Int,
    j: Int,
    private val carrera: Array<Array<Piloto?>>
) : Piloto("Checo Perez", 10, i, j), RedBull {

    /**
     * Realiza las acciones específicas de Checo Perez durante una vuelta, incluyendo vuelta rápida y pit stop.
     *
     * @param carrera Matriz que representa el estado del circuito.
     * @param vuelta Número de vuelta actual.
     */
    override fun accion(carrera: Array<Array<Piloto?>>, vuelta: Int) {
        super.accion(carrera, vuelta)
        realizarVueltaRapida(vuelta)  // Pasa la variable 'vuelta'
        realizarPitStop()
    }

    /**
     * Realiza una vuelta rápida con una probabilidad del 10%, avanzando dos posiciones en el circuito.
     *
     * @param vuelta Número de vuelta actual.
     */
    override fun realizarVueltaRapida(vuelta: Int) {
        if (columna != 9) {
            val prov = (1..100).random()
            if (prov <= 2) {
                val nuevaPosicion = (columna + 2) % 10
                if (carrera[vuelta][nuevaPosicion] == null) {
                    carrera[vuelta][columna] = null
                    columna = nuevaPosicion
                    carrera[vuelta][columna] = this
                    println("$nombre ha dado una vuelta rápida ⚡")
                } else {
                    println("$nombre no puede realizar la vuelta rápida debido a que la posición está ocupada.")
                }
            }
        }
    }
}