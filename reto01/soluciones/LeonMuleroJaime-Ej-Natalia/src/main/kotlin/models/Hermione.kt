package models

private const val CURA_HERMIONE = 30

/**
 * Clase para el personaje Hermione
 * @property nombre nombre del personaje
 * @property cantidadCura cantidad de daño que realiza
 * @author Jaime Leon Mulero
 * @since 1.0.0
 * @see PersonajeBueno Clase Padre
 */
class Hermione (
    nombre: String = "Hermione",
    cantidadCura: Int = CURA_HERMIONE
): PersonajeBueno (nombre, cantidadCura){

}