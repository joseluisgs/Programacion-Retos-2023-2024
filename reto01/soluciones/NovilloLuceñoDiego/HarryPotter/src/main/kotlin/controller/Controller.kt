package org.example.controller

import org.example.entities.allies.Ally
import org.example.entities.enemies.Enemy
import entities.Player
import org.example.entities.Entity
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
        /**
         * Crea un tablero y un nuevo personaje
         * @see Board.positionEntities esta función inicia todos los puestos con todos las entidades
         * @return un objeto controler para poder llamar a la funcion startround
         */
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

    /**
     * Ejecuta cada ronda, enseña el mapa y se mueve por la matriz
     * @see trytomove que empieza el movimiento
     */
    fun startround(){
        dungeon.showmap()
        trytomove()
    }

    /**
     * Coge la direccion que le da el usuario (que puede ser N/S/E/O)
     * Le das una letra y si es valida (validatedirection()) y se puede ahi (.cango()) mover se mueve en esa direccion
     * Llama a la funcion dealwhatsthere() para ver si es una entidad o nulo y actua segun lo que encuentre
     */
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

    /**
     * Llama a una funcion dependiendo de lo que se encuentre y imprime el mapa con lo que haya
     * encontrado y si es un enemigo llama a la funcion foundenemy(), si es un amigo llama a foundally()
     * y si es un horrocrux lo recoge.
     * Finalmente se mueve
     * @param direction coge la direccion en la que se quiere mover el jugador y  mira lo que hay
     */

    private fun dealwithwhatsthere(direction: Direction) {
        val newPosition = Position((player.position.X + direction.X), (player.position.Y + direction.Y))
        val currentposition = dungeon.trueBoard[newPosition.X][newPosition.Y]
        dungeon.unlocknewposition(newPosition)
        if (currentposition is Entity) dungeon.showmap() // Solo enseña el mapa si lo que se encuentra no es nulo
        when (currentposition){
            is Ally -> foundally(newPosition)
            is Enemy -> foundenemy(newPosition)
            is Horrocrux -> {
                horrocruxesfound++
                println("Harry found a horrocrux! ($horrocruxesfound/7)")
                dungeon.trueBoard[newPosition.X][newPosition.Y] = null
            }
        }
        println("You moved ${direction.directionname}")
        player.move(dungeon,direction)
    }

    /**
     * Si acierta: si es un dementor lo mata, si es un enemigo "fuerte" lo mueve
     * Si falla: si es un Dementor; le hace daño a Harry y si es un enemigo fuerte le hace daño y se mueve
     * @param newPosition la posicion en la que esta el enemigo
     */
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

    /**
     * Sale de la funcion anterior, lo saqué para que se puede leer mas facilmente,
     * si el enemigo le hace daño se imprime su HP y mueve al enemigo
     * @param currentEnemy es el enemigo actual, lo necesito para moverle y para mandarlo a la funcion (takedmg())
     */
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

    /**
     * Mira si el amigo es Ron para saber si cabe la posibilidad de que vaya a hacer daño a Harry,
     * y mira si está muerto para saber si se debe acabar el juego.
     * En el caso de que el amigo no sea ron o incluso si es Ron y no falla, añade HP a Harry
     * Finalmente imprime el HP
     * @param newPosition la posicion del amigo que ha encontrado.
     */

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

    /**
     * Si pierde todo el HP se acaba el juego
     */
    private fun gameover() {
        println("Harry died in the dungeons...Lord Voldermort wins")
        println()
        printstats()
        exitProcess(0)
    }

    /**
     * Imprime lo que ha hecho a lo largo del juego cuendo se acaba
     */
    fun printstats() {
        println("Here´s what you did this game:")
        println("Killed $dementorskilled dementor/s")
        println("Won $duelswon duel/s")
        println("Lost $duelslost duel/s")
        println("Found $alliesfound ally/ies")
        println("Found $horrocruxesfound horrocrux/es")
    }

    /**
     * Se queda atascado aqui hasta que le pongas una direccion valida (n,s,e,o) en ingles y la devuelve
     * @param input es el resultado del readln()
     */
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

    /**
     * Mira si el caracter que le metiste es uno de los validos
     * @see validatedirection es la funcion que lo utiliza
     *
     */
    private fun isvalid(input : String) : Boolean{
        return (input.length == 1 && (
                    input == "N" ||
                    input == "S" ||
                    input == "E" ||
                    input == "W" )
                )
    }

    /**
     * Mira si la cantidad de horrocruxes son 7 para ver si se puede finalizar el juego
     */
    fun winningconditionsaremet(): Boolean {
        return horrocruxesfound == 7
    }
}