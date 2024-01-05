package org.example.entities.allies

import org.example.controller.Board
import org.example.controller.Position
import org.example.objects.Entity

abstract class Ally(position: Position) : Entity(position){
    abstract val healingpower : Int
    abstract val name : String

    abstract fun discoveredallymsg()

    fun dissapear (dungeon : Board){
        dungeon.trueBoard[position.X][position.Y] = null
    }
}