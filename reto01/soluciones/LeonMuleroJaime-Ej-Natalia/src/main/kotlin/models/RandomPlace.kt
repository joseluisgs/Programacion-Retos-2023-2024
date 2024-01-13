package models

interface RandomPlace {

    fun selectRandomPlace(mapa:  Array<Array<Celda>>): Posicion {
        var fila: Int
        var columna: Int
        do {
            fila = (0..5).random()
            columna = (0..5).random()
        } while (mapa[fila][columna].personaje != null)
        val posicion = Posicion(fila,columna)
        return posicion
    }

}