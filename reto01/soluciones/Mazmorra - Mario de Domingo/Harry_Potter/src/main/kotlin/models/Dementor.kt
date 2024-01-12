package models

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 * @property nombre del personaje/amigo
 * @property danio que inflingen
 */
data class Dementor(var numDementores: Int = 6) : Enemigos("👻 Dementor 👻", -10) {
    /**
     * @author Mario de Domingo
     * @version 1.0-SNAPSHOT
     * @return texto casilla dementor
     */
    fun casilla() {
        println("¡OH NO! un oscuro 👻DEMENTOR👻")
    }
}


