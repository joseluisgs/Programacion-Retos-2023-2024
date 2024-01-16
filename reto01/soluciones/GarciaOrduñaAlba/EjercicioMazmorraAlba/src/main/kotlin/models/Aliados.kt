package models

import org.example.models.Personaje
/**
 * Clase base que representa a los aliados en la aventura.
 *
 * @property cura Valor de curación proporcionado por los aliados.
 */

open class Aliados(open var cura:Int): Personaje(){
    /**
     * Realiza la acción de curar, devolviendo el valor de curación proporcionado por los aliados y mostrando un mensaje.
     *
     * @return Valor de curación proporcionado por los aliados.
     */
    open fun curar(): Int {
        println("Aliados curan")
        return cura
    }
}


