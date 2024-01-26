package models


import kotlin.random.Random

/**
 * Clase que representa la escudería Red Bull.
 *
 * @constructor Crea una instancia de la escudería con el nombre y los pilotos especificados.
 * @property nombre El nombre de la escudería.
 * @property piloto1 El primer piloto de la escudería.
 * @property piloto2 El segundo piloto de la escudería.
 */
class RedBull(
    nombre: String
) : Escuderia(nombre, Verstappen(), Checo()) {

    /**
     * Ejecuta eventos específicos para la escudería Red Bull.
     * En este caso, se verifica si se ha realizado una vuelta rápida.
     */
    override fun ejecutarEventos() {
        vueltaRapida()
    }

    /**
     * Realiza una vuelta rápida con una probabilidad del 10%.
     *
     * @return `true` si se ha realizado una vuelta rápida, `false` en caso contrario.
     */
    private fun vueltaRapida(): Boolean {
        val random = Random.nextInt(100)
        if (random < 10) {
            println("$nombre ha hecho una vuelta rápida")
            return true
        }
        return false
    }
}
