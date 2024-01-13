package models

private const val DAÑO_DEMENTOR = 10
private const val PROB_ATAQUE_EFECTIVO = 60
private const val PROB_RECIBIR_ATAQUE = 50

/**
 * Clase para el personaje Dementor
 * @property nombre nombre del personaje
 * @property cantidadDaño cantidad de daño que realiza
 * @author Jaime Leon Mulero
 * @since 1.0.0
 * @see PersonajeMalo Clase Padre
 */
class Dementor (
    nombre: String = "Dementor",
    cantidadDaño: Int = DAÑO_DEMENTOR
): PersonajeMalo (nombre, cantidadDaño){

    /**
     * Funcion que realiza el ataque de un dementor
     * @param mapa talero donde se ejecuta el juego
     * @param harry Objeto al cual se le realiza el ataque
     * @param enemy nombre del enemigo
     * @author Jaime Leon Mulero
     * @since 1.0.0
     * @see selectRandomPlace
     */
    override fun ataque(mapa: Array<Array<Celda>>, harry: Harry, enemy: String) {
        println("\nTe has encontrado con un $nombre")
        println("Decides atacarle...")
        val random = (0..100).random()
        if (random <= PROB_ATAQUE_EFECTIVO) {
            println("El ataque ha sido efectivo y el Dementor ha muerto")
        } else {
            println("El ataque ha fallado pero...")
            val random2 = (0..100).random()
            if (random2 <= PROB_RECIBIR_ATAQUE) {
                println("Tu defensa ha fallado y recibes daño, Dementor huye a una nueva posición")
                harry.vida -= DAÑO_DEMENTOR
                val posicionDementor = selectRandomPlace(mapa)
                mapa[posicionDementor.fila][posicionDementor.columna].personaje = Dementor()
            } else {
                println("Tu defensa ha sido efectiva, no recibes daño pero Dementor huye a una nueva posición")
                val posicionDementor = selectRandomPlace(mapa)
                mapa[posicionDementor.fila][posicionDementor.columna].personaje = Dementor()
            }
        }
    }

}