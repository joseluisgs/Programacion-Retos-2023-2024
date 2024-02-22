package models

import java.time.LocalTime

interface Piloto {
    val nombre:String

    var tiempoInicio: LocalTime
    var tiempoFinal: LocalTime
    var tiempoFinalComprobada:Boolean


    val fila:Int
    var columna:Int
    fun mover(){
        columna++

    }
    /**
     * esta funcion s erealiza siempre que el piloto no haya terminado
     * si el piloto tiene tiempo en pitstop no se mueve y se resta un segundo al contador de pitstop
     * si el pitstop esta en 0 se mueve, si la columna es 9 se pone en 0 y se suma una vuelta
     * @param piloto
     */
    fun moverPiloto(){
        if(!this.acabada()){
            if (this.tiempoPitstop!=0){
                println("${this.nombre} ESTA EN PITSTOP")
                this.tiempoPitstop--
            }
            if (this.tiempoPitstop==0) {
                if (this.columna == 9) {
                    this.vuelta++
                    this.columna = 0
                    when(this){
                        is RedBull->this.isRapida=false
                    }
                    if (this.vuelta>=3)this.isTerminada=true
                } else this.mover()
            }
        }
    }
    var vuelta:Int
    var isTerminada:Boolean

    var isDnf:Boolean
    val dnf:Int
    var accidente:Boolean
    fun accidenteF():Boolean

    var tiempoPitstop:Int
    fun pitStop():Int{
        var tiempo=0
        if (columna==4){
            tiempo=(1..3).random()
            return tiempo
        }
        return tiempo
    }

    /**
     * esta funcion comprueba que el piloto hay terminado y nos coloca su tiempo de final para luego poder ordenarlos
     * @param piloto
     */
    fun comprobarPiloto() {
        if (isTerminada && !tiempoFinalComprobada){
            tiempoFinal= LocalTime.now()
            tiempoFinalComprobada=true
            //piloto.tiempoFinal=piloto.tiempoInicio-piloto.tiempoFinal
            //piloto.timpoFinal-=piloto.tiempoInicio
        }
    }

    /**
     * esta funcion nos coloca al piloto en la matriz segun su columna y su fila
     * @param piloto
     */
   fun posicinamientoInicial(circuito:Array<Array<Piloto?>>){
        circuito[fila][columna]=this
        tiempoInicio= LocalTime.now()
    }

    /**
     * un jugador termina si esta descalidicado o si a dado tres vueltas
     * @param piloto
     * @return true si esta acabada
     * @return false si no a terminado
     */
    fun acabada(): Boolean {
        if (isTerminada || isDnf)return true
        return false
    }

    /**
     * mientras que no este dnf, hacemos la probabilidad de que lo sea, y si lo es activamos el true en el piloto y imprimimos por pantalla
     * @param piloto
     */
    fun accidente() {
        if (!isDnf){
            isDnf=accidenteF()
            if (isDnf) println("${nombre} HA SUFRIDO UN ACCIDENTE EN SU VUELTA ${vuelta}")
        }
    }

    /**
     * siempre que el tiempo de pitstop este en 0 hacemos el pitstop que es un random que duevlve los segundos que tenemso que parar
     * @param piloto
     */
    fun pitStopPiloto(){
        if (tiempoPitstop==0) {
            tiempoPitstop = pitStop()
        }
    }

    /**
     * anulamos las posicioes de los jugadores antes de moverlos
     * @param piloto
     */
    fun anularPiloto(circuito:Array<Array<Piloto?>>){
        if (this.columna>9)this.columna=0
        circuito[this.fila][this.columna]=null
    }

    fun posicionamiento(circuito:Array<Array<Piloto?>>){
        if (this.isDnf){
            println("${this.nombre} DNF")
        }else{
            if (this.vuelta>=3){
                println("${nombre} HA TERMINADO")
                isTerminada=true
            }
            else {
                if (this.columna>9)this.columna=0
                circuito[this.fila][this.columna]=this
            }
        }
    }
}