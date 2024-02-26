package org.example

import org.example.models.Restaurant


fun main() {
    val restaurante = Restaurant()

    for (i in 0..5){
        restaurante.simular()
    }
}