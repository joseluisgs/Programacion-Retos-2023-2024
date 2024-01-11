package org.example.models

class Mortifago: Enemigo() {

    var hp=10
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