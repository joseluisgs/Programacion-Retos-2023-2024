package org.example.models

/**
 * Clase que representa a los aliados del juego.
 *
 * @property cura cantidad de puntos de vida que sumará a la vida de la clase Potter
 */
open class Aliados(var cura : Int = 0) : Personaje() {

    /**
     * Función que suma la cura del aliado a la vida la clase Potter.
     *
     * @param potter variable que llama a la clase Potter
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    fun curar(potter: Potter){
        potter.vida += cura
        if (potter.vida > 100){
            potter.vida=100
        }
    }
}