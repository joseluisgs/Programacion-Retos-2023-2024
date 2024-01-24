package org.example.models

/**
 * Clase que representa al piloto Russell del equipo Mercedes.
 *
 * @param i Índice de la fila inicial del piloto en la parrilla de salida.
 * @param j Índice de la columna inicial del piloto en la parrilla de salida.
 */
class Russel(i: Int, j: Int) : Piloto("Russel", 15, i, j), Mercedes {
    /**
     * Método que simula la acción general de un piloto Russell en una vuelta de la carrera.
     * Realiza la acción común a todos los pilotos y luego ejecuta acciones específicas de Russell.
     *
     * @param carrera Matriz que representa la pista de la carrera.
     * @param vuelta Número de la vuelta en la que se realiza la acción.
     */
    override fun accion(carrera: Array<Array<Piloto?>>, vuelta: Int) {
        super.accion(carrera, vuelta)
        realizarPitStop()
        errorSafteyCar()
    }

    /**
     * Método específico de Russell que simula un error de safety car.
     * Puede ser llamado durante la acción de un piloto en una vuelta de la carrera.
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
