package models

/**
 * Clase que representa al piloto Lance Stroll.
 *
 * @constructor Crea una instancia del piloto con el nombre, probabilidad de accidente y tiempo especificados.
 * @property nombre El nombre del piloto
 * @property probabilidadAccidente La probabilidad de accidente del piloto
 * @property tiempo El tiempo acumulado del piloto en la carrera
 */
class Stroll(
    override val nombre: String = "Lance Stroll",
    override val probabilidadAccidente: Int = 20,
    override var tiempo: Int = 0
) : Piloto {

    /**
     * Inicializa la posición del piloto en la parrilla de salida.
     *
     * @param parrilla La matriz que representa la parrilla de salida.
     */
    override fun inicializarPiloto(parrilla: Array<Array<Piloto?>>) {
        parrilla[3][0] = this
    }
}
