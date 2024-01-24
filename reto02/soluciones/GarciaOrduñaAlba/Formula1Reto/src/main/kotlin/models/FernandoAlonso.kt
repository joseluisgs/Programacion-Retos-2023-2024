package org.example.models

/**
 * Clase que representa al piloto Fernando Alonso, perteneciente al equipo AstonMartin.
 *
 * @param i Fila inicial en la que se encuentra el piloto en el circuito.
 * @param j Columna inicial en la que se encuentra el piloto en el circuito.
 */
class FernandoAlonso(i: Int, j: Int) : Piloto("Fernando Alonso", 5, i, j), AstonMartin {

    /**
     * Realiza las acciones específicas de Fernando Alonso durante una vuelta, incluyendo pit stop.
     *
     * @param carrera Matriz que representa el estado del circuito.
     * @param vuelta Número de vuelta actual.
     */
    override fun accion(carrera: Array<Array<Piloto?>>, vuelta: Int) {
        super.accion(carrera, vuelta)
        realizarPitStop()
    }

    /**
     * Aplica una posible mala estrategia con una probabilidad del 15%, deteniéndose por 2 segundos.
     */
    override fun malaEstrategia() {
        val prov = (1..100).random()
        if (prov <= 7) {
            moverse = 2
            println("Mala estrategia: $nombre se detiene por 2 segundos")
        }
    }

}
