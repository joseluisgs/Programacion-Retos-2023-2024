package org.example.models

import org.lighthousegames.logging.logging

/**
 * Clase que representa a una rata en el restaurante.
 */
class Rata {

    /**
     * Método que simula la aparición de la rata en el restaurante.
     */
    fun aparecer() {
        if ((0..100).random() <= 20) {
            logging().info { "¡Ha aparecido una rata!" }

            if ((0..100).random() <= 5) {
                logging().info { "¡La rata se ha subido a la mesa!\n" +
                        "¡Los comensales se han enfadado y se han ido sin pagar!" }
            } else if ((0..100).random() <= 10) {
                logging().info { "¡La rata ha aparecido en el pasillo y ha hecho que el camarero se caiga!" }
            }
        }
    }
}