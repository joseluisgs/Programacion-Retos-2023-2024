package models

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 * @property nombre del personaje/amigo
 * @property puntosVida vida que suma a harry si se encuentran
 * @property danio daño que hace en caso de error
 * @property error numero que define si ron falla
 */
class Ron(
    private val error: Int = (1..10).random(),
    private val danio: Int = 10,
    ): Amigos("🐭 Ron 🐭",20)
{
        var valor : Int =0

    /**
     * @author Mario de Domingo
     * @version 1.0-SNAPSHOT
     * @return si falla o acierta
     */
    override fun ayudar(){
        if (error<=3){
            println("El torpe de 🐭RON🐭 ha fallado y te ha quitado 10 puntos de vida")
            valor = danio
        }else{
            println("Has tenido suerte, 🐭RON🐭 no ha fallado y te ha dado 20 puntos de Vida")
            valor = puntosVida
        }

    }
}