package org.example.models.base

import org.example.repositories.colas.Cocina
import org.example.repositories.colas.mesas.Mesa1
import org.example.repositories.colas.mesas.Mesa2
import org.example.repositories.colas.mesas.Mesa3
import org.example.models.personajes.Camarero1
import org.example.models.personajes.Camarero2
import org.example.models.personajes.Camarero3

private const val TAM_FILAS = 6
private const val TAM_COLUMNAS = 3

/**
 * Clase que representa el restaurante, con sus mesas, cocina y camareros.
 */
class Restaurante {

    /**
     * Matriz bidimensional que representa el restaurante.
     */
    private val restaurante: Array<Array<Any?>> = Array(TAM_FILAS) { arrayOfNulls<Any>(TAM_COLUMNAS) }

    /**
     * Variable que llama a la clase Camarero1.
     */
    private val camarero1: Camarero1 = Camarero1()

    /**
     * Variable que llama a la clase Camarero2.
     */
    private val camarero2: Camarero2 = Camarero2()

    /**
     * Variable que llama a la clase Camarero3.
     */
    private val camarero3: Camarero3 = Camarero3()

    /**
     * Variable que llama a la clase Mesa1.
     */
    private val mesa1: Mesa1 = Mesa1()

    /**
     * Variable que llama a la clase Mesa2.
     */
    private val mesa2: Mesa2 = Mesa2()

    /**
     * Variable que llama a la clase Mesa3.
     */
    private val mesa3: Mesa3 = Mesa3()

    /**
     * Variable que llama a la clase Cocina.
     */
    private val cocina: Cocina = Cocina()

    /**
     * Mueve a los personajes en el restaurante.
     */
    private fun moverPersonajes() {
        camarero1.mover(restaurante)
        camarero2.mover(restaurante)
        camarero3.mover(restaurante)
    }

    /**
     * Posiciona a los personajes en el restaurante.
     */
    private fun posicionarPersonajes() {
        mesa1.posicionar(restaurante)
        mesa2.posicionar(restaurante)
        mesa3.posicionar(restaurante)
        cocina.posicionar(restaurante)
        camarero1.posicionar(restaurante)
        camarero2.posicionar(restaurante)
        camarero3.posicionar(restaurante)
    }

    /**
     * Imprime el estado actual del restaurante.
     */
    private fun imprimirRestaurante() {
        for (i in restaurante.indices) {
            for (j in restaurante[i].indices) {
                when (restaurante[i][j]) {
                    null -> print("[    ]")
                    is Mesa1, is Mesa2, is Mesa3 -> print("[ 🍽️ ]")
                    is Cocina -> print("[ 👨🏻‍🍳 ]")
                    is Camarero1 -> print("[ 🧍🏻 ]")
                    is Camarero2 -> print("[ 🧍🏻‍♀️ ]")
                    is Camarero3 -> print("[ 🧍🏻‍♂️ ]")
                }
            }
            println()
        }
    }

    /**
     * Imprime un informe final con estadísticas del restaurante.
     */
    private fun informe() {
        println("🍴Total de clientes que visitaron el restaurante: ")
        println("😡Total de clientes descontentos: ")
        println("💲Recaudación total: ")
    }

    /**
     * Simula las acciones del restaurante.
     */
    fun simular(){
        posicionarPersonajes()
        imprimirRestaurante()

        var contador : Int = 0
        println()
        do {
            moverPersonajes()
            imprimirRestaurante()

            contador++
            println()

            Thread.sleep(500)
        }while (contador != 20)

        informe()
    }
}