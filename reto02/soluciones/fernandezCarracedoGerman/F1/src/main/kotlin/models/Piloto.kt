package org.example.models
import org.lighthousegames.logging.logging
import java.time.LocalTime

private val log = logging()
private const val MAX_TIEMPO_PIT_STOP = 3
private const val MIN_TIEMPO_PIT_STOP = 1

/**
 * Clase que representa un Piloto
 * @property nombre Nombre del piloto
 * @property abrNombre Abreviatura del piloto para mostrar en pantalla
 * @property probAccidente Probabilidad de sufrir un accidente en carrera
 * @property posicion Posición actual del piloto, carril y columna o tramo de la pista
 * @property equipo Equipo del piloto
 * @property icono Icono que representará visualmente al piloto en la pista
 * @property vueltaCarrera Vuelta de carrera actual
 * @property estado Estado del piloto en la carrera
 * @property segundosStop Segundos que estará parado el piloto por diferentes circunstancias de carrera
 * @property tiempoInicioCarrera Tiempo en el que inicia la carrera el piloto
 * @property tiempoCarrera Contador que lleva el tiempo de carrera del piloto
 * @see EstadoPiloto
 * @see Posicion
 */
class Piloto(
    val nombre:String = "",
    val abrNombre:String = "",
    val probAccidente:Int = 0,
    var posicion:Posicion=Posicion(0,0),
    val equipo:Equipo
) {
    var icono:String = "🏎️"
    var vueltaCarrera:Int = 1
    var estado:EstadoPiloto = EstadoPiloto.CARRERA
    var segundosStop:Int = 0
    var avance:Int = 1
    val tiempoInicioCarrera = 0 // LocalTime.now()
    var tiempoCarrera = 0// : LocalTime = LocalTime.now()

    /**
     * Función que establece los parámetros del piloto afectados cuando tiene un accidente
     * @see tieneAccidente
     */
    fun provocarAccidente(){
        icono = "💥"
        estado = EstadoPiloto.DNF
        tiempoCarrera+= 99999999 // para ordenaciones posteriores de los pilotos por tiempos
        log.debug { "💥 ¡¡¡ACCIDENTE!!! ¡¡$nombre se ha salido de la pista !! 💥" }
    }

    /**
     * Comprueba si el piloto ha tenido un accidente, en base a su probabilidad de que esto suceda
     * @return Verdadero si tiene un accidente
     * @see provocarAccidente
     */
    fun tieneAccidente(): Boolean{
        return ((0..<100).random() < probAccidente)
    }

    /**
     * Función que establece los parámetros del piloto afectados cuando hace un pit stop.
     *
     * El piloto estará parado unos segundos aleatorios mientras cambia neumáticos
     * @see MIN_TIEMPO_PIT_STOP
     * @see MAX_TIEMPO_PIT_STOP
     */
    fun hacerPitStop() {
       estado = EstadoPiloto.PITSTOP
       segundosStop = (MIN_TIEMPO_PIT_STOP..MAX_TIEMPO_PIT_STOP).random()
       icono = "🔧"
       log.debug { "🔧 $nombre entra en boxes. Parada = $segundosStop 🔧" }
   }


}