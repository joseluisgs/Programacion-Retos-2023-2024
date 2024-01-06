package org.example.entities.enemies

import org.example.controller.Position

class Dementor(position: Position) : Enemy(position) {
    override val attack: Int = 10
    override val name = "Dementor"

    override fun discoveredenemymsg() {
        println("You found a dementor!")
    }
}