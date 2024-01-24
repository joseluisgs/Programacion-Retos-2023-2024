package org.example.models

/**
 * Lance Stroll, Piloto de Aston Martin
 * @property tiempoPerdido tiempo perdido por mala estrategia
 */
class Stroll(nombre: String = "Stroll", override var tiempoPerdido: Int = 0) :AstonMartin(nombre, fila = 3, columna = 0, probAccidente = 10) {
    /**
     * El piloto se para durante el tiempo perdido
     */
    fun estratgia(pista: Array<Array<Pilotos?>>) {
        cagada(this)
        if(tiempoPerdido > 0){
            tiempoPerdido--
        }else if(tiempoPerdido == 0){
            moverse(pista)
        }
    }
}