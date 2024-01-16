package org.example.models

const val MAX_MORTIFAGOS=6
const val MAX_HORROCRUXES=7

/**
 * @property ministerio es la matriz sobre la que ocurriran las acciones
 * @property mortifago referencia a la clase Mortifago
 * @see Mortifago
 * @property potter referencia a la clase Harry
 * @see Harry
 * @property voldemort referencia  a la clase Voldemort
 * @see Voldemort
 * @property bellatrix referencia a la clase Bellatrix
 * @see Bellatrix
 * @property horrocrux referencia a la clase Horrocrux
 * @see Horrocrux
 * @property mcGonagall referencia a la clase McGonagall
 * @see McGonagall
 * @property ronald referencia a la clase Ronald
 * @see Ronald
 * @property hermaioni referencia a la clase Hermaioni
 * @see Hermaioni
 * @property casilla referencia a la clase Casilla
 * @see Casilla
 * @property ministerioBuffer es la matriz sobre la que se realizara la impresion.
 * @author Javier Ruiz
 * @since 1.0.0
 */
class Ministerio {
    private var ministerio: Array<Array<Casilla?>> = Array(6){Array (6) {null}}
    private val mortifago=Mortifago()
    private val potter=Harry()
    private val voldemort=Voldemort()
    private val bellatrix=Bellatrix()
    private val horrocrux=Horrocrux()
    private val mcGonagall=McGonagall()
    private val ronald=Ronald()
    private val hermaioni=Hermaioni()
    private val casilla=Casilla()
    private var ministerioBuffer: Array<Array<Casilla?>> = Array(6){Array (6) {casilla}}

    /**
     * Es la funcion que usaremos para colocar a Potter en la matriz
     * @author Javier Ruiz
     * @since 1.0.0
     */
    private fun placePotter(){
    ministerio[0][0]=potter
    ministerioBuffer[0][0]=potter
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


    /**
     * Es la funcion con la que colocaremos los personajes principales en la matriz.
     * @param list es un array de personajes para la inclusion en la matriz
     * @author Javier Ruiz
     * @since 1.0.0
     */
    private fun placeRandom(){
        val list: Array<Personaje?> = Array (5) {null}
        list[0] = voldemort
        list[1] = bellatrix
        list[2] = mcGonagall
        list[3] = hermaioni
        list[4] = ronald
        var filaRandom: Int
        var columnaRandom: Int
        var pos=-1
        for (i in list){
            pos++
            do{
                filaRandom=(0..5).random()
                columnaRandom=(0..5).random()
                if(ministerio[filaRandom][columnaRandom]==null){
                    ministerio[filaRandom][columnaRandom]=list[pos]
                    break
                }
            }while(ministerio[filaRandom][columnaRandom]!=null)
        }
    }


    /**
     * Es la funcion que usaremos para colocar los horrocruxes
     * @author Javier Ruiz
     * @since 1.0.0
     */
    private fun placeHorrocrux(){
        var cont=0
        var filaRandom: Int
        var columnaRandom: Int
        do{
            filaRandom=(0..5).random()
            columnaRandom=(0..5).random()
            if(ministerio[filaRandom][columnaRandom]==null){
                ministerio[filaRandom][columnaRandom]=horrocrux
                cont++
            }
        }while(cont< MAX_HORROCRUXES)
    }


    /**
     * Es la funcion con la que inicializaremos la matriz
     * @author Javier Ruiz
     * @since 1.0.0
     */
    private fun initMinisterio(){
        var cont=0
        var filaRandom: Int
        var columnaRandom: Int
        placePotter()
        placeRandom()
        //placeVoldemort()
        //placeBellatrix()
        placeHorrocrux()
        do{
            filaRandom=(0..5).random()
            columnaRandom=(0..5).random()
            if(ministerio[filaRandom][columnaRandom]==null){
                ministerio[filaRandom][columnaRandom]=mortifago
                cont++
            }
        }while(cont<MAX_MORTIFAGOS)

    }


    /**
     * Es la funcion que movera aleatoriamente a Bellatrix y Voldemort
     * @author Javier Ruiz
     * @since 1.0.0
     */
    private fun randomMovement(enemigo: Enemigo){
        var randomFila: Int
        var randomColumna: Int
        do{
            randomFila=(0..5).random()
            randomColumna=(0..5).random()
            ministerio[randomFila][randomColumna]
        }while(ministerio[randomFila][randomColumna]!=null || ministerioBuffer[randomFila][randomColumna]==casilla)
            ministerio[randomFila][randomColumna]=enemigo
            ministerioBuffer[randomFila][randomColumna]=enemigo
        println("Me muevo a ${randomFila+1} y ${randomColumna+1}")
    }


    /**
     * Es la funcion que realizara el movimiento dirigido de potter.
     * @author Javier Ruiz
     * @since 1.0.0
     */
    private fun moverPotter(){
        println("Hacia que direccion nos debemos mover Harry")
        val posicion = potter.posicion
       printMinisterio()
        var exit=true
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
                     if(ministerioBuffer[posicion[0]][posicion[1]] == potter) {
                         ministerioBuffer[posicion[0]][posicion[1]] = null
                     }
                     if(ministerio[posicion[0]][posicion[1]] == potter) {
                         ministerio[posicion[0]][posicion[1]] = null
                     }
                      exit=limitTestin(posicion, direccion)
                     when (direccion) {
                         "W" -> if(posicion[0] > 0) posicion[0]--
                         "S" -> if(posicion[0] < 5) posicion[0]++
                         "A" -> if(posicion[1] > 0) posicion[1]--
                         "D" -> if(posicion[1] < 5) posicion[1]++
                     }
                 }
             }while(exit)

        val nuevaFila = posicion[0]
        val nuevaColumna = posicion[1]

        interacciones(nuevaFila, nuevaColumna)
    }


    /**
     * Es la funcion que realizara las interacciones entre personajes
     * @author Javier Ruiz
     * @since 1.0.0
     */
    private fun interacciones(fila: Int, columna: Int){
        var acierto: Boolean
        when(ministerio[fila][columna]){
            voldemort ->
                if (voldemort.atacar()){
                    potter.lifePoints=(potter.lifePoints-voldemort.dmg)
                    randomMovement(voldemort)
                    ministerio[fila][columna] = potter
                    ministerioBuffer[fila][columna]=potter
                }else{
                    randomMovement(voldemort)
                    ministerio[fila][columna] = potter
                    ministerioBuffer[fila][columna]=potter
                }

            mortifago ->
                do{
                    if(mortifago.atacar()){
                        potter.lifePoints=(potter.lifePoints-mortifago.dmg)
                    }
                    acierto=potter.atacar()
                    if(acierto){
                        mortifago.muertos++
                        ministerio[fila][columna] = potter
                        ministerioBuffer[fila][columna]=potter
                    }
                }while(!acierto)

            bellatrix ->
                if (bellatrix.atacar()){
                    potter.lifePoints=(potter.lifePoints-bellatrix.dmg)
                    randomMovement(bellatrix)
                    ministerio[fila][columna] = potter
                    ministerioBuffer[fila][columna]=potter
                }else{
                    randomMovement(bellatrix)
                    ministerio[fila][columna] = potter
                    ministerioBuffer[fila][columna]=potter
                }
            horrocrux ->
            {horrocrux.destruirHorrocruxes()
            ministerio[fila][columna] = potter
            ministerioBuffer[fila][columna]=potter}

            mcGonagall ->
                if(potter.lifePoints<100) {
                    potter.lifePoints=(potter.lifePoints+mcGonagall.heal)
                    ministerio[fila][columna] = potter
                    ministerioBuffer[fila][columna]=potter
                    if(potter.lifePoints>100) potter.lifePoints=100
                    println("Señor Potter proteja a sus amigos.")
                }else{
                    println("Señor Potter cuidese que el mal no perdona.")
                    mantenerSitio(fila, columna, mcGonagall)
                    //ministerio[fila][columna]=mcGonagall
                    //ministerioBuffer[fila][columna]=mcGonagall
                }
            hermaioni ->
                if(potter.lifePoints<100) {
                    potter.lifePoints=(potter.lifePoints+hermaioni.heal)
                    ministerio[fila][columna] = potter
                    ministerioBuffer[fila][columna]=potter
                    if(potter.lifePoints>100) potter.lifePoints=100
                    println("Harry te necesitamos a tope para destruir los horrocruxes restantes.")
                }else{
                    println("Pero Harry corre o nos cerraran la biblioteca.")
                    mantenerSitio(fila, columna, hermaioni)
                    //ministerio[fila][columna]=hermaioni
                    //ministerioBuffer[fila][columna]=hermaioni
                }
            ronald ->
                if(potter.lifePoints<100) {
                    if(ronald.liarla()){
                        potter.lifePoints=(potter.lifePoints-ronald.dmg)
                    }else{
                        potter.lifePoints=(potter.lifePoints+ronald.heal)
                        if(potter.lifePoints>100) potter.lifePoints=100
                    }
                    ministerio[fila][columna] = potter
                    ministerioBuffer[fila][columna]=potter
                    println("Harry espero que no hagas llorar a mi hermana.")
                }else{
                    println("Harry espera no encuentro mi varita, ayudame a buscarla.")
                    mantenerSitio(fila, columna, ronald)
                    //ministerio[fila][columna]=ronald
                    //ministerioBuffer[fila][columna]=ronald
                }

            else -> {println("Por fin un paso seguro")
            ministerio[fila][columna] = potter
            ministerioBuffer[fila][columna]=potter}
        }
    }

    /**
     * Es la funcion que realizara la comprobacion de que seguimos dentro de la matriz.
     * @author Javier Ruiz
     * @since 1.0.0
     */
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
        }else{
            return false
        }
    }


    /**
     * Funcion auxiliar para cuando los aliados no curan
     * @author Javier Ruiz
     * @since 1.0.0
     */
    fun mantenerSitio(fila: Int, columna: Int, personaje: Personaje) {
        ministerio[fila][columna]=personaje
        ministerioBuffer[fila][columna]=personaje
        return

    }


    /**
     * Funcion que nos da el resumen de puntos de vida, horrocruxes destruidos y mortifagos muertos
     * @author Javier Ruiz
     * @since 1.0.0
     */
    private fun informe(){
        if(potter.lifePoints<0) potter.lifePoints=0
        println("Resumen de la aventura:" +
                "\nMe quedan ${potter.lifePoints} puntos de vida" +
                "\n$horrocrux" +
                "\nHe matado ${mortifago.muertos} mortifagos")
    }


    /**
     * Funcion principal donde se realiza el programa.
     * @author Javier Ruiz
     * @since 1.0.0
     */
    fun accioPartida(){
        println("Bienvenido Potter al Ministerio de Magia")

        initMinisterio()
       // copyMinisterio(ministerio, ministerioBuffer)
        do{
            println()
            imprimirMinisterio()
            println()
            moverPotter()
            println()
            informe()

        }while(horrocrux.destruidos<7 && potter.lifePoints>0)

        imprimirMinisterio()
        println("Este es el final de mi aventura")
        if(potter.lifePoints==0){
            println("Harry ha caido ante el señor tenebroso")
        }
        if(horrocrux.destruidos==7){
            println("Harry ha destruido los 7 horrocruxes y asi acabado con el señor tenebroso")
        }
        informe()

    }


    /**
     * Funcion que imprime el ministerio en oculto.
     * @author Javier Ruiz
     * @since 1.0.0
     *
     */
    private fun printMinisterio() {

        for (i in ministerioBuffer.indices) {
            for (j in ministerioBuffer[i].indices) {


                when (ministerioBuffer[i][j]) {
                    mortifago -> print("[🐍]")
                    potter -> print("[👓]")
                    voldemort -> print("[👃]")
                    bellatrix -> print("[🗡️]")
                    horrocrux -> print("[💎]")
                    mcGonagall -> print("[🐈]")
                    hermaioni -> print("[🧠]")
                    ronald -> print("[🥕]")
                    casilla -> print("[??]")
                    else -> print("[  ]")
                }

            }
            println()
        }

    }

    /**
     * Funcion que imprime el ministerio en vista por si hay que comprobar o es el final de la partida.
     * @author Javier Ruiz
     * @since 1.0.0
     */
    private fun imprimirMinisterio(){
        for (i in ministerio.indices) {
            for (j in ministerio[i].indices) {
                when (ministerio[i][j]) {
                    mortifago -> print("[🐍]")
                    potter -> print("[👓]")
                    voldemort -> print("[👃]")
                    bellatrix -> print("[🗡️]")
                    horrocrux -> print("[💎]")
                    mcGonagall -> print("[🐈]")
                    hermaioni -> print("[🧠]")
                    ronald -> print("[🥕]")
                    else -> print("[  ]")
                }
            }
            println()
        }
    }

}