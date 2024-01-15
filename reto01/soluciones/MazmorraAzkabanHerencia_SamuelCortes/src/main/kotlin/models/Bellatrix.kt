package models

class Bellatrix : Enemigo() {
    override fun atacar() {
        println("¡Bellatrix apareció! Harry pierde 30 puntos de vida y Bellatrix se reubica.")
    }
}