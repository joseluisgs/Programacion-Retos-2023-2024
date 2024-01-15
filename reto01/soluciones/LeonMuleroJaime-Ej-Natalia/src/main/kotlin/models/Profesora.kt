package models

private const val CURA_PROFESORA = 70

/**
 * Clase para el personaje Profesora McGanogall
 * @property nombre nombre del personaje
 * @property cantidadCura cantidad de cura que realiza
 * @author Jaime Leon Mulero
 * @since 1.0.0
 * @see PersonajeBueno Clase Padre
 */
class Profesora (
    nombre: String = "McGonagall",
    cantidadCura: Int = CURA_PROFESORA
): PersonajeBueno(nombre, cantidadCura){

}