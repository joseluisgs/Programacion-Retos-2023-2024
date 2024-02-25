package org.example.controllers

import org.example.models.*
/**
 * La clase Restaurante modela un restaurante con camareros, mesas, y un menú de platos.
 * Permite simular el funcionamiento del restaurante durante un período de tiempo determinado.
 * @property restaurante Una matriz que representa el restaurante y sus diferentes áreas.
 * @property clientesAtendidos El número total de clientes atendidos en el restaurante.
 * @property recaudacionTotal El monto total recaudado por el restaurante.
 * @property camarero1, camarero2, camarero3 Los camareros que trabajan en el restaurante.
 * @property mesa1, mesa2, mesa3 Las mesas disponibles para los clientes.
 * @property comida El menú de platos disponibles en el restaurante.
 * @property tiempo El tiempo transcurrido durante la simulación del restaurante.
 */
class Restaurante {
    private val restaurante: Array<Array<Any?>> = Array(8) { Array(3) { null } }
    private var clientesAtendidos = 0
    private var recaudacionTotal = 0.0
    private val camarero1 = Camarero(0, 5)
    private val camarero2 = Camarero(1, 4)
    private val camarero3 = Camarero(2, 3)
    private val mesa1 = Mesa(0)
    private val mesa2 = Mesa(1)
    private val mesa3 = Mesa(2)
    private val comida = Comida()
    private var tiempo = 0

    /**
     * Imprime la representación visual del restaurante en consola.
     * Muestra la disposición de los camareros, mesas y cocina en el restaurante.
     */
    private fun imprimirMatriz() {
        println("Turno $tiempo:")
        println("Clientes atendidos: $clientesAtendidos")
        for (i in restaurante.indices) {
            for (j in restaurante[i].indices) {
                when (restaurante[i][j]) {
                    "Cocina" -> print("[👨🏻‍🍳 ] ") // Representa la cocina
                    is Mesa -> print("[🛎️ ]") // Representa una mesa
                    is Camarero -> print("[💁🏻 ]") // Representa un camarero
                    else -> print("[   ]") // Espacio vacío
                }
            }
            println()
        }
        println()
    }

    /**
     * Atiende a una mesa, sirviendo los platos pedidos por un camarero.
     * Calcula el precio total del menú y actualiza la recaudación del restaurante.
     * @param mesa La mesa que será atendida.
     * @param camarero El camarero que atiende la mesa.
     */
    private fun atenderMesa(mesa: Mesa, camarero: Camarero) {
        if (mesa.platosPedidos.isNotEmpty()) {
            camarero.servirPlatos(mesa)

            val menuIndex = comida.elegirMenu()
            val menuElegido = comida.menus[menuIndex]
            val precioTotalMenu = menuElegido.precio

            println("Precio total del menú de la mesa ${mesa.numero + 1}: $precioTotalMenu")
            recaudacionTotal += precioTotalMenu
            println("Recaudación total actualizada: $recaudacionTotal")

            cuentaPagada(mesa, camarero)
        }
        camarero.veces++
    }

    /**
     * Simula un turno en el restaurante, donde se atienden las mesas por los camareros.
     * Llama a la función de atender mesa para cada mesa ocupada por clientes.
     */
    private fun turno() {
        camarero(mesa1, camarero1)
        camarero(mesa2, camarero2)
        camarero(mesa3, camarero3)
        imprimirMatriz()
        tiempo++
    }

    /**
     * Atiende una mesa por un camarero, si está ocupada, o la ocupa si está vacía.
     * @param mesa La mesa que será atendida.
     * @param camarero El camarero que atenderá la mesa.
     */
    private fun camarero(mesa: Mesa, camarero: Camarero) {
        if (mesa.ocupada) {
            restaurante[camarero.i][camarero.j] = null
            camarero.accion()
            restaurante[camarero.i][camarero.j] = camarero
            if (camarero.i == 1) {
                atenderMesa(mesa, camarero)
            }
        } else {
            mesa.ocupar()
        }
    }

    /**
     * Marca una mesa como desocupada, actualiza la recaudación y reinicia el camarero.
     * @param mesa La mesa que ha terminado de comer y paga la cuenta.
     * @param camarero El camarero que atendía la mesa.
     */
    private fun cuentaPagada(mesa: Mesa, camarero: Camarero) {
        println("La Mesa ${mesa.numero + 1} ha terminado de comer y paga la cuenta💵")
        camarero.veces = 0
        clientesAtendidos++
        mesa.desocupar()
        restaurante[camarero.i][camarero.j] = null
        camarero.i = 5
        restaurante[camarero.i][camarero.j] = camarero
    }

    /**
     * Coloca los personajes (camareros y mesas) en sus posiciones iniciales en el restaurante.
     */
    private fun colocarPersonajes() {
        // Coloca la cocina en la fila superior
        for (j in restaurante[7].indices) {
            restaurante[7][j] = "Cocina"
        }

        // Coloca los camareros y las mesas en las posiciones correspondientes
        restaurante[5][camarero1.j] = camarero1
        restaurante[4][camarero2.j] = camarero2
        restaurante[3][camarero3.j] = camarero3
        restaurante[0][mesa1.numero] = mesa1
        restaurante[0][mesa2.numero] = mesa2
        restaurante[0][mesa3.numero] = mesa3
    }

    /**
     * Simula el funcionamiento del restaurante durante un período de tiempo.
     * Atiende a los clientes, lleva el control del tiempo y muestra estadísticas al final.
     */
    fun simular() {
        colocarPersonajes()
        imprimirMatriz()

        // Simula el funcionamiento del restaurante hasta que se atiendan 10 clientes
        while (clientesAtendidos < 10) {
            Thread.sleep(1000)
            turno()
        }

        // Imprime estadísticas al final de la simulación
        println("El turno ha terminado")
        println("El restaurante ha atendido $clientesAtendidos clientes")
        println("Recaudación total: $recaudacionTotal")
    }
}