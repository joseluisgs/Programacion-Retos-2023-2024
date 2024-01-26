package org.example.models

/**
 * Pilot es una interface que tiene dentro lo que tiene que tener un piloto generico que
 * luego implementan las marcas
 */
interface Pilot{
    val failurerate : Int // Un int que necesita para saber si en algun momento fallarán
    val state : State // Una variable que include: si son dnf y la cantidad de tiempo que estan parados
    var laps : Int // La cantidad de vueltas que han dado
    var position: Int // El indice de la matriz en la que están
    var name : String // El nombre del piloto
    var finishtime : Int // El tiempo que ha tardado cada piloto en terminar la carrera
    var done : Boolean // Si han terminado

    /**
     * Dentro de esta funcion estan las funciones que corresponden a cualquier clase que implemente
     * esta interface para hacer que se mueva
     */
    fun move()

    /**
     * Mira si la posicion en la que está el piloto es la mitad de la matriz, y si lo es, añade
     * "downtime" para que se pare
     */
    fun checkforpitstop(){
        if (position == 4){
            this.state.downtimeleft += (1..3).random()
            println("$name needs to take a trip to the pitstop, " +
                    "they´ll be out for ${state.downtimeleft} seconds")
        }
    }

    /**
     * Cambia la posicion dependiendo de la cantidad de posiciones que se pueden mover, que es 1
     * por defecto ya que la mayoria solo se mueve 1 posicion.
     * Si la posicion esta fuera de la matriz, vuelve al principio y le añade una vuelta
     * En el caso de que tenga que retroceder y si esta en la primera posicion volvera a la posicion
     * que le corresponda en la vuelta anterior
     */
    fun advance(amount : Int = 1){
        position += (amount)
        if (position >= 10){
            position -= 10
            laps++
        }
        if (position <= -1){
            if (laps > 0){
                position = 10 -amount
                laps--
            }else position = 0

        }
        if (laps == 3) done = true
    }

    /**
     * Mira si el piloto ha tenido un accidente dependiendo de su "failurerate" cada vez que
     * se mueve
     */
    fun checkforaccidents(){
        if ((0..100).random() <= failurerate){
            println("$name had an accident, and they´ve been disqualified.")
            state.dnf = true
        }
    }
}
