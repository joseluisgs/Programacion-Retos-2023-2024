package models
import mundo

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 * @property vida puntos que tiene harry al empezar el juego
 * @property nombre que heredamos de persoanjes
 */
data class Harry (var vida: Int = 0): Personajes("Harry"){

    /**
     * @author Mario de Domingo
     * @return vida restante de harry
     * @version 1.0-SNAPSHOT
     */
    fun vida(){
        println("Te quedan $vida puntos de vida")
        if(vida < 1){mundo.finJuego=true}
    }

    /**
     * @author Mario de Domingo
     * @return vida variada segun los puntos de daño o cura de sus amigos y enemigos
     * @version 1.0-SNAPSHOT
     */
    fun variarVida(numero : Int = 0){
        vida += numero
    }
}