package models

interface Mercedes {

    /**
     * Función que calcula la probabilidad de que salga un safetyCar para el equipo de Mercedes
     * @since 1.0.0
     * @author Jaime Leon Mulero
     * @return Boolean
     */
    fun safetyCar(): Boolean {
        val random = (0..100).random()
        if (random <= 20) {
            return true
        } else {
            return false
        }
    }

}