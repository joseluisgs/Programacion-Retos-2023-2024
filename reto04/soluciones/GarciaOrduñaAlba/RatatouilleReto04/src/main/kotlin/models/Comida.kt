package org.example.models

import kotlin.random.Random
/**
 * La clase Comida representa la gestión de los menús disponibles en el restaurante.
 * @property menus Lista de menús disponibles, cada uno representado por un objeto de tipo MenuPlatos.
 */
class Comida {
    val menus = listOf(
        MenuPlatos(
            listOf("Paella", "Gazpacho"),
            listOf("Tortilla Española", "Pollo al Ajillo"),
            listOf("Flan", "Churros"),
            120.0
        ),
        MenuPlatos(
            listOf("Spaghetti Carbonara", "Bruschetta"),
            listOf("Pizza Margarita", "Lasagna"),
            listOf("Tiramisú", "Panna Cotta"),
            100.0
        ),
        MenuPlatos(
            listOf("Sushi Variado", "Miso Soup"),
            listOf("Yakitori", "Ramen"),
            listOf("Matcha Ice Cream", "Dorayaki"),
            90.0
        ),
        MenuPlatos(
            listOf("Hamburguesa Clásica", "Ensalada César"),
            listOf("Fish and Chips", "Shepherd's Pie"),
            listOf("Apple Crumble", "Trifle"),
            80.0
        ),
        MenuPlatos(
            listOf("Pad Thai", "Tom Yum Goong"),
            listOf("Green Curry", "Massaman Curry"),
            listOf("Mango Sticky Rice", "Coconut Ice Cream"),
            70.0
        )
    )

    /**
     * Genera un pedido aleatorio seleccionando un menú y combinando sus platos.
     * @return Lista de platos del pedido generado.
     */
    fun generarPedido(): List<String> {
        val menuIndex = elegirMenu()
        val menuElegido = menus[menuIndex]
        val pedido = mutableListOf<String>()
        pedido.addAll(menuElegido.primerosPlatos)
        pedido.addAll(menuElegido.segundosPlatos)
        pedido.addAll(menuElegido.postres)
        return pedido
    }

    /**
     * Elige aleatoriamente un menú basado en las probabilidades proporcionadas por los precios de los menús.
     * @return El índice del menú seleccionado.
     */
    fun elegirMenu(): Int {
        val probabilidades = mutableListOf<Double>()
        var sumaPrecios = 0.0
        for (menu in menus) {
            sumaPrecios += menu.precio
        }
        for (menu in menus) {
            val probabilidad = menu.precio / sumaPrecios
            probabilidades.add(probabilidad)
        }
        var random = Random.nextDouble()
        var acumulador = 0.0
        for (i in probabilidades.indices) {
            acumulador += probabilidades[i]
            if (random <= acumulador) {
                return i
            }
        }
        return probabilidades.size - 1
    }
}

/**
 * La clase MenuPlatos representa un menú de platos que puede ser pedido en el restaurante.
 * @property primerosPlatos Lista de primeros platos disponibles en el menú.
 * @property segundosPlatos Lista de segundos platos disponibles en el menú.
 * @property postres Lista de postres disponibles en el menú.
 * @property precio Precio total del menú.
 */
data class MenuPlatos(
    val primerosPlatos: List<String>,
    val segundosPlatos: List<String>,
    val postres: List<String>,
    val precio: Double
)