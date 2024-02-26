package models

const val PREPARE_TIME=3000L

class Cocinero {

    fun prepararPlato(menu: Menu): Plato{
        Thread.sleep(PREPARE_TIME)
        return Plato(menu, menu.valoracion)
    }
}