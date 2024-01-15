package models

class Dementor : Enemigo() {
    override fun atacar() {
        println("¡Dementor en frente! Harry pierde 10 puntos de vida.")
    }

}