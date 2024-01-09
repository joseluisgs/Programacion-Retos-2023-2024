package org.example.entities.allies

import org.example.controller.Position

class McGonagall(position: Position) : Ally(position){
    override val healingpower: Int = 70
    override val name = "McGonagall"

    /**
     * @see Ally.discoveredallymsg un override de su clase padre
     */
    override fun discoveredallymsg() {
        println("Harry found McGonagall in the dungeon!")
    }
}