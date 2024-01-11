package org.example.models

class Harry: Personaje() {

    var lifePoints=100
    var posicion=Array(2){0}

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