package models

interface AstonMartin {

    /**
     * Función que calcula si un piloto con la interfaz AstonMartin sufre mala estrategia
     * @since 1.0.0
     * @author Jaime Leon Mulero
     * @return Boolean
     */
    fun malaEstrategia(): Boolean {
        val random = (0..100).random()
        if (random <= 15) {
            return true
        } else {
            return false
        }
    }

}