package models

/**
 * Clase abstracta que representa una escudería de Fórmula 1.
 *
 * @constructor Crea una instancia de la escudería con el nombre y los pilotos especificados.
 * @property nombre El nombre de la escudería.
 * @property piloto1 El primer piloto de la escudería.
 * @property piloto2 El segundo piloto de la escudería.
 */
abstract class Escuderia(
    val nombre: String,
    val piloto1: Piloto,
    val piloto2: Piloto
) {
    /**
     * Método abstracto que debe ser implementado por las subclases.
     * Permite ejecutar eventos específicos para cada escudería.
     */
    abstract fun ejecutarEventos()
}
