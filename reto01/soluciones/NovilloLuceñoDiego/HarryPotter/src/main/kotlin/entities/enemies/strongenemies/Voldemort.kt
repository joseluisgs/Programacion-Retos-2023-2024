package org.example.entities.enemies.strongenemies

import org.example.controller.Position
import org.example.entities.enemies.Enemy

class Voldemort(position: Position) : StrongEnemy(position){
    override val attack: Int = 70
    override val name = "Voldemort"

    /**
     * @see Enemy.discoveredenemymsg
     */
    override fun discoveredenemymsg() {
        println("You´ve found Voldemort!")
    }
}