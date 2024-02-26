package org.example.models

import org.example.models.order.Menu

const val TABLEAMOUNT = 3

class Restaurant(
    tables : Array<Table>,
    servers : Array<Server>,
    private val restaurantGrid : Array<Array<Any?>> = Array<Array<Any?>>(TABLEAMOUNT){ arrayOfNulls(6) }
){
    init {
        //Posiciona a los servidores y las mesas en la matriz
        var current = 0
        for (i in restaurantGrid.indices){
            for (j in restaurantGrid[0].indices){
                when (j) {
                    0 -> restaurantGrid[i][j] = servers[current]
                    5 -> restaurantGrid[i][j] = tables[current++] // Añade uno para la siguiente
                }
            }
        }
    }

    var unhappyCostumers = 0
    var costumers = 0
    var amountMade = 0
    var menu = Menu()

    /**
     * Imprime la matriz
     */
    fun showRestaurant(){
        println()
        for (i in restaurantGrid.indices){
            if (i % 2 != 0) print("\uD83D\uDC00 ")
            else print("\uD83D\uDD2A ")
            for (j in restaurantGrid[i]){
                when (j) {
                    is Server -> print("[\uD83E\uDDD1\uD83C\uDFFD\u200D\uD83D\uDCBC]")
                    is Table -> {
                        if (j.order != null) print("[\uD83C\uDF54]")
                        else print("[\uD83C\uDF74]")
                    }
                    null -> print("[ ]")
                }
            }
            println()
        }
        println()
    }

    /**
     * Cambia a los servidores de posición dependiendo lo que devuelva la función "move" en Server
     * @param kitchen la necesita la función "move"
     */
    fun moveServers(kitchen: Kitchen){
        for (i in restaurantGrid.indices){
            val oldPosition = restaurantGrid[i].indexOfFirst { it is Server }
            val table = (restaurantGrid[i].find { it is Table }) as Table
            val newPosition = oldPosition + (restaurantGrid[i][oldPosition] as Server).move(oldPosition,
                kitchen, table, menu)
            if (oldPosition != newPosition) { // Solo si se mueve cambia la posicion
                restaurantGrid[i][newPosition] = restaurantGrid[i][oldPosition]
                restaurantGrid[i][oldPosition] = null
            }
        }
    }
}