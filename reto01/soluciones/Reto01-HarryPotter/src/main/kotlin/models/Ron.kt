package org.example.models

/**
 * Clase que representa al aliado "Ron" del juego.
 *
 * Hereda de la clase Aliados.
 *
 * @property cura cantidad de puntos de vida que sumará Ron a la vida de la clase Potter.
 * @property numFallo cantidad de puntos de vida que restará Ron a la vida de la clase Potter.
 */
class Ron (cura : Int, val numFallo : Int) : Aliados(cura){

    /**
     * Función que resta el fallo de Ron a la vida la clase Potter.
     *
     * @param potter variable que llama a la clase Potter
     * @author Natalia González
     * @since 1.0-SNAPSHOT
     */
    fun fallar(potter: Potter){
        potter.vida -= numFallo
    }
}