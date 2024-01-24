package org.example
import org.lighthousegames.logging.logging
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyles.*
import com.github.ajalt.mordant.terminal.Terminal
import org.example.controllers.Circuito


private val log = logging()
private val term=Terminal()

/**
 * F1: Gran Premio de DAW
 * Metodo principal del juego simulación F1. Crea e inicializa el circuito, y llama a la función que controla la carrera
 * @since 1.0
 * @author Germán Fernández Carracedo
 */
fun main() {

    term.println(bold(red("🏁🎌🚩 F1 GRAN PREMIO DE DAW 🚩🎌🏁")) + green(" - Jan-21-2024"))

    val circuito = Circuito()
    circuito.carrera()

}