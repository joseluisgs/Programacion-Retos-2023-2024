package models

class Voldemort : Enemigo() {
    override fun atacar() {
        println("¡Voldemort apareció! Harry pierde 70 puntos de vida y Voldemort se reubica.")
    }
}