package org.example.entities.allies

import org.example.controller.Position

const val RONSUCCESSRATE = 30

class Ron(position: Position) : Ally(position) {
    override val healingpower: Int = 20
    override val name = "Ron"

    override fun discoveredallymsg() {
        println("Harry found Ron in the dungeon!")
    }

    fun failstoheal() : Boolean{
        val chance = (0..100).random()
        return RONSUCCESSRATE >= chance
    }
}