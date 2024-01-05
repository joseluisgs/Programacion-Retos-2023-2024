package org.example.controller

import entities.Player
import org.example.entities.Horrocrux
import org.example.entities.allies.Herminone
import org.example.entities.allies.McGonagall
import org.example.entities.allies.Ron
import org.example.entities.enemies.Dementor
import org.example.entities.enemies.strongenemies.Bellatrix
import org.example.entities.enemies.strongenemies.Voldemort
import org.example.objects.Entity

const val WIDTH = 6
const val HEIGHT = 6
const val HORROCRUXESAMOUNT = 7
const val DEMENTORSAMOUNT = 6

class Board {
    var gameBoard : Array<Array<Boolean>> = Array(HEIGHT) { Array(WIDTH) { false } }
    var trueBoard : Array<Array<Entity?>> = Array(HEIGHT) { Array(WIDTH) { null } }

    fun positionEntities(player: Player){
        trueBoard[0][0] = player
        unlocknewposition(Position(0,0))
        var freePosition : Position
        //Placing the horrocruxes
        for (i in 0 until HORROCRUXESAMOUNT){
            freePosition = getafreeposition()
            trueBoard[freePosition.X][freePosition.Y] = Horrocrux(freePosition)
        }
        //Placing the Enemies
        for (i in 0 until DEMENTORSAMOUNT){
            freePosition = getafreeposition()
            trueBoard[freePosition.X][freePosition.Y] = Dementor(freePosition)
        }
        freePosition = getafreeposition()
        trueBoard[freePosition.X][freePosition.Y] = Voldemort(freePosition)
        freePosition = getafreeposition()
        trueBoard[freePosition.X][freePosition.Y] = Bellatrix(freePosition)
        //Placing the Allies
        freePosition = getafreeposition()
        trueBoard[freePosition.X][freePosition.Y] = Herminone(freePosition)
        freePosition = getafreeposition()
        trueBoard[freePosition.X][freePosition.Y] = Ron(freePosition)
        freePosition = getafreeposition()
        trueBoard[freePosition.X][freePosition.Y] = McGonagall(freePosition)
    }

    private fun getafreeposition() : Position{
        var done = false
        var freePosition = Position(0,0)
        while(!done){
            val randomX = trueBoard.indices.random()
            val randomY = trueBoard.indices.random()
            if (trueBoard[randomX][randomY] == null){
                freePosition = Position(randomX,randomY)
                done = true
            }
        }
        return freePosition
    }

    fun unlocknewposition(position: Position){
        gameBoard[position.X][position.Y] = true
    }

    fun showmap() {
        for (i in gameBoard.indices){
            for (j in gameBoard[0].indices){
                if (gameBoard[i][j]){
                    if (trueBoard[i][j] is Entity) {
                        val currentspot = trueBoard[i][j]
                        when (currentspot) {
                            is Dementor -> print("\uD83D\uDC7B")
                            is Player -> print("\uD83E\uDDD9")
                            is Voldemort -> print("\uD83E\uDDDB\uD83C\uDFFB\u200D♂\uFE0F")
                            is Bellatrix -> print("\uD83D\uDC69\uD83C\uDFFB\u200D\uD83C\uDFA4")
                            is Herminone -> print("\uD83E\uDDD9\uD83C\uDFFD\u200D♀\uFE0F")
                            is McGonagall -> print("\uD83E\uDDD9\uD83C\uDFFC\u200D♀\uFE0FF")
                            is Ron -> print("\uD83E\uDDD9\uD83C\uDFFC")
                            is Horrocrux -> print("✨")
                        }
                    }else{
                        print("\uD83D\uDFEB")
                    }
                }else{
                    print("⚫")
                }
            }
            println()
        }
    }
}