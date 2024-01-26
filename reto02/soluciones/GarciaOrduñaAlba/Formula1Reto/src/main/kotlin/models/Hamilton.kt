package org.example.models
/**
 * Clase que representa al piloto Hamilton, perteneciente al equipo Mercedes.
 *
 * @param i Fila inicial en la que se coloca en el circuito.
 * @param j Columna inicial en la que se coloca en el circuito.
 */
class Hamilton(i: Int, j: Int) : Piloto("Hamilton", 10, i, j), Mercedes {

    /**
     * Realiza las acciones específicas de Hamilton durante una vuelta de la carrera.
     *
     * @param carrera Matriz que representa el circuito.
     * @param vuelta Número de la vuelta actual.
     */
    override fun accion(carrera: Array<Array<Piloto?>>, vuelta: Int) {
        super.accion(carrera, vuelta)
        realizarPitStop()
        errorSafteyCar()
    }

    /**
     * Simula un posible error de seguridad (safety car) para Hamilton.
     * Retrocede a Hamilton en una casilla si se cumple la condición.
     */
    override fun errorSafteyCar() {
        if (columna != 0 && columna != 1) {
            val prov = (1..100).random()
            if (prov <= 8) {
                columna -= 2
                println("Error: $nombre retrocede 1 casilla")
            }
        }
    }


}




