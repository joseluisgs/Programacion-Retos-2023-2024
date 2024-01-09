package org.example.entities.allies

import org.example.controller.Position

const val RONSUCCESSRATE = 30

class Ron(position: Position) : Ally(position) {
    override val healingpower: Int = 20
    override val name = "Ron"

    /**
     * @see Ally.discoveredallymsg un override de su clase padre
     */
    override fun discoveredallymsg() {
        println("Harry found Ron in the dungeon!")
    }

    /**
     * Es una funcion que devuelve un Boolean que un 30% devuelve true
     * @return Boolean
     */
    fun failstoheal() : Boolean{
        val chance = (0..100).random()
        return RONSUCCESSRATE >= chance
    }
}