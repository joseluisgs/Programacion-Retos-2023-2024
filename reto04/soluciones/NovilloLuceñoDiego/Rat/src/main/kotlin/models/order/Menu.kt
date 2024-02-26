package org.example.models.order

class Menu {
    val options = arrayOf(
        MenuItem( overallRating = (0..5).random(), price = (10..100).random(), name = "Spaguetti"),
        MenuItem( overallRating = (0..5).random(), price = (10..100).random(), name = "Sopa"),
        MenuItem( overallRating = (0..5).random(), price = (10..100).random(), name = "Pizza"),
        MenuItem( overallRating = (0..5).random(), price = (10..100).random(), name = "Calzonne" ),
        MenuItem( overallRating = (0..5).random(), price = (10..100).random(), name = "Ratatouille")
    )
        get() {
            //Quiero que cada vez que pidan la lista de items esté ordenada por el rating
            field.sortByDescending { it.overallRating }
            return field
        }

    /**
     * Encuentra el item que forma parte del pedido y le da un rating random de 0 a 5
     * @param order es el pedido que está garantizado que lleve un item válido,
     * sino hubiera utilizado firstOrNull()
     */
    fun rateMenuItem(order : Order, rating : Int = (0..5).random()){
        options.first { it.uuid == order.item.uuid }.overallRating = rating
    }
}