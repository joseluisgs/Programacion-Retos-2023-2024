package org.example.models

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 *
 * Clase que representa una mesa en un restaurante.
 *
 * @property i Número de la mesa.
 */
class Mesa(val i: Int) {

    // Propiedad que indica si la mesa está ocupada o no.
    var ocupada: Boolean = false

    // Menú asociado a la mesa.
    private val menu = Menu()

    // Lista que almacenará el menú específico de la mesa.
    var menuMesa: List<String> = listOf()

    /**
     * Método para simular la ocupación de la mesa de manera aleatoria.
     */
    fun ocupada() {
        val ocupar = (0..3).random()
        when (ocupar) {
            0, 2 -> {
                ocupada = true
                println("Se ha ocupado la mesa nº${i + 1}")
            }
            1 -> ocupada = false
        }
    }

    /**
     * Método para generar un menú aleatorio para la mesa, seleccionando un plato
     * al azar de cada categoría (primer plato, segundo plato, postre).
     */
    fun menuAleatorio() {
        val menuAleatorio: List<String> = listOf(
            menu.primerPlato[(0..4).random()],
            menu.segundoPlato[(0..4).random()],
            menu.postre[(0..4).random()]
        )
        menuMesa = menuAleatorio
    }
}
