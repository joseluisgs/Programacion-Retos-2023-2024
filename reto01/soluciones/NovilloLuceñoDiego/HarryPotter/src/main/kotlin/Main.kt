package org.example

import org.example.controller.Controller

/**
 * Te pregunta si quieres seguir jugando y espera una S,s, N,n, si es una s empieza otro juego
 * @return un boolean depende de lo que haya puesto el usuario
 */
fun playagain(input : String): Boolean {
    var editableinput = input.uppercase()
    var result = false
    do {
        if (!(editableinput == "Y" || editableinput == "N")){
            println("Not what i asked, try again (Y/N)")
            editableinput = readln()
        }
    }while (!(editableinput == "Y" || editableinput == "N"))

    if (editableinput == "Y"){
        result = true
    }

    return result
}

fun main() {
    do {
        println("Game starts! Good luck beating Voldemort!!")
        val controller = Controller.creategameinstance()
        do {
            controller.startround()
        }while (!controller.winningconditionsaremet())
        println("Congratulations! Harry collected all horrocruxes!!")
        println()
        controller.printstats()
        println()
        println("Do you want to play again? (Y/N)")
    }while (playagain(readln()))
}

