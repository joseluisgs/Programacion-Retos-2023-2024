package models

private const val PROB_CURAR_RON = 30
private const val CURA_RON = 20
private const val DAÑO_RON = 10

/**
 * Clase para el personaje Ron
 * @property nombre nombre del personaje
 * @property cantidadCura cantidad de cura que realiza
 * @author Jaime Leon Mulero
 * @since 1.0.0
 * @see PersonajeBueno Clase Padre
 */
class Ron (
    nombre: String = "Ron",
    cantidadCura: Int = CURA_RON
): PersonajeBueno(nombre, cantidadCura) {

    /**
     * Funcion que cura con el personaje Ron
     * @param harry personaje al cual curamos
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    override fun curar(harry: Harry) {
        println("Te has encontrado a $nombre")
        println("Decide lanzarte un hechizo aumentando tu vida $cantidadCura puntos")
        val random = (0..100).random()
        if (random <= PROB_CURAR_RON) {
            println("Sin embargo, debido a su torpeza, Ron te quita $DAÑO_RON puntos de vida")
            harry.vida -= DAÑO_RON
        } else {
            harry.vida += CURA_RON
        }
        println("$nombre huye de la mazmorra")
    }

}