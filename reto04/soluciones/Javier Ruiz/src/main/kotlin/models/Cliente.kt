package org.example.models

class Cliente() {

    private val menu = Menu()
    fun pedir(){
        val comanda=menu.carta.random()
        println("Voy a elegir el menu: $comanda")
    }


}