package models

import java.time.LocalDate
import java.time.LocalDateTime

abstract class Piloto (
    val nombre: String = "",
    val accidenteprob: Int=0,
    nombreequipo:String=""
    ):Equipo(nombreequipo){

    open val coche: String="🏎️"

    protected val tiemposalida = LocalDateTime.now()

}