package org.example.models.base

/**
 * Los pilotos que recorren la carrera
 * @property nombre el nombre del piloto
 * @property probFallo probabilidad de fallo de cada piloto
 * @property probAccidente probabilidad de sufir un accidente
 * @property fila la fila en la que se encuentra
 * @property columna la columna en la que se encuentra
 * @property finCarrera determina si ha acabado la carrera para el piloto
 * @property dnf determina si ese piloto ha sufrido un DNF
 * @property lluvia determina si el piloto se ha visto afectado por la lluvia
 * @property vueltasCompletadas número de vueltas completadas
 * @property pitStopTime tiempo que le tomará realizar un PitStop
 * @property tiempoInicial tiempo en el cual inicia la carrera
 * @property tiempoFinal tiempo en el que acaba la carrera
 * @property tiempoCarrera tiempo que tarda en completar la carrera
 */
abstract class Piloto(
    var nombre: String = "Piloto1",
    var probFallo: Int = 0,
    var probAccidente: Int = 0,
    var fila: Int = 0,
    var columna: Int = 0,
    var finCarrera: Boolean = false,
    var dnf: Boolean = false,
    var lluvia: Boolean = false,
    var vueltasCompletadas: Int = 0,
    var pitStopTime: Int = 0,
    var tiempoInicial: Long = 0,
    var tiempoFinal: Long = 0,
    var tiempoCarrera: Long = 0,
    ) {
    /**
     * Permite moverse al piloto a través de las columnas de su fila, si no ha acabado la carrera y no está DNF
     * @param pistaMatriz la pista en la que se mueve el piloto
     * @return la pista con la nueva posición del piloto
     */
   open fun moverse(pistaMatriz: Array<Array<Piloto?>>): Array<Array<Piloto?>> {
       val nuevaFila = this.fila
       var nuevaColumna = this.columna
       pistaMatriz[fila][columna] = null
       if(!finCarrera && !dnf){
           if(pitStopTime != 0){
               pitStopTime--
           }
           if(pitStopTime == 0){
               if(columna == 9){
                   vueltasCompletadas++
                   println("$nombre ha completado una vuelta y lleva $vueltasCompletadas vueltas")
                   comprobarVueltas()
                   nuevaColumna = 0
                   this.fila = nuevaFila
                   this.columna = nuevaColumna
                   pistaMatriz[fila][columna] = this
                   return pistaMatriz
               }else{
                   nuevaColumna++
                   this.fila = nuevaFila
                   this.columna = nuevaColumna
                   pistaMatriz[fila][columna] = this
                   return pistaMatriz
               }
           }
       }
       return pistaMatriz
   }

    /**
     * Coloca al piloto en su posición inicial de la pista
     * @param pista la pista donde se coloca al piloto
     * @return la pista con el piloto en su posición inicial
     */
    fun colocarPiloto(pista: Array<Array<Piloto?>>): Array<Array<Piloto?>> {
        pista[fila][columna] = this
        return pista
    }

    /**
     * Si se cumple la probabilidad de accidente, el piloto sufre un accidente y este pasa a estar DNF
     * @return true si ha sufrido el accidente y false si no ha sufrido el accidente
     */
    private fun accidente(): Boolean {
       if((0..100).random() < probAccidente){
           println("$nombre ha sufrido un accidente y se queda fuera de la carrera")
           return true
       }
       return false
   }

    /**
     * Comprueba que si el piloto no está DNF tenga la posibilidad de sufrir un accidente
     */
    fun comprobarAccidente(){
        if(!dnf){
            dnf = accidente()
        }
    }

    /**
     * Aviso en caso de lluvia si no está DNF
     */
    fun avisoIngeniero(){
        if(!dnf){
            println("$nombre BOX BOX we go for inters")
        }
        this.lluvia = true
    }

    /**
     * Al llegar a la columna 4 se entra a boxes durante un tiempo aleatorio de entre 1 y 3 segundos
     */
   private fun pitStop():Int{
        var tiempoPit = 0
        if(columna==4 && lluvia){
            tiempoPit = (1..3).random()
            println("$nombre BOX BOX")
            when(tiempoPit){
                1-> println("Buenísima parada para $nombre")
                2-> println("Buena parada para $nombre")
                3-> println("Mala parada para $nombre")
            }
            return tiempoPit
        }
        return tiempoPit
   }

    /**
     * Comprueba que si el tiempo de pitStop es 0 pueda entrar a boxes
     */
   fun pitStopPiloto(){
       if(pitStopTime == 0){
           pitStopTime = pitStop()
       }
   }

    /**
     * Comprueba el número de vueltas completadas y en caso de llevar 3 o más termina la carrera y guarda el tiempo final
     */
    fun comprobarVueltas(){
       if(vueltasCompletadas >= 3){
           finCarrera = true
           tiempoFinal = System.currentTimeMillis()
       }
   }

    /**
     * Comprueba que la carrera haya acabado para el piloto ya sea por número de vueltas o por DNF
     * @return true si ha acabado la carrera y false si no ha terminado
     */
   fun comprobarCarreraAcabada():Boolean{
       if(finCarrera || dnf){
           return true
       }
       return false
   }

    /**
     * Guarda el tiempo que empieza la carrera el piloto
     */
    fun cronoInicial(){
        tiempoInicial = System.currentTimeMillis()
    }

    /**
     * Si el piloto está DNF, asigna el tiempo de Carrera cuando la carrera ha terminado.
     * Si el piloto no está DNF, asigna el tiempo de Carrera el resultante entre el final menos el inicial
     */
    fun cronoFinal(){
        if(dnf){
            tiempoCarrera = 1000000000000
            println("El tiempo final de $nombre fue $tiempoCarrera segundos debido a DNF")
        }else{
            tiempoCarrera = (tiempoFinal - tiempoInicial) / 1000
            println("El tiempo final de $nombre fue $tiempoCarrera segundos")
        }

    }
}

