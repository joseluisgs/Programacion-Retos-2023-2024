package models

import java.time.LocalDate
import java.time.LocalTime

class Sainz: Ferrari(), Piloto {

    override val nombre: String="SAINZ"

    override var tiempoInicio: LocalTime = LocalTime.now()
    override var tiempoFinal: LocalTime = LocalTime.now()
    override var tiempoFinalComprobada: Boolean=false

    override val fila: Int=6
    override var columna: Int=0

    override var vuelta: Int=0
    override var isTerminada: Boolean=false

    override var isDnf: Boolean=false
    override val dnf: Int=1
    override var accidente: Boolean=false
    override fun accidenteF(): Boolean {
        if ((0..100).random()<dnf)return true
        return false
    }

    override var tiempoPitstop: Int=0
}