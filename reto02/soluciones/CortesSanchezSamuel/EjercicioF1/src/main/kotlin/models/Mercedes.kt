package models

/**
 * Clase que representa la escudería Mercedes.
 *
 * @constructor Crea una instancia de la escudería con el nombre y los pilotos especificados.
 * @property nombre El nombre de la escudería.
 * @property piloto1 El primer piloto de la escudería.
 * @property piloto2 El segundo piloto de la escudería.
 */
class Mercedes(
    nombre: String
) : Escuderia(nombre, Hamilton(), Russel()) {

    /**
     * Método que ejecuta eventos específicos para la escudería Mercedes.
     * No implementado en esta clase concreta, pero puede ser sobrescrito en subclases.
     */
    override fun ejecutarEventos() {
        // Implementar eventos específicos de la escudería Mercedes si es necesario.
    }
}
