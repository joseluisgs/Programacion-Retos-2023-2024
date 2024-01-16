package org.example.models


/**
 * Clase que define el objeto inmovil Horrocrux
 * @param destruidos referencia a la cantidad de objetos que hemos destruido hasta el momento.
 * @see Ministerio
 * @author Javier Ruiz
 * @since 1.0.0
 */
class Horrocrux: Personaje(){

     var destruidos=0


    /**
     * Funcion que realizamos cuando nos encontramos un horrocrux.
     */
    fun destruirHorrocruxes(){
        println("He encontrado un horrocrux y ahora lo destruire por el poder de la amistad")
        destruidos++
    }

    override fun toString(): String {
       return "He destruido un total de $destruidos horrocruxes"
    }

}