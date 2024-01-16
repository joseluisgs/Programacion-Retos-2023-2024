package org.example.models


/**
 * Clase para el personaje principal de Bellatrix
 * @see Ministerio
 * @see Enemigo
 * @param dmg es la cantidad de daño que inflige Bellatrix
 * @author Javier Ruiz
 * @since 1.0.0
 */
class Bellatrix: Enemigo() {
    var dmg=30


    /**
     * Funcion que realiza cuando se encuentra con potter en la matriz
     * @author Javier Ruiz
     * @since 1.0.0
     */
    override fun atacar(): Boolean{
        if((1..100).random()<50){
            println("Bellatrix te ha lanzado un Crucio, te retuerces de dolor")
            return true
        }else{
            println("Bellatrix sigue celebrando la muerte de Sirius con un baile de Fortnite")
            return false
        }
    }

}