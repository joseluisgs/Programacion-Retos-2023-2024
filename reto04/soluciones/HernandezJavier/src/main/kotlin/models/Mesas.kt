package org.example.models

class Mesas(val fila: Int, val columna: Int, var free: Boolean = true, var tiempoComer: Int = 5, var numClientes: Int = 0) : Colocarse {
    override fun colocar( restaurante: Array<Array<Any?>>): Array<Array<Any?>> {
        restaurante[this.fila][this.columna] = this
        return restaurante
    }

    fun sentarComensal(){
        val random = (0..100).random()
        if(random < 35){
            println("Un cliente quiere sentarse en una mesa")
            if(!free){
                println("Las mesas en este momento están llenas, disculpe las molestias")
            }else{
                println("Pase por aquí caballero")
                this.free = false
            }
        }
    }

    fun recibirMenu(camarero: Camareros){
        camarero.platoEntregado = true
        println("Muchas gracias")
        numClientes++
        println("La cuenta por favor")
        println("Hasta luego")
        tiempoComer = 3
        this.free = true
        camarero.comensalSatisfecho = true
        camarero.comandaRecibida = false
        camarero.platoRecibido = false
        }
    }
