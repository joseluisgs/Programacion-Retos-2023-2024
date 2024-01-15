package models

private const val PROB_ATAQUE_EFECTIVO = 60
private const val PROB_RECIBIR_ATAQUE = 50

/**
 * Clase abstracta que engloba a todos los personajes malos
 * @see Personaje Clase Padre
 * @property nombre Especifica el nombre de cada personaje
 * @property cantidadDaño Especifica la cantidad de daño de cada personaje
 * @author Jaime Leon Mulero
 * @since 1.0.0
 */
abstract class PersonajeMalo(
    nombre: String,
    val cantidadDaño: Int
): Personaje(nombre), RandomPlace {

    /**
     * Funcion en la cual se efectua un ataque
     * @param mapa Tablero donde se realiza el juego
     * @param harry Objeto harry al cual le restamos vida
     * @param enemy nombre del enemigo que realiza la acción
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    open fun ataque(mapa: Array<Array<Celda>>, harry: Harry, enemy: String) {
        println("Te has encontrado a $nombre")
        println("Decides atacarle...")
        val random = (0..100).random()
        if (random <= PROB_ATAQUE_EFECTIVO) {
            println("El ataque ha sido efectivo y $nombre huye a una nueva posición")
            val posicionEnemigo = selectRandomPlace(mapa)
            when (enemy) {
                "Voldermort" -> mapa[posicionEnemigo.fila][posicionEnemigo.columna].personaje = Voldermort()
                "Bellatrix" -> mapa[posicionEnemigo.fila][posicionEnemigo.columna].personaje = Bellatrix()
            }
        } else {
            println("El ataque ha fallado pero...")
            val random2 = (0..100).random()
            if (random2 <= PROB_RECIBIR_ATAQUE) {
                println("Tu defensa ha fallado y recibes daño, Volvermort huye a una nueva posición")
                harry.vida -= cantidadDaño
                val posicionEnemigo = selectRandomPlace(mapa)
                when (enemy) {
                    "Voldermort" -> mapa[posicionEnemigo.fila][posicionEnemigo.columna].personaje = Voldermort()
                    "Bellatrix" -> mapa[posicionEnemigo.fila][posicionEnemigo.columna].personaje = Bellatrix()
                }
            } else {
                println("Tu defensa ha sido efectiva, no recibes daño pero Voldermort huye a una nueva posición")
                val posicionEnemigo = selectRandomPlace(mapa)
                when (enemy) {
                    "Voldermort" -> mapa[posicionEnemigo.fila][posicionEnemigo.columna].personaje = Voldermort()
                    "Bellatrix" -> mapa[posicionEnemigo.fila][posicionEnemigo.columna].personaje = Bellatrix()
                }
            }
        }
    }
}