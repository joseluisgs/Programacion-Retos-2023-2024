package org.example.models

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