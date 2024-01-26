package models

import org.example.models.Pilot
import org.example.models.brands.AstorMartin
import org.example.models.brands.Ferrari
import org.example.models.brands.Mercedes
import org.example.models.brands.RedBull

/**
 * Una clase que se encarga de todas los metodos que tienen que ver con el mapa,
 * contiene "trackmatrix" que es donde se pondran los Pilotos
 */
class Track {
    val trackmatrix = Array<Array<Pilot?>>(8){ Array(10){ null } }

    /**
     * @param participatingpilots son los pilotos que estan dentro del "track"
     * Mira a cada piloto y actualiza su posicion cada vez que se ejecuta
     */
    fun updatetrack(participatingpilots : Array<Pilot>){
        for (i in participatingpilots.indices){
            trackmatrix[i] = arrayOfNulls(10)
            trackmatrix[i][participatingpilots[i].position] = participatingpilots[i]
        }
    }

    /**
     * Un metodo que imprime el mapa en la consola, dependiendo de lo que se encuentre en la matriz,
     * imprime una cosa o otra
     */
    fun printmap(){
        for (i in trackmatrix.indices){
            for (j in trackmatrix[0].indices) {
                val currentposition = trackmatrix[i][j]
                if (currentposition is Pilot && currentposition.state.dnf) print("☠\uFE0F")
                else when (currentposition){
                        null -> print("⬛")
                        is RedBull -> print("\uD83D\uDD35")
                        is Ferrari -> print("\uD83D\uDD34")
                        is AstorMartin -> print("⚪")
                        is Mercedes -> print("\uD83D\uDFE3")
                }
            }
            println()
        }
    }
}