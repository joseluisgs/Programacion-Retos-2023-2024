package models

/**
 * Clase para el personaje Harry
 * @property nombre nombre del personaje
 * @property cantidadCura cantidad de cura que realiza
 * @property vida Vida total del personaje
 * @author Jaime Leon Mulero
 * @since 1.0.0
 * @see PersonajeBueno Clase Padre
 */
class Harry (
    nombre: String = "Harry",
    cantidadCura: Int = 0,
    var vida: Int = 100
): PersonajeBueno (nombre, cantidadCura) {

    override fun toString(): String {
        return "Vida actual de Harry: $vida"
    }
}