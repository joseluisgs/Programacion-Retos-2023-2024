package org.example.entities.enemies

import org.example.controller.Position
import org.example.objects.Entity

const val SUCCESSRATE = 50

abstract class Enemy(position: Position) : Entity(position){
    abstract val attack : Int
    abstract val name : String

    fun canattack() : Boolean{
        return (0..100).random() <= SUCCESSRATE
    }

    abstract fun discoveredenemymsg()
}