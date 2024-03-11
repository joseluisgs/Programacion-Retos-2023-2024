package org.example.storage

import org.example.dto.PersonajesDto
import java.io.FileWriter
import java.nio.file.Path
import java.nio.file.Paths
import java.text.SimpleDateFormat

/**
 * Hacemos un registro de la batalla para pasarla a un .txt
 *
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 *
 * @property fileWriter especificamos la ruta de almacenamiento y el archivo correspondiente
 * @property dateFormat el formato de la fecha y hora
 */

class BitacoraDeBatalla {
    private val fileWriter = FileWriter("data/bitacora.txt", true)
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    /**
     * Hacemos el registro de los datos necesarios que se escribirán en el archivo txt
     *
     * @param ganador especificamos el ganador de la batalla
     * @param participantesJugador lista de los personajes del Jugador
     * @param participantesMaquina lista de los personajes de la maquina
     * @param fecha fecha y hora de la batalla
     * @param sumaPuntosJugador suma de puntos de la lista Jugador
     * @param sumaPuntosMaquina suma de puntos de la lista Maquina
     */
    fun registrarBatalla(
        ganador: String,
        participantesMaquina: List<PersonajesDto>,
        participantesJugador: List<PersonajesDto>,
        fecha: String,
        sumaPuntosJugador: Int,
        sumaPuntosMaquina: Int,
        databaseDir: Path
    ) {
        println()
        fileWriter.use {
            it.write("\n")
            it.write("Batalla el $fecha: ")
            it.write("ganador: $ganador, ")
            it.write("-> Participantes de la Maquina($sumaPuntosMaquina): (")
            participantesMaquina.forEach { participante->
                it.write("${participante.nickName}(${participante.puntosCombate}), ")
            }
            it.write("), -> Participantes del Jugador($sumaPuntosJugador): (")
            participantesJugador.forEach { participante ->
                it.write("${participante.nickName} (${participante.puntosCombate}), ")
            }
            it.write(").")
            it.flush()
        }
        val rutaArchivo = Paths.get(databaseDir.toString(), "bitacora.txt").toString()
        println("Bitacora actualizada con éxito en: $rutaArchivo")
    }

    /**
     * Cerramos y guardamos el archivo txt
     */
    fun cerrar() {
        fileWriter.close()
    }
}
