/**
 * La Ratatouille
 * @author Germán Fernández Carracedo
 * @since 1.0
 *
 */


package org.example

import org.example.controllers.RestauranteController

fun main() {
    println("Restaurante La Ratatouille")

    val restauranteController = RestauranteController.getInstance()

    restauranteController.simular()
    restauranteController.mostrarInformes()

}