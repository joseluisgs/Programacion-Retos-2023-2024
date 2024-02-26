package org.example.controllers

import org.example.models.*
import org.example.models.personajes.*

object Ratatouille {
    var restaurante: Array<Array<Casilla>> = Array(7) { Array(3) { Casilla() } }

    private val mesa1 = Mesa(1)
    private val mesa2 = Mesa(2)
    private val mesa3 = Mesa(3)

    private val camarero1 = Camarero(1)
    private val camarero2 = Camarero(2)
    private val camarero3 = Camarero(3)

    private val cocinero1 = Cocinero()
    private val cocinero2 = Cocinero()
    private val cocinero3 = Cocinero()

    private val arrayCamareros = arrayOf(camarero1, camarero2, camarero3)
    private val arrayMesas = arrayOf(mesa1, mesa2, mesa3)
    private val arrayPlatos = arrayOf("entrecote", "ensalada", "pizza", "hamburguesa", "spaghetti")

    private var contadorClientes = 0

    private var recaudacion = 0

    fun simulacion(){
        posicionesIniciales()

        do {
            println("Contador de clientes: $contadorClientes")
            imprimirRestaurante()
            println()

            if (contadorClientes != 10) {
                val clienteRandom = (1..3).random()
                if (clienteRandom == 3) {
                    val clienteMasUno = nuevoCliente()
                    if (clienteMasUno) contadorClientes++
                }
            }

            moveAllCamareros()

            Thread.sleep(1000) //1 segundo

            println("\n--------------------------\n")
        } while (contadorClientes != 10 || !comprobarFinCamareros())

        println("\nClientes totales de hoy: $contadorClientes")
        println("Recaudación total obtenida: $recaudacion")
    }

    private fun comprobarFinCamareros(): Boolean {
        var finCamarero1 = false
        var finCamarero2 = false
        var finCamarero3 = false

        for (camarero in arrayCamareros) {
            if (!camarero1.clienteEnMesa) finCamarero1 = true
            if (!camarero2.clienteEnMesa) finCamarero2 = true
            if (!camarero3.clienteEnMesa) finCamarero3 = true
        }

        if (finCamarero1 && finCamarero2 && finCamarero3) return true
        else return false
    }

    private fun nuevoCliente(): Boolean {
        var clienteSentado = true
        val mesa = arrayMesas.random()
        val emptyMesa = mesa.comprobarMesaLibre(mesa)
        if (emptyMesa) placeCliente(mesa)
        else clienteSentado = false
        return clienteSentado
    }

    private fun placeCliente(mesa: Mesa) {
        mesa.cliente = true
        mesa.plato = arrayPlatos.random()
        when (mesa.plato) {
            "entrecote" -> recaudacion += 18
            "ensalada" -> recaudacion += 10
            "pizza" -> recaudacion += 15
            "hamburguesa" -> recaudacion += 8
            "spaghetti" ->  recaudacion += 12
        }
        println("Se ha sentado un cliente en la mesa ${mesa.id} y ha pedido ${mesa.plato}")
    }

    private fun moveAllCamareros() {

        var filaMesa = 1
        var columna = 0
        val filaCocinero = 5
        funcionesRestaurante(filaMesa, columna, camarero1, mesa1, cocinero1)

        filaMesa = 1
        columna = 1
        funcionesRestaurante(filaMesa, columna, camarero2, mesa2, cocinero2)

        filaMesa = 1
        columna = 2
        funcionesRestaurante(filaMesa, columna, camarero3, mesa3, cocinero3)
    }

    private fun funcionesRestaurante(
        filaMesa: Int,
        columna: Int,
        camarero: Camarero,
        mesa: Mesa,
        cocinero: Cocinero
    ) {
        camarero.comprobarMesaConCliente(mesa, camarero)
        camarero.direccionCamarero(camarero)

        if (restaurante[filaMesa][columna].persona == camarero && camarero.plato == "") {
            camarero.plato = mesa.plato
            println("El camarero ${camarero.id} ha recogido la comanda y va a entregarla al cocinero")
        } else if (restaurante[filaMesa][columna].persona == camarero && camarero.plato != "") {
            println("El camarero ${camarero.id} entrega el plato de ${camarero.plato} en la mesa ${mesa.id}")
            camarero.plato = ""
            mesa.clienteComiendo = 5
        }

        if (restaurante[5][columna].persona == camarero && camarero.plato != ""  && cocinero.cocinando == 0) {
            cocinero.cocinando = 4
            println("El cocinero recoge la comanda y comienza a cocinar el plato del camarero ${camarero.id}")
        }

        if (cocinero.cocinando != 0) cocinero.cocinando--

        if (mesa.clienteComiendo != 0) {
            mesa.clienteComiendo--
            if (mesa.clienteComiendo == 0) {
                camarero.clienteEnMesa = false
                mesa.cliente = false
                println("El cliente en la mesa ${mesa.id} ha terminado de comer y se ha ido")
            }
        }

        if (camarero.clienteEnMesa && cocinero.cocinando == 0) camarero.moverCamarero(camarero)
    }

    private fun posicionesIniciales() {
        restaurante[0][0].persona = mesa1
        restaurante[0][1].persona = mesa2
        restaurante[0][2].persona = mesa3

        restaurante[5][0].persona = camarero1
        restaurante[5][1].persona = camarero2
        restaurante[5][2].persona = camarero3

        restaurante[6][0].persona = cocinero1
        restaurante[6][1].persona = cocinero2
        restaurante[6][2].persona = cocinero3
    }

    private fun imprimirRestaurante(){
        for (i in restaurante.indices) {
            for (j in restaurante[i].indices) {
                when (restaurante[i][j].persona) {
                    is Mesa -> print("[🪑] ")
                    is Cocinero -> print("[👨🏽‍🍳] ")
                    is Camarero -> print("[🥸] ")
                    is Rata -> print("[🐀] ")
                    else -> print("[◼️] ")
                }
            }
            println()
        }
    }

}