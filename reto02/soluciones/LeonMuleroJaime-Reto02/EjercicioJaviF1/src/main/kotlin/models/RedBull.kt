package models

interface RedBull {

    /**
     * Función que calcula la probabilidad de que un piloto haga vuelta rápida
     * @since 1.0.0
     * @author Jaime Leon Mulero
     * @return Boolean
     */
    fun vueltaRapida(): Boolean {
        val random = (0..100).random()
        if (random <= 10) {
            return true
        } else {
            return false
        }
    }

}