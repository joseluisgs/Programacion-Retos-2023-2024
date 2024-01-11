package org.example.models

const val MAX_MORTIFAGO=6
const val MAX_HORROCRUX=7
class MinisterioBuffer {
    private val ministerio: Array<Array<Casilla?>> = Array(6) { Array(6) { null } }

    private val mortifago = Mortifago()
    private val potter = Harry()
    private val voldemort = Voldemort()
    private val bellatrix = Bellatrix()
    private val horrocrux = Horrocrux()
    private val mcGonagall = McGonagall()
    private val ronald = Ronald()
    private val hermaioni = Hermaioni()
    private val casilla = Casilla()
    private val ministerioBuffer: Array<Array<Casilla?>> = Array(6) { Array(6) { casilla } }
    private fun placePotter() {
        ministerioBuffer[0][0] = potter
    }

    /*private fun placeVoldemort(){
        var cont=0
        var filaRandom: Int
        var columnaRandom: Int
        do{
            filaRandom=(0..5).random()
            columnaRandom=(0..5).random()
            if(ministerio[filaRandom][columnaRandom]==null){
                ministerio[filaRandom][columnaRandom]=voldemort
                cont++
            }
        }while(cont<1)
    }
    private fun placeBellatrix(){
        var cont=0
        var filaRandom: Int
        var columnaRandom: Int
        do{
            filaRandom=(0..5).random()
            columnaRandom=(0..5).random()
            if(ministerio[filaRandom][columnaRandom]==null){
                ministerio[filaRandom][columnaRandom]=bellatrix
                cont++
            }
        }while(cont<1)
    }*/

    private fun placeRandom() {
        val list: Array<Personaje?> = Array(5) { null }
        list[0] = voldemort
        list[1] = bellatrix
        list[2] = mcGonagall
        list[3] = hermaioni
        list[4] = ronald
        var filaRandom: Int
        var columnaRandom: Int
        var pos = -1
        for (i in list) {
            pos++
            do {
                filaRandom = (0..5).random()
                columnaRandom = (0..5).random()
                if (ministerio[filaRandom][columnaRandom] == null) {
                    ministerio[filaRandom][columnaRandom] = list[pos]
                    break
                }
            } while (ministerio[filaRandom][columnaRandom] != null)
        }
    }


    private fun placeHorrocrux() {
        var cont = 0
        var filaRandom: Int
        var columnaRandom: Int
        do {
            filaRandom = (0..5).random()
            columnaRandom = (0..5).random()
            if (ministerio[filaRandom][columnaRandom] == null) {
                ministerio[filaRandom][columnaRandom] = horrocrux
                cont++
            }
        } while (cont < MAX_HORROCRUX)
    }


    private fun initMinisterio() {
        var cont = 0
        var filaRandom: Int
        var columnaRandom: Int
        placePotter()
        placeRandom()
        //placeVoldemort()
        //placeBellatrix()
        placeHorrocrux()
        do {
            filaRandom = (0..5).random()
            columnaRandom = (0..5).random()
            if (ministerio[filaRandom][columnaRandom] == null) {
                ministerio[filaRandom][columnaRandom] = mortifago
                cont++
            }
        } while (cont < MAX_MORTIFAGO)

    }


    private fun randomMovement(enemigo: Enemigo) {
        var randomFila: Int
        var randomColumna: Int
        do {
            randomFila = (0..5).random()
            randomColumna = (0..5).random()
            ministerio[randomFila][randomColumna]
        } while (ministerio[randomFila][randomColumna] != null)
        ministerio[randomFila][randomColumna] = enemigo
        println("Me muevo a ${randomFila + 1} y ${randomColumna + 1}")
    }


    private fun moverPotter() {
        println("Hacia que direccion nos debemos mover Harry")
        val posicion = potter.posicion
        printMinisterio(posicion[0], posicion[1])
        var exit = true
        do {
            println(
                "W-Arriba" +
                        "\nS-Abajo" +
                        "\nA-Izquierda" +
                        "\nD-Derecha"
            )
            val direccion = readln()
            if (direccion != "W" && direccion != "S" && direccion != "A" && direccion != "D") {
                println("Recuerda que para moverte solo es necesario que introduzcas 'W' // 'S' // 'A' // 'D'")
            } else {
                if (ministerio[posicion[0]][posicion[1]] == potter) {
                    ministerio[posicion[0]][posicion[1]] = null
                }
                exit = limitTestin(posicion, direccion)
                when (direccion) {
                    "W" -> if (posicion[0] > 0) posicion[0]--
                    "S" -> if (posicion[0] < 5) posicion[0]++
                    "A" -> if (posicion[1] > 0) posicion[1]--
                    "D" -> if (posicion[1] < 5) posicion[1]++
                }
            }
        } while (exit)

        val nuevaFila = posicion[0]
        val nuevaColumna = posicion[1]
        printMinisterio(nuevaFila, nuevaColumna)
        interacciones(nuevaFila, nuevaColumna)
    }


    private fun moverPotterBuffer() {
        println("Hacia que direccion nos debemos mover Harry")
        val posicion = potter.posicion
        //printMinisterio(posicion[0], posicion[1])
        var exit = true
        do {
            println(
                "W-Arriba" +
                        "\nS-Abajo" +
                        "\nA-Izquierda" +
                        "\nD-Derecha"
            )
            val direccion = readln()
            if (direccion != "W" && direccion != "S" && direccion != "A" && direccion != "D") {
                println("Recuerda que para moverte solo es necesario que introduzcas 'W' // 'S' // 'A' // 'D'")
            } else {
                    ministerioBuffer[posicion[0]][posicion[1]] = null
                exit = limitTestin(posicion, direccion)
                when (direccion) {
                    "W" -> if (posicion[0] > 0) posicion[0]--
                    "S" -> if (posicion[0] < 5) posicion[0]++
                    "A" -> if (posicion[1] > 0) posicion[1]--
                    "D" -> if (posicion[1] < 5) posicion[1]++
                }
            }
        } while (exit)

        val nuevaFila = posicion[0]
        val nuevaColumna = posicion[1]
        printMinisterio(nuevaFila, nuevaColumna)
        interacciones(nuevaFila, nuevaColumna)
    }


    private fun interacciones(fila: Int, columna: Int) {
        var acierto: Boolean
        when (ministerio[fila][columna]) {
            voldemort ->
                if (voldemort.atacar()) {
                    potter.lifePoints = (potter.lifePoints - voldemort.dmg)
                    randomMovement(voldemort)
                    ministerio[fila][columna] = potter
                } else {
                    randomMovement(voldemort)
                    ministerio[fila][columna] = potter
                }

            mortifago ->
                do {
                    if (mortifago.atacar()) {
                        potter.lifePoints = (potter.lifePoints - mortifago.dmg)
                    }
                    acierto = potter.atacar()
                    if (acierto) {
                        mortifago.muertos++
                        ministerio[fila][columna] = potter
                    }
                } while (!acierto)

            bellatrix ->
                if (bellatrix.atacar()) {
                    potter.lifePoints = (potter.lifePoints - bellatrix.dmg)
                    randomMovement(bellatrix)
                    ministerio[fila][columna] = potter
                } else {
                    randomMovement(bellatrix)
                    ministerio[fila][columna] = potter
                }

            horrocrux -> {
                horrocrux.destruirHorrocruxes()
                ministerio[fila][columna] = potter
            }

            mcGonagall ->
                if (potter.lifePoints < 100) {
                    potter.lifePoints = (potter.lifePoints + mcGonagall.heal)
                    ministerio[fila][columna] = potter
                    if (potter.lifePoints > 100) potter.lifePoints = 100
                    println("Señor Potter proteja a sus amigos.")
                } else {
                    println("Señor Potter cuidese que el mal no perdona.")
                    (ministerio[fila][columna]) = mcGonagall
                }

            hermaioni ->
                if (potter.lifePoints < 100) {
                    potter.lifePoints = (potter.lifePoints + hermaioni.heal)
                    ministerio[fila][columna] = potter
                    if (potter.lifePoints > 100) potter.lifePoints = 100
                    println("Harry te necesitamos a tope para destruir los horrocruxes restantes.")
                } else {
                    println("Pero Harry corre o nos cerraran la biblioteca.")
                    (ministerio[fila][columna]) = hermaioni
                }

            ronald ->
                if (potter.lifePoints < 100) {
                    if (ronald.liarla()) {
                        potter.lifePoints = (potter.lifePoints - ronald.dmg)
                    } else {
                        potter.lifePoints = (potter.lifePoints + ronald.heal)
                        if (potter.lifePoints > 100) potter.lifePoints = 100
                    }
                    ministerio[fila][columna] = potter
                    println("Harry espero que no hagas llorar a mi hermana.")
                } else {
                    println("Harry espera no encuentro mi varita, ayudame a buscarla.")
                    (ministerio[fila][columna]) = ronald
                }

            else -> {
                println("Por fin un paso seguro")
                ministerio[fila][columna] = potter
            }
        }
    }

    private fun limitTestin(posicion: Array<Int>, direccion: String): Boolean {
        if (posicion[0] == 0 && direccion == "W"
            ||
            posicion[0] == 5 && direccion == "S"
            ||
            posicion[1] == 0 && direccion == "A"
            ||
            posicion[1] == 5 && direccion == "D"
        ) {
            println("Recuerda que no podemos salir del ministerio hasta que destruyamos los horrocruxes")
            return true
        } else {
            return false
        }
    }


    private fun informe() {
        if (potter.lifePoints < 0) potter.lifePoints = 0
        println(
            "Resumen de la aventura:" +
                    "\nMe quedan ${potter.lifePoints} puntos de vida" +
                    "\n$horrocrux" +
                    "\nHe matado ${mortifago.muertos} mortifagos"
        )
    }


    fun accioPartida() {
        println("Bienvenido Potter al Ministerio de Magia")

        initMinisterio()
        do {
            println()
            imprimirMinisterio()
            println()
            moverPotterBuffer()
            println()
            informe()

        } while (horrocrux.destruidos < 7 && potter.lifePoints > 0)

        imprimirMinisterio()
        println("Este es el final de mi aventura")
        if (potter.lifePoints == 0) {
            println("Harry ha caido ante el señor tenebroso")
        }
        if (horrocrux.destruidos == 7) {
            println("Harry ha destruido los 7 horrocruxes y asi acabado con el señor tenebroso")
        }
        informe()

    }


    private fun printMinisterio(fila: Int, columna: Int) {

        for (i in ministerioBuffer.indices) {
            for (j in ministerioBuffer[i].indices) {
                if(i==fila && j==columna) ministerioBuffer[i][j]=potter
                when (ministerioBuffer[i][j]) {

                    potter -> print("[\uD83C\uDF29]")
                    casilla -> print("[??]")
                    null -> print("[  ]")
                }

            }
            println()
        }

    }


    private fun imprimirMinisterio() {
        for (i in ministerio.indices) {
            for (j in ministerio[i].indices) {
                when (ministerio[i][j]) {
                    mortifago -> print("[\uD83D\uDC0D]")
                    potter -> print("[\uD83C\uDF29]")
                    voldemort -> print("[\uD83D\uDC43]")
                    bellatrix -> print("[\uD83D\uDDE1]")
                    horrocrux -> print("[\uD83D\uDC8E]")
                    mcGonagall -> print("[\uD83D\uDC31\u200D\uD83D\uDC64]")
                    hermaioni -> print("[\uD83E\uDDE0]")
                    ronald -> print("[\uD83E\uDD55]")
                    else -> print("[  ]")
                }
            }
            println()
        }
    }

}