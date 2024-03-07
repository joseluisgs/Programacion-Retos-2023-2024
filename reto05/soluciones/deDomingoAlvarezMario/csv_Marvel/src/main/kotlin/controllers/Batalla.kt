package controllers

import org.example.dto.PersonajesDto
import org.example.storage.BitacoraDeBatalla
import java.nio.file.Path
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 *
 * @property sumaPuntos variable temporal de la suma de puntos
 * @property sumaPuntosJugador puntos totales del jugador
 * @property sumaPuntosMaquina puntos totales de la Maquina
 * @property idsExcuidos listado de ids excluidos ya sea por no repetir o porque están muertos
 * @property ganador especificamos quien es el ganador que será registrado en un txt
 */
class Batalla {
    private var sumaPuntos = 0
    private var sumaPuntosJugador = 0
    private var sumaPuntosMaquina = 0
    private var idsExcuidos : ArrayList<Int> = ArrayList()
    private var ganador: String = ""

    /**
     * Se crean 2 listas de personajes aleatorios teniendo en cuenta que no pueden estar muertos ni repetirse
     *
     * @param personajes lista de personajes donde cogeremos aleatorios para la lista de cada jugador
     */
    fun batalla(personajes: List<PersonajesDto>, databaseDir: Path){

        val fechaActual = LocalDateTime.now()
        val formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val fechaFormateada = fechaActual.format(formatoFecha)

        println("La Batalla ha comenzado")

        //añadimos el ID de los personajes que estén fallecidos a numElegidos
        excluirFallecidos(personajes)

        val maquina = mutableListOf(
            personajeRdmNoRepe(personajes),
            personajeRdmNoRepe(personajes),
            personajeRdmNoRepe(personajes),
            personajeRdmNoRepe(personajes),
            personajeRdmNoRepe(personajes),
        )
        val jugador = mutableListOf(
            personajeRdmNoRepe(personajes),
            personajeRdmNoRepe(personajes),
            personajeRdmNoRepe(personajes),
            personajeRdmNoRepe(personajes),
            personajeRdmNoRepe(personajes),
        )

        imprimirListas("l Jugador", jugador)
        puntosCombate(jugador)
        sumaPuntosJugador = sumaPuntos

        imprimirListas(" la Maquina", maquina)
        puntosCombate(maquina)
        sumaPuntosMaquina = sumaPuntos

        luchaIndividual(jugador, maquina)
        println()
        luchaGeneral()

        //generamos un resumen del los resultados finales y los guardamos en un txt
        val bitacora = BitacoraDeBatalla()
        bitacora.registrarBatalla(ganador,maquina,jugador,fechaFormateada,sumaPuntosJugador,sumaPuntosMaquina,databaseDir)
        bitacora.cerrar()

    }

    /**
     * Añadimos los ID de los personajes fallecidos para no añadirlo a ninguna lista
     *
     * @param personajes lista de personajes
     */
    private fun excluirFallecidos(personajes: List<PersonajesDto>) {
        personajes.forEach {
            if (!it.vivo) {
                idsExcuidos.add(it.id)
            }
        }
    }

    /**
     * Cogemos un ID aleatorio siempre que no esté en la lista de excluidos
     *
     * @param personajes lista de Personajes
     */
    private fun personajeRdmNoRepe(personajes: List<PersonajesDto>): PersonajesDto {
        val random = (0..29).random()
        return if (!idsExcuidos.contains(random)) {
            idsExcuidos.add(random)
            personajes[random]
        } else {
            personajeRdmNoRepe(personajes)
        }
    }

    /**
     * Imprime las lista de los personajes según la lista
     *
     * @param nombre de la lista que imprimimos
     * @param lista lista que vamos a imprimir
     */
    private fun imprimirListas(nombre: String, lista: MutableList<PersonajesDto>) {

        println("    ==> Los personajes de la lista de$nombre son:")
        lista.forEach {println("${it.nombre} alias ${it.nickName} con ${it.puntosCombate} Puntos de Combate")}

    }

    /**
     * Calculamos los puntos de combate totales según la lista
     *
     * @param lista de la cual cogemos los puntos que vamos a sumar para la puntuación total
     */
    private fun puntosCombate(lista: List<PersonajesDto>){

        sumaPuntos = lista.sumBy { it.puntosCombate }
        println("-> La suma de los puntos de combate es: $sumaPuntos")
        println()

    }

    /**
     * Calculamos los resultados de la lucha entre personajes de distintas listas, pero mismo índice e imprimimos
     * dichos resultados
     *
     * @param jugador lista de personajes del Jugador
     * @param maquina lista de jugadores de la Maquina
     */
    private fun luchaIndividual(jugador: MutableList<PersonajesDto>, maquina: MutableList<PersonajesDto>) {
        println("═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═")

        for (i in jugador.indices) {
            when {

                jugador[i].puntosCombate < maquina[i].puntosCombate ->
                    println("${jugador[i].nickName} (${jugador[i].puntosCombate}PC) ha Perdido contra " +
                            "${maquina[i].nickName} (${maquina[i].puntosCombate}PC)🔴")

                jugador[i].puntosCombate > maquina[i].puntosCombate ->
                    println("${jugador[i].nickName} (${jugador[i].puntosCombate}PC) ha Ganado contra " +
                            "${maquina[i].nickName} (${maquina[i].puntosCombate}PC)🟢")

                else ->
                    println("${jugador[i].nickName} (${jugador[i].puntosCombate}PC) ha Empatado contra " +
                            "${maquina[i].nickName} (${maquina[i].puntosCombate}PC)🟡")
            }
        }

        println("═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═ ═")
    }

    /**
     * Comprobamos que equipo tiene una puntuación mayor lo que determina si gana o queda en empate
     */
    private fun luchaGeneral() {

        println("  ==> Es Hora de La Batalla entre todos los integrantes: ")
        println()

        when {

            sumaPuntosJugador >= sumaPuntosMaquina -> {
                println("🟢¡¡¡HAS GANADO!!!🟢")
                ganador = "Jugador 🟢"
            }

            sumaPuntosJugador == sumaPuntosMaquina -> {
                println("🟡¡¡¡EMPATE!!!🟡")
                ganador = "null"
            }

            sumaPuntosJugador <= sumaPuntosMaquina -> {
                println("🔴¡¡¡HAS PERDIDO!!!🔴")
                ganador = "Maquina 🔴"
            }

        }

    }
}
