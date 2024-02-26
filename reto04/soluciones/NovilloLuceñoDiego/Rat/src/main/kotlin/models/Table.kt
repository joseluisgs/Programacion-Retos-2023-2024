package org.example.models
import org.example.models.order.Menu
import org.example.models.order.Order

data class Table(
    val tableNumber : Int,
    var waitingTime : Int = 0,
    var isTaken : Boolean = false,
    var order: Order? = null,
    var eatingTime : Int = 0,
    var mood : Mood = Mood.NORMAL
){
    companion object{
        /**
         * Crea la cantidad de mesas que se precise en un array de objetos Table
         * @param number determina el tamaño del array y la cantidad de mesas
         */
        fun createXAmountofTables(number : Int) : Array<Table>{
            var currentTableNumber = 1
            val output = Array<Table>(number){ Table(0) }
            for (i in output.indices) output[i] = Table(currentTableNumber++)
            return output
        }
    }

    /**
     * Llama a un companion object en Order para que le de un pedido random
     */
    fun giveRandomOrder(menu: Menu) : Order{
        val order = Order.getRandomOrder(menu)
        println("La mesa Nº $tableNumber ha pedido ${order.item.name}")
        return order
    }

    /**
     * Cambia el estado de la mesa cuando se va alguien
     */
    fun resetTable(){
        this.isTaken = false
        this.waitingTime = 0
        this.isTaken = false
        this.order = null
        this.eatingTime = 0
        this.mood = Mood.NORMAL
    }

    /**
     * Actualiza el estado de la mesa y lo devuelve
     */
    fun checkState() : Mood {
        if (waitingTime == 15) mood = Mood.MAD
        else if (waitingTime == 20) mood = Mood.FURIOUS
        return mood
    }
}