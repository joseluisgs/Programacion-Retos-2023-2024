package org.example.entities.enemies.strongenemies

import org.example.controller.Position
import org.example.entities.enemies.Enemy

class Bellatrix(position: Position) : StrongEnemy(position) {
    override val attack: Int = 30
    override val name = "Bellatrix"

    /**
     * @see Enemy.discoveredenemymsg
     */
    override fun discoveredenemymsg() {
        println("You´ve found Bellatrix Lestrange!")
    }
}