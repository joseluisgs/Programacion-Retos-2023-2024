package controllers

import org.example.dto.PersonajesDto
import java.io.FileWriter
import java.text.SimpleDateFormat

class BitacoraDeBatalla {
    private val fileWriter = FileWriter("bitacora_de_batalla.txt", true)
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    fun registrarBatalla(
        ganador: String,
        participantesMaquina: List<PersonajesDto>,
        participantesJugador: List<PersonajesDto>,
        fecha: String,
        sumaPuntosJugador: Int,
        sumaPuntosMaquina: Int
    ) {
        fileWriter.use {
            it.write("Batalla el $fecha: ")
            it.write("ganador: $ganador, ")
            it.write("-> Participantes de la Maquina($sumaPuntosMaquina): (")
            participantesMaquina.forEach { participante ->
                it.write("${participante.nickName}(${participante.puntosCombate}), ")
            }
            it.write("), -> Participantes del Jugador($sumaPuntosJugador): (")
            participantesJugador.forEach { participante ->
                it.write("${participante.nickName} (${participante.puntosCombate}), ")
            }
            it.write(").")
            it.write("\n")
            it.flush()
        }
    }

    fun cerrar() {
        fileWriter.close()
    }
}
