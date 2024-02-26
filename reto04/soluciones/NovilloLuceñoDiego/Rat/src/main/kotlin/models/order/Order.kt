package org.example.models.order

import java.util.UUID

data class Order (val uuid : UUID = UUID.randomUUID(), val item : MenuItem){

    companion object{
        fun getRandomOrder(menu: Menu) : Order {
            val randomNumber = (0..100).random()
            when {
                randomNumber >= 50 -> return Order(item = menu.options[0])
                randomNumber >= 25 -> return Order(item = menu.options[1])
                randomNumber >= 15 -> return Order(item = menu.options[2])
                randomNumber >= 8 -> return Order(item = menu.options[3])
                else -> return Order(item = menu.options[4])
            }
        }
    }
}