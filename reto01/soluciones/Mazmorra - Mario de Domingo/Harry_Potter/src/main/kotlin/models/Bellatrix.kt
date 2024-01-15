package models

import mundo

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 * @property nombre del personaje/amigo
 * @property danio que inflingen
 */
class Bellatrix : Enemigos("Bellatrix 🐍",-30){
    var valor = 0

    /**
     * @author Mario de Domingo
     * @return casilla de bellatrix
     * @version 1.0-SNAPSHOT
     */
    fun casilla(){
        println("Has encontrado a ⚕️🐍 BELLATRIX 🐍⚕️")
        print("""A- Atacar.    D- Defenderse
            |       Eleccion:  """.trimMargin())
        val respuesta: String = readln()
        when(respuesta) {
            "a" -> {
                val probExito=(1..2).random()
                if (probExito ==1){
                    println("¡Enhorabuena! la has herido pero ha consegido escaparse")
                    mundo.casillaRandom(nombre)
                }else {
                    println("¡Has fallado! Bellatrix contraataca quitandote 30 de vida y se escapa")
                    mundo.casillaRandom(nombre)
                    valor = danio
                }
            }
            "d" -> {
                println("Te has defendido y Vellatrix a escapado")
                mundo.casillaRandom(nombre)
            }
            else -> println("Respuesta no valida")
        }
    }
}
