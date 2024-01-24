package org.example.models

/**
 * Fernando Alonso, Piloto de Aston Martin
 * @property tiempoPerdido tiempo perdido debido a mala estrategia
 */
class GOAT(nombre: String = "Alonso", override var tiempoPerdido: Int = 0) :AstonMartin(nombre, fila = 2, columna = 0, probAccidente = 1) {
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