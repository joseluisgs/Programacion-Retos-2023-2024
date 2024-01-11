package org.example.models

class Bellatrix: Enemigo() {
    var dmg=30

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