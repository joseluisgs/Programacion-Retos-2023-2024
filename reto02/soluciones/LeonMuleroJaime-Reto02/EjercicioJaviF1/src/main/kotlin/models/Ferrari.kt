package models

interface Ferrari{

    /**
     * Función que calcula la probabilidad de que un piloto sufra mala estrategia
     * @since 1.0.0
     * @author Jaime Leon Mulero
     * @return Boolean
     */
    fun malaEstrategia(): Boolean {
        val random = (0..100).random()
        if (random <= 7) {
            return true
        } else {
            return false
        }
    }

    /**
     * Función que calcula la probabilidad de que un piloto sufra problemas de fiabilidad
     * @since 1.0.0
     *      * @author Jaime Leon Mulero
     *      * @return Boolean
     */
    fun problemasFiabilidad(): Boolean {
        val random = (0..100).random()
        if (random <= 10) {
            return true
        } else {
            return false
        }
    }

}