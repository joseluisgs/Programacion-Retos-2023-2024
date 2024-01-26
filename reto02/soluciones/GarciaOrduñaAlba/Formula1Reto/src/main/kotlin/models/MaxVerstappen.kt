package org.example.models

/**
 * Clase que representa al piloto Max Verstappen, perteneciente al equipo Red Bull.
 *
 * @param i Fila inicial en la que se coloca en el circuito.
 * @param j Columna inicial en la que se coloca en el circuito.
 */
class MaxVerstappen(i: Int, j: Int) : Piloto("Max Verstappen", 5, i, j), RedBull {

    /**
     * Realiza las acciones específicas de Max Verstappen durante una vuelta de la carrera.
     *
     * @param carrera Matriz que representa el circuito.
     * @param vuelta Número de la vuelta actual.
     */
    override fun accion(carrera: Array<Array<Piloto?>>, vuelta: Int) {
        super.accion(carrera, vuelta)
        realizarPitStop()
        realizarVueltaRapida(vuelta)
    }

    /**
     * Simula la posibilidad de que Max Verstappen realice una vuelta rápida durante una vuelta.
     * Aumentará su posición en una columna si se cumple la condición.
     *
     * @param vuelta Número de la vuelta actual.
     */
    override fun realizarVueltaRapida(vuelta: Int) {
        if (columna != 9) {
            val prov = (1..100).random()
            if (prov <= 2) {
                columna++
                println("$nombre ha dado una vuelta rápida ⚡")
            }
        }
    }


}