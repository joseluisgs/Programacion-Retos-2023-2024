package org.example.models


/**
 * Clase del enemigo comun
 * @param dmg cantidad de daño que inflingen al acertar un ataque.
 * @param muertos recuento de enemigos muertos hasta el momento
 * @see Enemigo
 * @see Ministerio
 * @author Javier Ruiz
 * @since 1.0.0
 */
class Mortifago: Enemigo() {


    var dmg=10
    var muertos=0
     override fun atacar(): Boolean{
        if((1..100).random()<40){
            println("El Mortifago ha acertado su ataque")
            return true
        }else{
            println("El Mortifago ha errado su ataque")
            return false
        }
    }

}