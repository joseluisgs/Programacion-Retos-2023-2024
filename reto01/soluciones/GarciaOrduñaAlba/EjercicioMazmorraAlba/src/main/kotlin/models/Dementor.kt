package models
/**
 * Clase que representa al personaje Dementor, un enemigo en la aventura.
 *
 * @param daño Valor de daño infligido por Dementor.
 */
class Dementor:Enemigos(daño=10) {
    /**
     * Realiza la acción de atacar, devolviendo el valor de daño infligido por Dementor y mostrando un mensaje.
     *
     * @return Valor de daño infligido por Dementor.
     */
    override fun atacar(): Int {
        println("Dementor atacando")
        return daño
    }
}