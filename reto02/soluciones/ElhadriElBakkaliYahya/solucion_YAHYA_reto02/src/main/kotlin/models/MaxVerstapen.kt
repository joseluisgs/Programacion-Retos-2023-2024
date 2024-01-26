package models

import java.time.LocalDate
import java.time.LocalTime

class MaxVerstapen() : RedBull(), Piloto {
    override val nombre: String="MAX"

    override var tiempoInicio: LocalTime = LocalTime.now()
    override var tiempoFinal: LocalTime = LocalTime.now()
    override var tiempoFinalComprobada: Boolean=false

    override val fila: Int=0
    override var columna: Int=0
    override fun moverPiloto() {
        if (isRapida){
            if (columna>=7){
                columna=0
                vuelta++
                isRapida=false
            }
            else columna+=2
            if (vuelta>=3 || isTerminada)
            else println("MAX ESTA EN VUELTA RAPIDA")
        }
        else columna++
    }

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