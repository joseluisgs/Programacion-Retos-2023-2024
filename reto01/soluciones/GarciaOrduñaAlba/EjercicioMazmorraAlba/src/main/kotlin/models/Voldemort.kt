package models
/**
 * Clase que representa al personaje Voldemort, un enemigo en la aventura.
 *
 * @param daño Valor de daño infligido por Voldemort.
 */
class Voldemort(): Enemigos(daño =60){
    /**
     * Realiza la acción de atacar, devolviendo el valor de daño infligido por Voldemort y mostrando un mensaje.
     *
     * @return Valor de daño infligido por Voldemort.
     */
    override fun atacar(): Int {
        println("Voldemort ataca")
        return daño
    }
}
