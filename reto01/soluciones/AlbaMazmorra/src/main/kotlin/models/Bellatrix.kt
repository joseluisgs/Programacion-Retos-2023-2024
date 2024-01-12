package models
/**
 * Clase que representa al personaje Bellatrix, un enemigo en la aventura.
 *
 * @param daño Valor de daño infligido por Bellatrix.
 */
class Bellatrix: Enemigos(daño = 30) {
    /**
     * Realiza la acción de atacar, devolviendo el valor de daño infligido por Bellatrix y mostrando un mensaje.
     *
     * @return Valor de daño infligido por Bellatrix.
     */
    override fun atacar(): Int {
        println("Bellatrix ataca y nos hace $daño puntos ")
        return daño
    }
}

