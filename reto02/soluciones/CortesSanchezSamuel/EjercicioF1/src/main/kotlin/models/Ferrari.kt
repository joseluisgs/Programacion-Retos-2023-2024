package models

/**
 * Clase que representa la escudería Ferrari.
 *
 * @constructor Crea una instancia de la escudería con el nombre y los pilotos especificados.
 * @property nombre El nombre de la escudería.
 * @property piloto1 El primer piloto de la escudería.
 * @property piloto2 El segundo piloto de la escudería.
 */
class Ferrari(
    nombre: String
) : Escuderia(nombre, Sainz(), LeClerc()) {

    /**
     * Método que ejecuta eventos específicos para la escudería Ferrari.
     * No implementado en esta clase concreta, pero puede ser sobrescrito en subclases.
     */
    override fun ejecutarEventos() {
        // Implementar eventos específicos de la escudería Ferrari si es necesario.
    }
}
