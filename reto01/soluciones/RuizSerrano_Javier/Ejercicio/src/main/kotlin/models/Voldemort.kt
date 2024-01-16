package org.example.models


/**
 * Clase del enemigo llamado Voldemort
 * @param dmg cantidad de daño infligido si acierta el ataque
 * @see Ministerio
 * @see Enemigo
 * @author Javier Ruiz
 * @since 1.0.0
 */
class Voldemort: Enemigo() {
    var dmg=70
    override fun atacar() : Boolean {
        if((1..100).random()<70){
            println("QuienNoDebeSerNombrado lanza un Avada Kedavra con malas intenciones claramente")
            return true
        }else{
            println("QuienNoDebeSerNombrado se ha pasado con la cerveza de mantequilla y ha fallado")
            return false
        }
    }
}