package models

/**
 * Clase para el piloto Checo Perez
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
 * @see Piloto
 */
class ChecoPerez (
    nombre: String = "Checo",
    siglas: String = "PER",
    emoticono: String = "🏎️",
    posibilidadAccidente:Int = 2,
    tiempo: Int = 0,
    vuelta: Int = 1,
    finMovement: Boolean = false,
    meta:Boolean = false,
    contadorPitStop: Int = 0
) : Piloto(nombre, siglas, emoticono, posibilidadAccidente, tiempo, vuelta, finMovement, meta, contadorPitStop), RedBull {
}