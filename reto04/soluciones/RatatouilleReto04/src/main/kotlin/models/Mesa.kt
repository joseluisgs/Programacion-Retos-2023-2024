package org.example.models
/**
 * La clase Mesa representa una mesa en el restaurante, donde los clientes pueden sentarse y hacer pedidos.
 * @property numero El número de identificación de la mesa.
 * @property ocupada Indica si la mesa está ocupada por clientes o no.
 * @property platosPedidos La lista de platos pedidos por los clientes en esta mesa.
 */
class Mesa(val numero: Int) {
    /**
     * Indica si la mesa está ocupada por clientes o no.
     * Por defecto, una mesa se considera desocupada.
     */
    var ocupada: Boolean = false
        private set

    private val comida = Comida() // Instancia del menú del restaurante
    var platosPedidos: List<String> = listOf() // Lista de platos pedidos en esta mesa

    /**
     * Ocupa la mesa con nuevos clientes y toma su pedido.
     * Imprime un mensaje indicando que se han sentado nuevos clientes en la mesa.
     */
    fun ocupar() {
        ocupada = true
        println("Se han sentado nuevos clientes en la mesa ${numero + 1}.")
        realizarPedido()
    }

    /**
     * Desocupa la mesa una vez los clientes han terminado de comer.
     * Imprime un mensaje indicando que la mesa ha quedado vacía y reinicia la lista de platos pedidos.
     */
    fun desocupar() {
        ocupada = false
        println("La mesa número ${numero + 1} ha quedado vacía.")
        platosPedidos = emptyList()
    }

    /**
     * Realiza el pedido de los clientes en la mesa.
     * Genera una lista de platos pedidos utilizando el menú del restaurante.
     */
    private fun realizarPedido() {
        platosPedidos = comida.generarPedido()
    }
}