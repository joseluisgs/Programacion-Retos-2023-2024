package models

/**
 * Clase que representa al personaje Ron, un aliado en la aventura.
 *
 * @property cura Valor de curación proporcionado por Ron al realizar la acción de curar.
 * @property error Probabilidad de error al realizar la acción de probabilidad (1 al 10, donde 1 es 10% y 10 es 100%).
 * @property daño Valor de daño infligido por Ron al realizar la acción de atacar.
 */
class Ron : Aliados(cura = 20) {

    /**
     * Valor de error al realizar la acción de probabilidad (1 al 10, donde 1 es 10% y 10 es 100%).
     */
    val error: Int = 3

    /**
     * Valor de daño infligido por Ron al realizar la acción de atacar.
     */
    val daño = 10

    /**
     * Realiza la acción de curar, devolviendo el valor de curación proporcionado por Ron.
     *
     * @return Valor de curación proporcionado por Ron.
     */
    override fun curar(): Int {
        return cura
    }

    /**
     * Realiza la acción de atacar, devolviendo el valor de daño infligido por Ron y mostrando un mensaje.
     *
     * @return Valor de daño infligido por Ron.
     */
    fun atacar(): Int {
        println("Ron falla y te ataca, pierdes puntos $daño")
        return daño
    }

    /**
     * Realiza la acción de probabilidad, eligiendo entre atacar o curar según la probabilidad de error.
     * Muestra un mensaje indicando el resultado.
     *
     * @return Valor de daño infligido por Ron si la probabilidad de error se cumple, o valor de curación si no se cumple.
     */
    fun probabilidad(): Int {
    if ((1..10).random() <= error) {
        return atacar()
    } else {
        return curar()
    }
        println("Éxito")
    }

}
