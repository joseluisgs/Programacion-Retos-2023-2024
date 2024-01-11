package org.example.models

class Horrocrux: Personaje(){

     var destruidos=0

    fun destruirHorrocruxes(){
        println("He encontrado un horrocrux y ahora lo destruire por el poder de la amistad")
        destruidos++
    }

    override fun toString(): String {
       return "He destruido un total de $destruidos horrocruxes"
    }

}