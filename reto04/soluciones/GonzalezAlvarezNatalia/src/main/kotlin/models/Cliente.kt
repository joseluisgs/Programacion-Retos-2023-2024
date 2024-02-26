package org.example.models

import org.lighthousegames.logging.logging

/**
 * Clase que representa a un cliente en el restaurante.
 *
 * @property id Identificador único del cliente.
 * @property tiempoDeEspera Tiempo de espera del cliente en el restaurante.
 * @property total Total de clientes en el restaurante.
 */
class Cliente(
    val id: Int,
    val tiempoDeEspera: Int,
    val total: Int
) {

    /**
     * Variable que llama a la clase Menu.
     */
    private val menu: Menu = Menu()

    /**
     * Método que simula que el cliente pide un menú al azar.
     */
    fun pedirMenu() {
        logging().info { "El cliente ha pedido el ${menu.menus.random()}" }
    }
}