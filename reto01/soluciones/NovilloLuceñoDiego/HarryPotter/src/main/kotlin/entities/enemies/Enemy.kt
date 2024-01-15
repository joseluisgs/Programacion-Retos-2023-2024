package org.example.entities.enemies

import org.example.controller.Position
import org.example.entities.Entity

const val SUCCESSRATE = 50

abstract class Enemy(position: Position) : Entity(position){
    abstract val attack : Int
    abstract val name : String

    /**
     * @return si el numero random es menor que la posibilidad de que puedan atacar
     */
    fun canattack() : Boolean{
        return (0..100).random() <= SUCCESSRATE
    }

    /**
     * Es un metodo que imprime que enemigo se ha encontrado
     */
    abstract fun discoveredenemymsg()
}