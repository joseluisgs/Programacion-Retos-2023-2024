package models

import kotlin.math.min

class Circuito (
    val pistasizelargo: Int=8,
    val pistasizeancho: Int=10,
    val totalcorredores: Int=8,
    val tiempomax:Int=30
) {
    private val pista = Array(pistasizelargo){ arrayOfNulls<Piloto>(pistasizeancho)}
    private val pilotos=Array(totalcorredores){
        when(){

        }
    }


    fun colocarPilotos(){
        for (fila in 0..<pistasizelargo){
            for(col in 0..<pistasizeancho){
                if(pista[fila][col]!=null){
                    pista[fila][col]=null
                }
            }
        }
        val pilotosaAlmacenar = min(pistasizeancho * pistasizelargo, pilotosRestantes)
        val pilotosAlmacenados=0
        val pilotosIndexados=0
        while (pilotosAlmacenados<pilotosaAlmacenar){
            while(pilotosIndexados<piloto.size)
        }
    }

    fun comenzarCarrera() {
        var tiempo = 1
        var vuelta=1
        colocarPilotos()
        imprimirPista()
        do{
            println("Tiempo: $tiempo")
            println("Vuelta: $vuelta")

            if (tiempo%5==0){
                println("PIT STOP!!")
            }
            if (tiempo%10==0){
                vuelta++
                println("$vuelta vuelta")
                colocarPilotos()
            }
            if (tiempo%6==0){
                var random=(0..100).random()
                if (random<=35){
                    println("PitStop lluvia")

                }
            }
            Thread.sleep(1000)
            tiempo++

        }while(tiempomax>=tiempo)

    }
    private fun imprimirPista(){
        for (fila in pista){
            for (piloto in fila){
                if (piloto==null){
                    print("[ ]")
                } else {
                    print(piloto.coche)
                }
            }
            println()
        }
    }



}