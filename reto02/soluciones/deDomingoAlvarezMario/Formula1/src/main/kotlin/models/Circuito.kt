package org.example.models

import org.example.models.Pilotos.*
/**
 * Clase principal que llama a todas las demas y donde esta la funcion de inicializar el
 * juego y el orden correcto para inicializar y llamar cada funcion
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 */
class Circuito(
    private val distance: Int = 10,
    private val mundo: Array<Array<String?>> = Array(8) { arrayOfNulls<String?>(distance) },
    private var fin: Boolean = false,
    private val numVueltas: Int = 3,
    private var segundo: Int = 0,
){
    private val fernandoAlonso = FernandoAlonso(2,0)
    private val maxVerstappen = MaxVerstappen(0,0)
    private val lanceStroll = LanceStroll(3,0)
    private val checoPerez = ChecoPerez(1,0)
    private val participantes = Inscripcion()
    private val hamilton = Hamilton(4,0)
    private val leClerc = LeClerc(7,0)
    private val russel = Russel(5,0)
    private val sainz = Sainz(6,0)

    /**
     * Funcion inicial que llama a todos los metodos
     */
    fun simular() {
        var j = 0
        colocar()
        impairMatrix()
        do{
            Thread.sleep(500)
            if (segundo%6 ==0){llover()}
            println()
            println()
            pilotos()
            j++
            impairMatrix()
            searchaccidents()
            segundo++
        }while(!fin)
        resultadoFinal()
    }

    /**
     * Cada 3 segs hay un 35% de provabilidad de que llueva
     */
    private fun llover(){
        val llueve = (1..100).random()
        if (llueve <= 35) {
            println("""Segun el nuevo informe de los Meteorología ha empezado 
                |a 🌦️ llover ⛈️ y necesitaremos un cambio de nuematicos 🛞
                |Para todos los Pilotos🏎️""".trimMargin())
            checoPerez.llueve()
            fernandoAlonso.llueve()
            hamilton.llueve()
            lanceStroll.llueve()
            leClerc.llueve()
            maxVerstappen.llueve()
            russel.llueve()
            sainz.llueve()
        }else{
            println("Segun el informe Meteorologico el dia esta despejado ☀️")
        }
    }

    /**
     * Llama a la funcion participantes de cada piloto
     */
    private fun pilotos(){
        participante(checoPerez)
        participante(fernandoAlonso)
        participante(hamilton)
        participante(lanceStroll)
        participante(leClerc)
        participante(maxVerstappen)
        participante(russel)
        participante(sainz)
    }

    /**
     * @param nombre es el nombre de cada participante
     */
    private fun participante(nombre: Pilotos){
        mundo[nombre.i][nombre.j] = null
        nombre.accion()
        mundo[nombre.i][nombre.j] = nombre.nombre
        if (nombre.vuelta == numVueltas){
            nombre.ganar()
            fin = true
        }
    }

    /**
     * Coloca a los pilotos en la posicion inicial al inicializar el mundo
     */
    private fun colocar() {
        for ((p, i) in participantes.pArray.indices.withIndex()) {
            mundo[p][0] = participantes.pArray[i]
        }
    }

    /**
     * Imprime la matriz con el nombre y simbolo de cada participante
     */
    private fun impairMatrix() {
        val apodos = arrayOf("🪽MAXV", "🪽CHEC", "💫NANO", "💫LANC", "☢️HAMI", "☢️RUSL", "💥SAIN", "💥LECL")
        println("╔${"═"*58}╗")
        println("║               100 200 300 400 BXS 600 700 900 900 1000   ║")
        for ((k, i) in mundo.indices.withIndex()) {
            print("║ ")
            print(" "+apodos[k] + "🔧 🏁 ")
            for (j in mundo[i].indices) {
                when (mundo[i][j]) {
                    maxVerstappen.nombre -> print(maxVerstappen.emji)
                    checoPerez.nombre -> print(checoPerez.emji)
                    fernandoAlonso.nombre -> print(fernandoAlonso.emji)
                    lanceStroll.nombre -> print(lanceStroll.emji)
                    hamilton.nombre -> print(hamilton.emji)
                    russel.nombre -> print(russel.emji)
                    sainz.nombre -> print(sainz.emji)
                    leClerc.nombre -> print(leClerc.emji)
                    null -> print("    ")
                }
            }
            print("🏁 ║")
            println("")
        }
        println("╚${"═"*58}╝")
    }

    /**
     * Busca y cuenta los accidentados para que si todos se accidenten acabe el juego
     */
    private fun searchaccidents(){
        var accidentados = 0
        for (i in mundo.indices) {
            for (j in mundo[i].indices) {
                if (mundo[i][j] == "accidente") {
                    accidentados++
                }
            }
        }
        if (accidentados==8){
            fin = true
            println("todos los pilotos han tenido un accidente")
        }
    }

    /**
     * Se muestran los resultados de la carrera: Ganadores, Accidentes, Ubicacion.
     */
    fun resultadoFinal(){
        println("🏁🏆🏁🏅🏁")
        println("En $segundo segundos ⌛")
        println()
        println("Los resultados son los siguientes:")
        maxVerstappen.estadoActual()
        println()
        checoPerez.estadoActual()
        println()
        fernandoAlonso.estadoActual()
        println()
        lanceStroll.estadoActual()
        println()
        hamilton.estadoActual()
        println()
        russel.estadoActual()
        println()
        sainz.estadoActual()
        println()
        leClerc.estadoActual()
    }
}

private operator fun String.times(i: Int): String {
    return this.repeat(i)
}
