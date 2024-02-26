package org.example.factories
import org.lighthousegames.logging.logging

import org.example.models.Menu

/**
 * Clase factory para generar menus
 */
class MenuFactory {

    companion object {

        /**
         * Crea un objeto Menu con primero, segundo y postre obtenidos aleatoriamente de listas de platos
         */
        fun crearRandom(): Menu {
            val primeros = listOf("Ensalada mixta", "Lentejas", "Arroz a la cubana","Espagueti a la Boloñesa","Sopa de Cocido","Fabada")
            val segundos = listOf("Entrecot","Escalope de pollo","Lomo de bacalao","Huevos fritos","Cocido completo","Merluza rebozada")
            val postres = listOf("Flan casero","Natillas","Helado de vainilla","Melón","Sandía","Piña")

            return Menu(primeros.random(), segundos.random(), postres.random(), (1..5).random())
        }
    }
}