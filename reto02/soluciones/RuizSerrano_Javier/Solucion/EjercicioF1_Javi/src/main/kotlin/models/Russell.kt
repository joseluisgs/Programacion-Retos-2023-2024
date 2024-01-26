package org.example.models

class Russell: Mercedes() {
    private val posAccidente=15
    override var nombre ="George Russell"
    override var posicion= Array(2){0}
    override val fila = 5
    override var columna = 0
    override var vuelta=0
    override var pit1=(1..3).random()
    override var pit2=(1..3).random()
    override var pit3=(1..3).random()
    val totalPits =(pit1 +pit2 + pit3)
}