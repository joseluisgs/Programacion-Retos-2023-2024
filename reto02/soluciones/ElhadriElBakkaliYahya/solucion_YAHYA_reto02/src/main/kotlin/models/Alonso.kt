package models


import java.time.LocalTime

class Alonso: AstonMartin(), Piloto {
    override val nombre: String="ALONSO"

    override var tiempoInicio: LocalTime = LocalTime.now()
    override var tiempoFinal: LocalTime = LocalTime.now()
    override var tiempoFinalComprobada: Boolean=false

    override val fila: Int=2
    override var columna: Int=0

    override val dnf: Int=1
    override fun accidenteF(): Boolean {
        if ((0..100).random()<dnf)return true
        return false
    }

    override var accidente: Boolean=false
    override var vuelta: Int=0

    override var tiempoPitstop: Int=0
    override var isDnf: Boolean=false

    override var isTerminada: Boolean=false

}