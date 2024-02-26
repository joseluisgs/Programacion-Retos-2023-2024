package org.example.models

import org.example.models.order.Menu
import org.example.models.order.Order

data class Server (
    var order : Order? = null,
    var isWaiting : Boolean = false,
    var goingToTable : Boolean = false,
    var goingForOrder : Boolean = false,
    var resettingPosition: Boolean = false,
    var hasArrivedAtTarget : Boolean = false,
    val table: Table
){
    companion object{
        /**
         * Crea un array de servidores que corresponden a una mesa
         * @param tables las mesas a la que debe corresponder los servidores
         */
        fun createXAmountofServers(tables: Array<Table>) : Array<Server>{
            val output = Array<Server>(tables.size){ Server(table = Table(tableNumber = 0)) }
            var currentTable = 0
            for (i in output.indices) output[i] = Server(table = tables[currentTable++])
            return output
        }
    }

    /**
     * Maneja el movimiento del servidor y lo que debe de hacer depenndiendo de su posicion y estado
     * @param currentPosition para saber en que posicion está en el array
     * @param kitchen para mandar pedidos y recogerlos
     * @param table para recoger un pedido a través de la función "giveRandomOrder()" en la clase Table
     * @param menu la necesita para que la mesa pueda elejir un pedido
     */
    fun move(currentPosition: Int,
             kitchen: Kitchen,
             table: Table,
             menu: Menu,
             ratPosition : Int?) : Int{
        when{
            goingToTable && order == null -> {
                if (currentPosition == 4){
                    goingToTable = false
                    order = table.giveRandomOrder(menu)
                    goingForOrder = true
                    return -1 //Tiene que retroceder una posicion para ir a la cocina
                }else return 1
            }

            goingToTable -> { // Cuando ha llegado a la mesa con el plato
                if (ratPosition != -1 &&
                    ratPosition == currentPosition &&
                    (1..10).random() <= 1 ){
                        println("El camarero se ha tropezado con una rata y se le ha caido el plato")
                        goingToTable = false
                        goingForOrder = true
                }else if (currentPosition == 4){
                    table.order = order
                    resettingPosition = true
                    goingToTable = false
                }else return 1
            }
            resettingPosition -> { //Cuando ya ha servido a alguien y vuelve para atrás
                if (currentPosition != 0) return -1
                else{
                    resettingPosition = false
                }
            }
            goingForOrder && !isWaiting -> { //Cuando ha llegado a la cocina
                if (currentPosition == 0){
                    order?.let { kitchen.chef.addWorkIfPossible(it) }
                    isWaiting = true
                }else return -1
            }

            isWaiting -> { //Cuando está esperando por su pedido
                val checkOrder = kitchen.takeOrder(order!!.uuid)
                if (checkOrder != null){
                    goingForOrder = false
                    goingToTable = true
                    order = checkOrder
                    isWaiting = false
                    return 1
                }
            }
        }
        return 0 //Si no se tiene que mover
    }

    /**
     * Cambia su estado
     */
    fun resetServer(){
        resettingPosition = true
        goingForOrder = false
        order = null
    }

    /**
     * Cambia su estado para que valla a la mesa
     */
    fun callServerOver(){
        goingToTable = true }

}