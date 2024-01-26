import models.*

fun main(){
    println("-----------🏎️✨SEAN BIENVENIDOS AL PRIMER GRAN PREMIO DE DAW✨🏎️-----------")
    println("Cuatro equipos de Fórmula 1 competirán en esta emocionante carrera")
    println("¡Prepárense para una experiencia única en el mundo automovilístico!")
    println(".-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.🏁PARRILLA DE SALIDA🏁.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.")

    val circuito=Circuito()
    circuito.darSalida()
    circuito.simular()

}