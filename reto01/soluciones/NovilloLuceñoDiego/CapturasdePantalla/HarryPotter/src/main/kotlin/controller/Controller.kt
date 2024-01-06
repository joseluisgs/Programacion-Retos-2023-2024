package org.example.controller

import org.example.entities.allies.Ally
import org.example.entities.enemies.Enemy
import entities.Player
import org.example.entities.Horrocrux
import org.example.entities.allies.Ron
import org.example.entities.enemies.Dementor
import org.example.entities.enemies.strongenemies.StrongEnemy
import kotlin.system.exitProcess

const val STARTINGPOSITION = 0

class Controller (
    private val dungeon : Board,
    private val player : Player)
{
    companion object{

        fun creategameinstance() : Controller{
            val newPlayer = Player(Position(STARTINGPOSITION, STARTINGPOSITION))
            val newDungeon = Board()
            newDungeon.positionEntities(newPlayer)
            return Controller(newDungeon,newPlayer)
        }
    }

    private var dementorskilled = 0
    private var duelswon = 0
    private var duelslost = 0
    private var alliesfound = 0
    private var horrocruxesfound = 0

    fun startround(){
        dungeon.showmap()
        trytomove()
    }

    private fun trytomove(){
        println("What direction do you want to go? (N|S|E|W)")
        var direction : Direction
        do {
            direction = validatedirection(readln())
            if (!player.canGo(direction, dungeon)){
                println("You can´t go in that direction, try again:")
            }
        }while (!player.canGo(direction, dungeon))
        dealwithwhatsthere(direction)
    }

    private fun dealwithwhatsthere(direction: Direction) {
        val newPosition = Position((player.position.X + direction.X), (player.position.Y + direction.Y))
        val currentposition = dungeon.trueBoard[newPosition.X][newPosition.Y]
        dungeon.unlocknewposition(newPosition)
        when (currentposition){
            is Ally -> {
                dungeon.showmap()
                foundally(newPosition)
            }

            is Enemy -> {
                dungeon.showmap()
                foundenemy(newPosition)
            }

            is Horrocrux -> {
                dungeon.showmap()
                horrocruxesfound++
                println("Harry found a horrocrux! ($horrocruxesfound/7)")
                dungeon.trueBoard[newPosition.X][newPosition.Y] = null
            }
        }
        println("You moved ${direction.directionname}")
        player.move(dungeon,direction)
    }

    private fun foundenemy(newPosition: Position) {
        val currentEnemy = dungeon.trueBoard[newPosition.X][newPosition.Y] as Enemy
        currentEnemy.discoveredenemymsg()
        if (player.spellissuccesful()) {
            if (currentEnemy is Dementor) {
                println("Harry used expecto patronum!!, he´s safe for now...")
            }else {
                if (currentEnemy is StrongEnemy){
                    println("Harry was able to beat ${currentEnemy.name}!, for now...")
                    currentEnemy.move(dungeon)
                }
            }
        } else {
            println("Harry´s spell failed...")
            dealwiththefailure(currentEnemy)
            if (!player.isalive) gameover()
        }
    }

    private fun dealwiththefailure(currentEnemy: Enemy) {
        if (currentEnemy is Dementor) {
            player.health.takedmg(currentEnemy)
            println("HP: ${player.health.gethealthvalue()}")
        } else {
            if (currentEnemy.canattack()) {
                player.health.takedmg(currentEnemy)
                println("HP: ${player.health.gethealthvalue()}")
                (currentEnemy as StrongEnemy).move(dungeon)
            } else{
                println("They didn´t attack! he´s safe for now...")
                (currentEnemy as StrongEnemy).move(dungeon)
            }
        }
    }

    private fun foundally(newPosition: Position) {
        val currentally = dungeon.trueBoard[newPosition.X][newPosition.Y] as Ally
        currentally.discoveredallymsg()
        if (currentally is Ron) {
            if (currentally.failstoheal()){
                player.health.takedmg(currentally)
            }else player.health.heal(currentally)
            if (!player.isalive) gameover()
        } else {
            player.health.heal(currentally)
        }
        println("HP: ${player.health.gethealthvalue()}")
        alliesfound++
    }

    private fun gameover() {
        println("Harry died in the dungeons...Lord Voldermort wins")
        println()
        printstats()
        exitProcess(0)
    }

    fun printstats() {
        println("Here´s what you did this game:")
        println("Killed $dementorskilled dementor/s")
        println("Won $duelswon duel/s")
        println("Lost $duelslost duel/s")
        println("Found $alliesfound ally/ies")
        println("Found $horrocruxesfound horrocrux/es")
    }

    private fun validatedirection(input : String): Direction{
        var editableinput = input.uppercase()
        do {
            if (!isvalid(editableinput)){
                println("Not a valid response, try one of these (N|S|E|W)")
                editableinput = readln().uppercase()
            }
        }while (!isvalid(editableinput))

        var chosendirection = Direction.North
        when(editableinput){
            "S" -> chosendirection = Direction.South
            "E" -> chosendirection = Direction.East
            "W" -> chosendirection = Direction.West
        }
        return chosendirection
    }

    private fun isvalid(input : String) : Boolean{
        return (input.length == 1 && (
                    input == "N" ||
                    input == "S" ||
                    input == "E" ||
                    input == "W" )
                )
    }

    fun winningconditionsaremet(): Boolean {
        return horrocruxesfound == 7
    }
}