package org.example.entities.enemies.strongenemies

import org.example.controller.Position
import org.example.entities.enemies.Enemy

class Bellatrix(position: Position) : Enemy(position) {
    override val attack: Int = 30
    override val name = "Bellatrix"

    override fun discoveredenemymsg() {
        println("You´ve found Bellatrix Lestrange!")
    }
}