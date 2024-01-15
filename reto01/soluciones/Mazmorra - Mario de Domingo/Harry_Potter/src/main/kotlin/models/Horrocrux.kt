package models

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 * @property nombre del objeto
 * @property numHorrocrux numero de horrocrux totales
 * @property conseguidos
 */
data class Horrocrux (
    val nombre: String = "Horrocrux 🪙",
    val numHorrocrux: Int = 7,
    var conseguidos: Int = 0){

    /**
     * @author Mario de Domingo
     * @version 1.0-SNAPSHOT
     * @return +horrocrux
     */
    fun casilla(){
        println("¡Enhorabuena! Destruiste un Horrocrux")
        conseguidos ++
    }
}