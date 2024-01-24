package org.example.models
import org.lighthousegames.logging.logging
private val log = logging()
/**
 * Interfaz que implementa el método Error Equipo
 * @see Piloto
 * @see Equipo
 */
interface ErrorEquipo {
    /**
     * Hace que el piloto retroceda una posición. Se resta del avance actual del piloto
     * @param piloto Piloto que sufre este evento
     */
    fun errorEquipo(piloto: Piloto){
        piloto.avance=-1
        piloto.estado = EstadoPiloto.ERROR_SAFETY_CAR
        piloto.icono = "🚨"
        log.debug { "🚨 ¡¡¡ERROR DEL EQUIPO !!! ¡¡El equipo ${piloto.equipo.nombre} comete un error con ${piloto.nombre} y provoca la salida del SC!! 🚨" }
    }
}