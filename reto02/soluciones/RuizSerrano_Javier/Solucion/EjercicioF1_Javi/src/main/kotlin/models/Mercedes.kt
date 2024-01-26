package org.example.models

open class Mercedes: Parrilla() {

    fun calentada(): Boolean{
        var posCagarla=20
        if((1..100).random() < posCagarla){

            return true
        }else{
            return false
        }

    }

}