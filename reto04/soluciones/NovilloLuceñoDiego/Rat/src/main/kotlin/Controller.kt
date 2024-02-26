package org.example

import org.example.models.Kitchen
import org.example.models.Restaurant
import org.example.models.Server
import org.example.models.Table
import org.example.models.Mood

object Controller {
    private val tables = Table.createXAmountofTables(3)
    private val servers = Server.createXAmountofServers(tables)
    private val restaurant = Restaurant(tables = tables, servers = servers)
    private val kitchen: Kitchen = Kitchen()

    private fun checkForNewFinishes(){
        //Recorre la lista de mesas
        for (i in tables.indices){
            //Si han terminado de comer resetea la mesa y el servidor
            if (tables[i].eatingTime == 5) {
                restaurant.menu.rateMenuItem(tables[i].order!!) // No puede ser nula nunca si eating time llegó a 5
                println("El cliente en la mesa ${tables[i].tableNumber} ha terminado su menu")
                restaurant.amountMade += tables[i].order!!.item.price
                resetTableandServer(tables[i])
            }
            //Si están enfadados imprime un mensaje en la consola
            if (tables[i].checkState() == Mood.MAD && tables[i].order == null) println("La mesa ${tables[i].tableNumber} está enfadada...")
            //Si están furiosos, se van y resetea la mesa y el servidor
            if (tables[i].checkState() == Mood.FURIOUS  && tables[i].order == null){
                println("La mesa ${tables[i].tableNumber} ha esperado mucho,y se ha marchado furiosa")
                restaurant.unhappyCostumers++
                resetTableandServer(tables[i])
            }
        }
    }

    /**
     * Encuentra al servidor que corresponde a la mesa y llama a la función "resetServer"
     * También llama a la función "reset
     */
    private fun resetTableandServer(table : Table){
        servers.find { it.table == table }!!.resetServer()
        table.resetTable()
    }

    /**
     * Recorre la lista de mesas y si encuentra algua que no está ocupada, la ocupa,
     * escribe un mensaje en la consola y suma uno a la variable "costumers"
     */
    private fun seatSomeoneIfPossible(){
        for (i in tables){
            if (!i.isTaken){
                i.resetTable()
                i.isTaken = true
                servers.find { it.table.tableNumber == i.tableNumber }?.callServerOver()
                println("La mesa ${i.tableNumber} ha sido sentada")
                restaurant.costumers++
            }
        }
    }

    /**
     * LLama a una serie de métodos para simular el funcionamento del restaurante
     * @see seatSomeoneIfPossible()
     * @see addWaitingTimeIfAppropiate()
     * @see checkForNewFinishes()
     * @see Restaurant.showRestaurant()
     * @see Kitchen.checkForNewFinishedOrders()
     * @see chef.tryToPrepareaQueuedPlate()
     * @see Restaurant.moveServers()
     */
    fun startSimulation(){
        while (restaurant.costumers <= 10){
            seatSomeoneIfPossible() // Busca si hay sitios libres, y si hay, sienta a alguien
            addWaitingTimeIfAppropiate() // Si hay alguien sentado añade tiempo de espera
            checkForNewFinishes() // Busca mesas que han terminado de comer
            addEatingTimeIfAppropiate() // Añade tiempo comiendo si están comiendo
            restaurant.showRestaurant() // Enseña el mapa
            kitchen.chef.tryToPrepareaQueuedPlate() //Si ha terminado de preparar un plato, prepara otro
            kitchen.checkForNewFinishedOrders() // Mira si el chef ha terminado un plato
            restaurant.moveServers(kitchen) // Mueve a los servidores en el mapa y llama a la funcion Server.move()
        }
    }

    /**
     * Si la mesa está ocupada y no tienen su pedido, añade tiempo de espera
     */
    private fun addWaitingTimeIfAppropiate(){
        for (i in tables){
            if (i.isTaken && i.order == null) i.waitingTime++
        }
    }

    /**
     * Si la mesa tiene su pedido, añade tiempo comiendo
     */
    private fun addEatingTimeIfAppropiate(){
        for (i in tables){
            if (i.order != null) i.eatingTime++
        }
    }
    /**
     * Imprime por pantalla la cantidad recaudada, la cantidad de personas sentadas y la cantidad
     * que se han ido cabreados
     */
    fun printStats(){
        println()
        println("----------------------------------------------------------------")
        println("En total, hoy el restaurante ha recaudado: ${restaurant.amountMade}€")
        println("El restaurante ha acogido: ${restaurant.costumers} clientes")
        println("El restaurante ha perdido ${restaurant.unhappyCostumers} clientes")
    }
}