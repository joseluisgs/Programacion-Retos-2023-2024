package services

import models.Personaje
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class PersonajesServices {

    /**
     * Eligir aleatoriamente una lista de héroes vivos sin repetición.
     * @param tamaño El tamaño de la lista a seleccionar.
     * @param listaHeroes La lista de héroes disponibles.
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    fun elegirPersonajeAleatorio(tamaño: Int, listaHeroes: List<Personaje>): List<Personaje> {
        val personajeElegido = mutableListOf<Personaje>()
        val random = Random

        if (listaHeroes.isNotEmpty()) {
            while (personajeElegido.size < tamaño) {
                val index = random.nextInt(listaHeroes.size)
                if (!personajeElegido.any { it.id == listaHeroes[index].id }) {
                    personajeElegido.add(listaHeroes[index])
                }
            }
        }
        return personajeElegido
    }

    /**
     * Eligir dos listas aleatorias de héroes, una para el jugador y otra para la máquina.
     * @param listaHeroes La lista de héroes disponibles.
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    fun ListasAleatorias(listaHeroes : List<Personaje>): Pair<List<Personaje>, List<Personaje>> {
        val heroesVivos = listaHeroes.filter { it.personajeVivo }
        val miLista = elegirPersonajeAleatorio(5, heroesVivos)
        val listaMaquina = heroesVivos
            .filterNot { miLista.any { personaje -> personaje.id == it.id } }
            .take(5)

        return miLista to listaMaquina
    }

    /**
     * Determina el ganador de la batalla comparando los puntos individuales y globales de cada lista.
     * @param listaJugador Lista de héroes del jugador.
     * @param listaMaquina Lista de héroes de la máquina.
     * @author Raul Fernández Delgado
     * @since 1.0
     */
    fun determinarGanador(listaJugador: List<Personaje>, listaMaquina: List<Personaje>): String {
        var misPuntos = 0
        var puntosMaquina = 0

        for (i in listaJugador.indices) {
            val personajeJugador = listaJugador[i]
            val personajeMaquina = listaMaquina[i]

            if (personajeJugador.pc > personajeMaquina.pc) {
                misPuntos++
            } else if (personajeMaquina.pc > personajeJugador.pc) {
                puntosMaquina++
            }
        }

        val ganadorIndividual = when {
            misPuntos > puntosMaquina -> "Jugador"
            puntosMaquina > misPuntos -> "Máquina"
            else -> "Empate"
        }

        return "🏆Ganador de los combates individuales: $ganadorIndividual\n"
    }
}