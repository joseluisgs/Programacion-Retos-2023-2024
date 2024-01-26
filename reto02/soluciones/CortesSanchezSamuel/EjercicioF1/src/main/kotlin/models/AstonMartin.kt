package models

/**
 * Clase que representa la escudería Aston Martin.
 *
 * @constructor Crea una instancia de la escudería con el nombre y los pilotos por defecto (Alonso y Stroll).
 * @property nombre El nombre de la escudería.
 * @property piloto1 El primer piloto de la escudería.
 * @property piloto2 El segundo piloto de la escudería.
 */
class AstonMartin(
    nombre: String
) : Escuderia(nombre, Alonso(), Stroll()) {

    /**
     * Método que ejecuta eventos específicos para la escudería Aston Martin.
     * No implementado en esta clase concreta, pero puede ser sobrescrito en subclases.
     */
    override fun ejecutarEventos() {
    }
}
