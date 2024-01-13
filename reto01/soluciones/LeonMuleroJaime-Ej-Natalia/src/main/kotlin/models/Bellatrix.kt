package models

private const val DAÑO_BELLATRIX = 30

/**
 * Clase para el personaje Bellatrix
 * @property nombre nombre del personaje
 * @property cantidadDaño cantidad de daño que realiza
 * @author Jaime Leon Mulero
 * @since 1.0.0
 * @see PersonajeMalo Clase Padre
 */
class Bellatrix (
    nombre: String = "Bellatrix",
    cantidadDaño: Int = DAÑO_BELLATRIX
): PersonajeMalo (nombre, cantidadDaño){

}