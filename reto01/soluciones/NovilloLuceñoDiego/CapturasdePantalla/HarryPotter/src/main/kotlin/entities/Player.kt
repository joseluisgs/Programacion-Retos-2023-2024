package entities

import org.example.controller.Board
import org.example.controller.Direction
import org.example.controller.Position
import org.example.entities.enemies.Health
import org.example.entities.Entity

const val STARTINGHEALTH = 100
const val PLAYERSUCCESSRATE = 60

class Player(position: Position) : Entity(position){
    val health = Health(STARTINGHEALTH)
    val isalive
        get() = this.health.value > 0

    fun canGo(direction: Direction, dungeon : Board) : Boolean{
        val newxposition = position.X + direction.X
        val newyposition = position.Y + direction.Y

        return !((newyposition < 0 || newxposition < 0) ||
                (newyposition >= dungeon.gameBoard.size || newxposition >= dungeon.gameBoard.size))
    }

    fun spellissuccesful() : Boolean{
        return (0..100).random() <= PLAYERSUCCESSRATE
    }

    fun move (dungeon: Board, direction: Direction){
        val newplayerposition = Position((position.X + direction.X), (position.Y + direction.Y))
        dungeon.trueBoard[newplayerposition.X][newplayerposition.Y] = this
        dungeon.trueBoard[position.X][position.Y] = null
        super.position = newplayerposition
    }
}