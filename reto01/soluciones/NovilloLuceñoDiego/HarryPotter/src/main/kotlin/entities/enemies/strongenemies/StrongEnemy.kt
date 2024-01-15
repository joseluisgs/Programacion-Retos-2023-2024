package org.example.entities.enemies.strongenemies

import org.example.controller.Board
import org.example.controller.Position
import org.example.entities.enemies.Enemy

abstract class StrongEnemy(position: Position) : Enemy(position){

    /**
     * Mueve el enemigo a una nueva posicion que está libre, no sale del while hasta que lo ha movido
     */
    fun move(dungeon : Board){
        var hasmoved = false
        while (!hasmoved){
            val newx = (0..<dungeon.trueBoard.size).random()
            val newy = (0..<dungeon.trueBoard[0].size).random()
            if (dungeon.trueBoard[newx][newy] == null){
                dungeon.trueBoard[newx][newy] = this
                dungeon.trueBoard[position.X][position.Y] = null
                super.position = Position(newx,newy)
                hasmoved = true
            }
        }
    }
}