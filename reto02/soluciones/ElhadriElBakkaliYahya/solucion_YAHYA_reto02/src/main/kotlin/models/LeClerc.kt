package models


import java.time.LocalTime

class LeClerc: Ferrari(), Piloto {

    override val nombre: String="LECLREC"
    override val fila: Int=7
    override var columna: Int=0

    override var tiempoInicio: LocalTime = LocalTime.now()
    override var tiempoFinal: LocalTime = LocalTime.now()
    override var tiempoFinalComprobada: Boolean=false

    override var vuelta: Int=0
    override var isTerminada: Boolean=false

    override val dnf: Int=3
    override var isDnf: Boolean=false
    override var accidente: Boolean=false
    override fun accidenteF(): Boolean {
        if ((0..100).random()<dnf)return true
        return false
    }

    override var tiempoPitstop: Int=0


}