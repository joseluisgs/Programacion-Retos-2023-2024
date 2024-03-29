package org.example.entities.allies

import org.example.controller.Position

class Herminone(position: Position) : Ally(position){
    override val healingpower: Int = 30
    override val name = "Herminone"

    /**
     * @see Ally.discoveredallymsg un override de su clase padre
     */
    override fun discoveredallymsg() {
        println("Harry found Hermione in the dungeon!")
    }
}