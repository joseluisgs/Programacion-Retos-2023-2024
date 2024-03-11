package org.example.services

import org.example.models.Heroe
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Clase con las funciones que simulan el programa.
 * @author Natalia González
 * @since 1.0
 */
class HeroeService {

    /**
     * Elige aleatoriamente una lista de héroes vivos sin repetición.
     * @param tamaño El tamaño de la lista a seleccionar.
     * @param listaHeroes La lista de héroes disponibles.
     * @return Lista de héroes seleccionados aleatoriamente.
     * @author Natalia González
     * @since 1.0
     */
    fun elegirPersonajeAleatorio(tamaño: Int, listaHeroes: List<Heroe>): List<Heroe> {
        val heroeElegido = mutableListOf<Heroe>()
        val random = Random()

        if (listaHeroes.isNotEmpty()) {
            while (heroeElegido.size < tamaño) {
                val index = random.nextInt(listaHeroes.size)
                if (!heroeElegido.any { it.id == listaHeroes[index].id }) {
                    heroeElegido.add(listaHeroes[index])
                }
            }
        }

        return heroeElegido
    }

    /**
     * Elige dos listas aleatorias de héroes, una para el jugador y otra para la máquina.
     * @param listaHeroes La lista de héroes disponibles.
     * @return Par de listas de héroes seleccionados.
     * @author Natalia González
     * @since 1.0
     */
    fun elegirListasAleatorias(listaHeroes : List<Heroe>): Pair<List<Heroe>, List<Heroe>> {
        val heroesVivos = listaHeroes.filter { it.vivo }
        val miLista = elegirPersonajeAleatorio(5, heroesVivos)
        val listaMaquina = heroesVivos
            .filterNot { miLista.any { heroe -> heroe.id == it.id } }
            .take(5)

        return miLista to listaMaquina
    }

    /**
     * Determina el ganador de la batalla comparando los puntos individuales y globales de cada lista.
     * @param listaJugador Lista de héroes del jugador.
     * @param listaMaquina Lista de héroes de la máquina.
     * @return Cadena que indica el ganador de los enfrentamientos individuales y el ganador global.
     * @author Natalia González
     * @since 1.0
     */
    fun determinarGanador(listaJugador: List<Heroe>, listaMaquina: List<Heroe>): String {
        var misPuntos = 0
        var puntosMaquina = 0

        for (i in listaJugador.indices) {
            val heroeJugador = listaJugador[i]
            val heroeMaquina = listaMaquina[i]

            if (heroeJugador.pc > heroeMaquina.pc) {
                misPuntos++
            } else if (heroeMaquina.pc > heroeJugador.pc) {
                puntosMaquina++
            }
        }

        val ganadorIndividual = when {
            misPuntos > puntosMaquina -> "Jugador"
            puntosMaquina > misPuntos -> "Máquina"
            else -> "Empate"
        }

        val puntosTotalesJugador = listaJugador.sumBy { it.pc }
        val puntosTotalesMaquina = listaMaquina.sumBy { it.pc }

        val ganadorGlobal = when {
            puntosTotalesJugador > puntosTotalesMaquina -> "Jugador"
            puntosTotalesMaquina > puntosTotalesJugador -> "Máquina"
            else -> "Empate"
        }

        return "🏆Ganador de los enfrentamientos individuales: $ganadorIndividual\n" +
                "🏆Ganador global de la Batalla de Héroes Marvel: $ganadorGlobal"
    }

    /**
     * Guarda el resultado de la batalla en un archivo txt llamado bitacora.
     * @param resultado Cadena que contiene el resultado de la batalla.
     * @author Natalia González
     * @since 1.0
     */
    fun guardarResultadoBatalla(resultado: String) {
        val fechaActual = obtenerFechaActual()
        val contenido = "$fechaActual - Resultado de la batalla: $resultado\n"

        val archivoBitacora = File("data/bitacora.txt")

        archivoBitacora.appendText(contenido)
    }

    /**
     * Obtiene la fecha y hora actual formateada como una cadena.
     * @return La fecha y hora actual como una cadena.
     * @author Natalia González
     * @since 1.0
     */
    fun obtenerFechaActual(): String {
        val formatoFecha = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val fechaActual = Calendar.getInstance().time
        return formatoFecha.format(fechaActual)
    }

    /**
     * variable que indica una lista de heroes.
     */
    private val listaDeHeroes = mutableListOf<Heroe>()

    /**
     * Obtiene todos los heroes en una lista.
     * @return La lista con los heroes.
     * @author Natalia González
     * @since 1.0
     */
    fun obtenerHeroes(): List<Heroe> {
        return listaDeHeroes.toList()
    }
}
