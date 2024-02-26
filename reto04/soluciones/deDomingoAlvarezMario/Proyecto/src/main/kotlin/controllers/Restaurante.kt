package org.example.controllers

import org.example.models.Camarero
import org.example.models.Mesa
import java.lang.Thread.sleep

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 *
 * Clase que representa un restaurante.
 *
 * @property restaurante Matriz que representa las mesas del restaurante.
 * @property clientesAtendidos Número de clientes atendidos en el restaurante.
 * @property camarero1 Primer camarero del restaurante.
 * @property camarero2 Segundo camarero del restaurante.
 * @property camarero3 Tercer camarero del restaurante.
 * @property finTurno Indica si ha finalizado el turno en el restaurante.
 * @property mesa1 Primera mesa del restaurante.
 * @property mesa2 Segunda mesa del restaurante.
 * @property mesa3 Tercera mesa del restaurante.
 * @property tiempo Tiempo transcurrido en el restaurante.
 */
class Restaurante {
    private val restaurante: Array<Array<Any?>> = Array(8) { Array(3) { null } }
    private var clientesAtendidos = 0
    private val camarero1 = Camarero(0, 5)
    private val camarero2 = Camarero(1, 5)
    private val camarero3 = Camarero(2, 5)
    private var finTurno = false
    private val mesa1 = Mesa(0)
    private val mesa2 = Mesa(1)
    private val mesa3 = Mesa(2)
    private var tiempo = 0

    /**
     * Simula el funcionamiento del restaurante.
     */
    fun simulate() {
        inicializarMatriz()
        imprimirMatrix()

        do {
            sleep(500)

            camarero(mesa1, camarero1)
            camarero(mesa2, camarero2)
            camarero(mesa3, camarero3)

            imprimirMatrix()

            if (clientesAtendidos == 10) {
                finTurno = true
            }

            tiempo += 1

        } while (!finTurno)

        println("El turno ha terminado")
        println("El restaurante ha atendido $clientesAtendidos clientes")
    }

    private fun camarero(mesa: Mesa, camarero: Camarero) {
        if (mesa.ocupada) {
            restaurante[camarero.i][camarero.j] = null
            camarero.accion()
            restaurante[camarero.i][camarero.j] = camarero
            if (camarero.i == 1) {
                atenderMesa(mesa, camarero)
            }
        } else {
            mesa.ocupada()
        }
    }

    private fun atenderMesa(mesa: Mesa, camarero: Camarero) {
        when (camarero.veces) {
            1 -> camarero.primeraVezMesa(mesa)
            2 -> camarero.primerPlato(mesa)
            3 -> camarero.segundoPlato(mesa)
            4 -> camarero.postre(mesa)
            5 -> cuentaPagada(mesa, camarero)
        }
        camarero.veces++
    }

    private fun cuentaPagada(mesa: Mesa, camarero: Camarero) {
        println("La Mesa ${mesa.i + 1} ha pagado con éxito")
        camarero.veces = 0
        clientesAtendidos++
        mesa.ocupada = false
        restaurante[camarero.i][camarero.j] = null
        camarero.i = 5
        restaurante[camarero.i][camarero.j] = camarero
    }

    private fun inicializarMatriz() {
        for (j in restaurante[7].indices) {
            restaurante[7][j] = "Cocina"
        }
        for (j in restaurante[6].indices) {
            restaurante[6][j] = "separacion"
        }
        restaurante[5][camarero1.j] = camarero1
        restaurante[5][camarero2.j] = camarero2
        restaurante[5][camarero3.j] = camarero3
        restaurante[0][mesa1.i] = mesa1
        restaurante[0][mesa2.i] = mesa2
        restaurante[0][mesa3.i] = mesa3
    }

    /**
     * Imprime la representación visual del restaurante.
     */
    private fun imprimirMatrix() {
        println("╔═════════╗")

        for (i in restaurante.indices) {

            print("║")

            for (j in restaurante[i].indices) {
                when (restaurante[i][j]) {
                    "separacion" -> print("═══")
                    "Cocina" -> print(" C ")
                    is Mesa -> print(" M ")
                    is Camarero -> print(" 0 ")
                    else -> print("   ")
                }
            }
            print("║")
            println()
        }
        println("╚═════════╝")
        println()
    }
}
