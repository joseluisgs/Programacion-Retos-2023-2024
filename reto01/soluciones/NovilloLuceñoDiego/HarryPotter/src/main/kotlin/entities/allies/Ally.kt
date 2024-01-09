package org.example.entities.allies

import org.example.controller.Position
import org.example.entities.Entity

abstract class Ally(position: Position) : Entity(position){
    abstract val healingpower : Int
    abstract val name : String

    /**
     * Imprime un mensaje diciendo el nombre del amigo
     */
    abstract fun discoveredallymsg()
}