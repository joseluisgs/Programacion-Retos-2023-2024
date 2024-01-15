package org.example.models


/**
 * Clase del personaje Principal
 * @param lifepoints referencia a la vida del personaje
 * @param posicion referencia a la posicion actual del personaje
 * @see Ministerio
 * @author Javier Ruiz
 * @since 1.0.0
 */
class Harry: Personaje() {

    var lifePoints=100
    var posicion=Array(2){0}

    /**
     * Funcion que realiza cuando se encuentra con un mortifago en la matriz
     * @author Javier Ruiz
     * @since 1.0.0
     */
    fun atacar() : Boolean {
        if((1..100).random()<60){
            println("Harry se ha limpiado las gafas y ha logrado un impacto pum")
            return true
        }else{
            println("Harry se ha pasado con la cerveza de mantequilla y ha fallado")
            return false
        }
    }
}