package controllers

import models.*
import repositories.matriz.*

const val NUMERO_CAMAREROS = 3
const val TAM_FILAS = 7
const val TAM_COLUMNAS = 3
const val ESPERA = 1000L

class Restaurante {

    private val cocinero = Cocinero()
    private var clientesEntrados = 0
    private var clientesDescontentos = 0
    private var recaudacion = 0.0
    private val camareros = mutableListOf<Camarero>()
    private val clientes = mutableListOf<Cliente>()
    private val matriz = MatrizImpl<String>(TAM_FILAS, TAM_COLUMNAS)

    init {
        for (i in 1..NUMERO_CAMAREROS) {
            camareros.add(Camarero(i,Camarero.EstadoCamarero.ESPERANDO))
        }
    }

    fun simular() {
        matriz.clear()
        asignarCamareros()
        println("Simulación del restaurante:")
        println(matriz.toString())
        println()

        while (clientesEntrados < 10 && matriz.matriz[0].any { it == null }) {
            Thread.sleep(ESPERA)
            val mesaDisponible = matriz.matriz[0].indexOfFirst { it == null }
            val cliente = if (mesaDisponible != -1) {
                Cliente(++clientesEntrados, mesaDisponible + 1, Menu.menuAleatorio())
            } else {
                null
            }

            if (cliente != null) {
                println("Cliente ${cliente.id} ha llegado y está buscando mesa...")

                println("Cliente ${cliente.id} se ha sentado en la mesa ${cliente.mesa}.")
                matriz.set(0, mesaDisponible, "🧔🏻")

                println(matriz.toString())
                println()
            } else {
                println("No hay mesas disponibles para el nuevo cliente.")
                clientesDescontentos++
            }
        }

        println("Clientes descontentos: $clientesDescontentos")
        println("Recaudación final: $recaudacion")
    }

    fun asignarCamareros() {
        for (camarero in camareros) {
            matriz.set(TAM_FILAS - 1, camarero.id - 1, "🧑🏻‍🍳")
        }
    }
}
