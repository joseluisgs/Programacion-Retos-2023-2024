package org.example.controllers

import org.example.models.Camareros
import org.example.models.Cocineros
import org.example.models.Mesas

class Restaurante {
    val restaurante = Array(6){ arrayOfNulls<Any?>(3) }
    private val cocinero1 = Cocineros(5,0)
    private val cocinero2 = Cocineros(5,1)
    private val cocinero3 = Cocineros(5,2)
    private val camarero1 = Camareros(4,0)
    private val camarero2 = Camareros(4,1)
    private val camarero3 = Camareros(4,2)
    private val mesa1 = Mesas(0,0)
    private val mesa2 = Mesas(0,1)
    private val mesa3 = Mesas(0,2)
    private var time = 0

    init {
        incializarRestaurante()
        imprimirRestaurante()
        jornada()
    }

    fun jornada(){
        do{
            imprimirRestaurante()
            mesa1.sentarComensal()
            mesa2.sentarComensal()
            mesa3.sentarComensal()
            camarero1.trabajoCamarero(this.restaurante, mesa1, cocinero1)
            camarero2.trabajoCamarero(this.restaurante, mesa2, cocinero2)
            camarero3.trabajoCamarero(this.restaurante, mesa3, cocinero3)
            Thread.sleep(1000)
            time+=1000
        }while (time < 40000)
        println("Número de clientes del servicio de hoy : ${mesa1.numClientes + mesa2.numClientes + mesa3.numClientes}")
    }

    private fun incializarRestaurante() {
        camarero1.colocar(this.restaurante)
        camarero2.colocar(this.restaurante)
        camarero3.colocar(this.restaurante)
        cocinero1.colocar(this.restaurante)
        cocinero2.colocar(this.restaurante)
        cocinero3.colocar(this.restaurante)
        mesa1.colocar(this.restaurante)
        mesa2.colocar(this.restaurante)
        mesa3.colocar(this.restaurante)

    }

    private fun imprimirRestaurante(){
        for(i in this.restaurante.indices){
            for(j in this.restaurante[i].indices){
                when(this.restaurante[i][j]){
                    null -> print("[    ]")
                    is Camareros -> print("[ 🤵 ]")
                    is Mesas -> print("[ 🪑 ]")
                    is Cocineros -> print("[ 🧑‍🍳 ]")
                }
            }
            println()
        }
        println()
    }
}