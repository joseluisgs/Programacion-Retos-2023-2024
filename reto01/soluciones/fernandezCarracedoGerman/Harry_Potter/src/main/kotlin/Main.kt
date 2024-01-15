

package org.example
//import models.Mazmorra
import org.example.models.Mazmorra
import org.lighthousegames.logging.logging

private val log = logging()

/**
 * Harry Potter: Aventura mágica en la Mazmorra Encantada de Azkaban
 * @since 1.0
 * @author Germán Fernández Carracedo
 */
fun main() {

    log.debug { "Iniciando Juego..." }

    val mazmorra = Mazmorra()

    mazmorra.jugar()

    log.debug { "Juego finalizado" }

}