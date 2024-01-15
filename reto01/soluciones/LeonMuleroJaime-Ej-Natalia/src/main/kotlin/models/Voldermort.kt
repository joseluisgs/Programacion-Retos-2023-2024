package models

private const val DAÑO_VOLDERMORT = 70

/**
 * Clase para el personaje Voldermort
 * @property nombre nombre del personaje
 * @property cantidadDaño cantidad de daño que realiza
 * @author Jaime Leon Mulero
 * @since 1.0.0
 * @see PersonajeMalo Clase Padre
 */
class Voldermort (
    nombre: String = "Voldermort",
    cantidadDaño: Int = DAÑO_VOLDERMORT
): PersonajeMalo (nombre, cantidadDaño){

}