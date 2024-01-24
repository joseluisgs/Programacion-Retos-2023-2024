package org.example.models

open class RedBull: Parrilla() {

    fun magia(): Boolean{
        val posVolar=15
        if((1..100).random() < posVolar){
            return true
        }else{
            return false
        }
    }

}