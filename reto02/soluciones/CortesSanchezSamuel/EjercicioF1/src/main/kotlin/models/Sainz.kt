package models

import models.Piloto

/**
 * Clase que representa al piloto Sainz.
 *
 * @constructor Crea una instancia del piloto con el nombre, probabilidad de accidente y tiempo especificados.
 * @property nombre El nombre del piloto
 * @property probabilidadAccidente La probabilidad de accidente del piloto
 * @property tiempo El tiempo acumulado del piloto en la carrera
 */
class Sainz(
    override val nombre: String = "Sainz",
    override val probabilidadAccidente: Int = 10,
    override var tiempo: Int = 0
) : Piloto {

    /**
     * Inicializa la posición del piloto en la parrilla de salida.
     *
     * @param parrilla La matriz que representa la parrilla de salida.
     */
    override fun inicializarPiloto(parrilla: Array<Array<Piloto?>>) {
        parrilla[6][0] = this
    }
}
