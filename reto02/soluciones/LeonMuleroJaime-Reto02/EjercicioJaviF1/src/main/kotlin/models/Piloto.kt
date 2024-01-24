package models

/**
 * Clase para los pilotos
 * @property nombre nombre del personaje
 * @property siglas siglas de cada piloto
 * @property emoticono emoticono para imprimir por pantalla
 * @property posibilidadAccidente cantidad de porcentaje en el cual un piloto sufre un accidente
 * @property tiempo variable que almacena el tiempo en el que llega a meta
 * @property vuelta variable que almacena la vuelta del piloto
 * @property finMovement variable que declara si un piloto ha terminado de moverse o puede seguir moviéndose
 * @property meta variable que indica si un piloto ha llegado a la meta
 * @property contadorPitStop variable que almacena el tiempo de pitStop de cada piloto
 * @author Jaime Leon Mulero
 * @since 1.0.0
 */
abstract class Piloto (
    val nombre: String,
    val siglas: String,
    var emoticono: String,
    val posibilidadAccidente: Int,
    var tiempo: Int,
    var vuelta: Int,
    var finMovement: Boolean,
    var meta: Boolean,
    var contadorPitStop: Int
) {

    /**
     * Función que reemplaza el toString por defecto y crea uno propio que devuelve el nombre
     */
    override fun toString(): String {
        return nombre
    }

    /**
     * Función que calcula si un piloto sufre un accidente mediante su probabilidad
     */
    fun accidente(): Boolean {
        var accidente = false
        val random = (0..100).random()
        if (random <= posibilidadAccidente) accidente = true
        return accidente
    }

}