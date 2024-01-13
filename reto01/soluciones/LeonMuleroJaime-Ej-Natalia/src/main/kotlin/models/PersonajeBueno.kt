package models

/**
 * Clase abstracta que engloba a todos los personajes buenos
 * @see Personaje Clase Padre
 * @property nombre Especifica el nombre de cada personaje
 * @property cantidadCura Especifica la cantidad de cura de cada personaje
 * @author Jaime Leon Mulero
 * @since 1.0.0
 */
abstract class PersonajeBueno(
    nombre: String,
    val cantidadCura: Int
): Personaje(nombre) {

    /**
     * Función que cura a harry
     * @param harry personaje al cual curamos
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    open fun curar(harry: Harry) {
        println("Te has encontrado a $nombre")
        println("Decide lanzarte un hechizo aumentando tu vida $cantidadCura puntos")
        println("$nombre huye de la mazmorra")
        harry.vida += cantidadCura
    }

}