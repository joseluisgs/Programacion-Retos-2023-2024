package org.example.models

open class AstonMartin: Parrilla() {

    fun malaEstrategia(): Boolean{
        var posCagarla=15
        if((1..100).random() < posCagarla){
            return true
        }else{
            return false
        }

    }
}