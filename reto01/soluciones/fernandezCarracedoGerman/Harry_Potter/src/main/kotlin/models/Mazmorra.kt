package org.example.models

//import org.example.models.*
import org.lighthousegames.logging.logging

private const val TAM_MAZMORRA = 6 // Tamaño de la mazmorra
private const val NUM_HORROCRUXES = 7 // Número de horrocruxes a destruir
private const val NUM_DEMENTORES = 6 // Número de dementores iniciales

/**
 * Clase que representa la mazmorra o tablero donde se desarrolla el juego
 * @property mazmorra Array<Array<Celda>> Matriz que representa el tablero de juego
 * @property mazmorraBuffer Array<Array<Celda>> Matriz temporal para usar durante la acción del juego
 * @property harry Objeto que representa al protagonista del juego
 * @property activoMcgonagall Indica false si hemos utilizado ya la curación del personaje McGonagall
 * @property activaHermione Indica false si hemos utilizado ya la curación del personaje Herminione
 * @property activoRon Indica false si hemos utilizado ya la curación del personaje Ron
 * @property horrocruxesEncontrados Número de Horrocruxes que hemos destruido ya
 * @property dementoresMuertos Número de Dementores que hemos eliminado
 * @property direccion Dirección en la que se moverá Harry Potter
 * @property posicion Posición actual de Harry Potter en el tablero de la mazmorra
 * @property finalizar Acción decidida por el usuario cuando se le solicita elegir
 * @since 1.0
 * @see inicializarMazmorra
 * @see jugar
 * @see accionJuego
 * @see imprimirMazmorra
 */
class Mazmorra {

    private val log = logging()

    private var mazmorra = Array(TAM_MAZMORRA) {Array(TAM_MAZMORRA) { Celda() } } // relleno de celdas vacías
    private var mazmorraBuffer = Array(TAM_MAZMORRA) {Array(TAM_MAZMORRA) { Celda() } } // relleno de celdas vacías
    private val harry = Harry_Potter()

    private var activoMcgonagall:Boolean = true
    private var activaHermione:Boolean = true
    private var activoRon:Boolean = true

    private var horrocruxesEncontrados: Int = 0
    private var dementoresMuertos:Int = 0

    private var direccion:Direccion = Direccion.SUR // inicialmente nos da igual porque lo pediremos al usuario
    private var posicion:Posicion = Posicion (0,0) // inicialmente aparecemos en esquina superior izquierda

    private var finalizar:Accion = Accion.CONTINUAR

    init {
        log.debug { "Inicializando Mazmorra" }

        mazmorra[posicion.fila][posicion.columna].contenido = harry
        mazmorra[posicion.fila][posicion.columna].visitada = true

        inicializarMazmorra()

    }

    /**
     * Muestra la mazmorra en pantalla con los iconos del objeto que haya en cada celda, y según esta haya sido visitada o no 
     */
    private fun imprimirMazmorra(){

        println("🧱" + "🧱".repeat(TAM_MAZMORRA) + "🧱")
        for (i in mazmorra.indices) {
            print("🧱")
            for (j in mazmorra[i].indices) {
                if (mazmorra[i][j].visitada) {
                    when (mazmorra[i][j].contenido) {
                        is Harry_Potter -> print((mazmorra[i][j].contenido as Harry_Potter).icono)// print(" P ")
                        is McGonagall -> print((mazmorra[i][j].contenido as McGonagall).icono) // print(" M ")
                        is Hermione -> print((mazmorra[i][j].contenido as Hermione).icono) // print(" H ")
                        is Ron -> print((mazmorra[i][j].contenido as Ron).icono) // print(" R ")
                        is Voldemort -> print((mazmorra[i][j].contenido as Voldemort).icono)// print(" V ")
                        is Bellatrix -> print((mazmorra[i][j].contenido as Bellatrix).icono)// print(" B ")
                        is Dementor -> print((mazmorra[i][j].contenido as Dementor).icono) // print(" D ")
                        is Horrocrux -> print((mazmorra[i][j].contenido as Horrocrux).icono) // print(" X ")
                        else -> print(mazmorra[i][j].iconoSuelo)
                    }
                } else { print(mazmorra[i][j].iconoOscuridad)}
            }
            println("🧱")
        }
        println("🧱" + "🧱".repeat(TAM_MAZMORRA) + "🧱")
        //log.debug { "Mazmorra impresa" }
        println()

    }

    /**
     * Coloca aleatoriamente todos los objetos y personajes por la mazmorra
     */
    private fun inicializarMazmorra(){

        repeat(NUM_HORROCRUXES) { posicionarAleatoriamente(Horrocrux(),mazmorra) }
        repeat(NUM_DEMENTORES) { posicionarAleatoriamente(Dementor(), mazmorra) }

        posicionarAleatoriamente(McGonagall(), mazmorra)
        posicionarAleatoriamente(Hermione(), mazmorra)
        posicionarAleatoriamente(Ron(), mazmorra)

        posicionarAleatoriamente(Voldemort(), mazmorra)
        posicionarAleatoriamente(Bellatrix(), mazmorra)

        log.debug { "Elementos posicionados => Mazmorra inicializada." }

    }

    /**
     * Gestiona el inicio del juego, su desarrollo y muestra la información final cuando este acaba
     */
    fun jugar(){

        println("🏰 Harry Potter en la Mazmorra Encantada de Azkaban 🏰")

        do {

            mazmorraBuffer = copiarMazmorra(mazmorra, mazmorraBuffer)
            accionJuego()
            mostrarEstadísticas()
            mazmorra = copiarMazmorra(mazmorraBuffer, mazmorra)

        } while (harry.puntosVida > 0 && horrocruxesEncontrados < NUM_HORROCRUXES && finalizar == Accion.CONTINUAR)

        println()

        if (finalizar == Accion.SALIR) {
            println("¡Partida abortada! Vuelve cuando estés preparado...")
        } else {
            println("🏰 ¡Fin del juego! 🏰")
            if (harry.puntosVida <= 0) {
                println("😥 ¡Harry ha muerto! 😥")
                harry.puntosVida = 0
            } else {
                println("🤩 ¡Harry ha sobrevivido y ha logrado destruir los horrocruxes! 🤩")
            }
            mostrarEstadísticas()
            imprimirMazmorra()
        }
    }

    /**
     * Muestra los valores de los indicadores principales del juego
     */
    private fun mostrarEstadísticas() {
        println("Vida Harry: ${harry.puntosVida}")
        println("Horrocruxes encontrados: $horrocruxesEncontrados")
        println("Dementores muertos: $dementoresMuertos")
        print("Utilizada ayuda de Amigos: ")
        print("McGonagall[${if (!activoMcgonagall) "X" else " "}] ")
        print("Herminione[${if (!activaHermione) "X" else " "}] ")
        println("Ron[${if (!activoRon) "X" else " "}] ")
    }

    /**
     * Pregunta al usuario por teclado la dirección a tomar (N/S/E/O). Incluye la opción X para finalizar el juego
     * @return Direccion Dirección elegida, o X si se eligió abandonar la partida
     */
    private fun preguntarDireccion():Direccion{
        var correcto = false
        val regex=Regex("^[NSEOX]\$")
        var input:String

        println("Elige tu siguiente movimiento: ")
        do {
            println("N => Norte")
            println("S => Sur")
            println("E => Este")
            println("O => Oeste\n")
            println("X => FINALIZAR JUEGO\n")
            print("Introduce Dirección: ")
            input=readln().uppercase()
            correcto = regex.matches(input)
            if (!correcto) {
                println("Error (solo \"N\" o \"S\" o \"E\" o \"O\" o \"X\").")
            }

        } while (!correcto)

        return when (input){
            "N"->Direccion.NORTE
            "S"->Direccion.SUR
            "E"->Direccion.ESTE
            "O" ->Direccion.OESTE
            else -> Direccion.SALIR
        }
    }

    /**
     * Pregunta al usuario por teclado si decide atacar o defenderse cuando se encuentra un enemigo
     * @return Accion Acción elegida
     */
    private fun preguntarAtacarODefenderse():Accion {
        var resp: String ="N"
        println("¡¡Te encuentras con un ememigo!!")
        do {
            print("¿Atacar o Defenderte (A/D)? ")
            resp = readln().uppercase()
        } while (resp != "A" && resp != "D")

        return (if (resp == "A") Accion.ATACAR else Accion.DEFENDERSE)
    }

    /**
     * Gestión de la acción principal del movimiento de Harry.
     *
     * Mostramos el contenido de las casillas adyacentes a la posición actual.
     * Preguntamos la dirección a tomar y avanzamos a la celda, dentro de los límites de la mazmorra, según esa dirección, comprobamos qué
     * objeto o personaje tiene y se produce la acción correspondiente.
     *
     * Tras esta acción se moverá (o no) a Harry según contenido de la celda, acción elegida y su resultado.
     */
    private fun accionJuego() {
        val nuevaPosicion = Posicion(posicion.fila, posicion.columna)
        val posicionInicialHarry = Posicion(posicion.fila, posicion.columna)
        var direccionCorrecta:Boolean = false
        var direccionActual:Direccion
        var harryEligeAccion:Accion = Accion.ATACAR
        var ataqueHarryExitoso:Boolean = false
        var moverse:Boolean = false

        do {
            marcarVisitadasAdyacentes(posicion)
            imprimirMazmorra()

            direccionActual = preguntarDireccion()
            when (direccionActual) {
                Direccion.NORTE -> if (posicion.fila >= 0) nuevaPosicion.fila-- // Norte => decremento fila
                Direccion.SUR -> if (posicion.fila <= TAM_MAZMORRA - 1) nuevaPosicion.fila++ // Sur => incremento fila
                Direccion.ESTE -> if (posicion.columna <= TAM_MAZMORRA - 1) nuevaPosicion.columna++ // Este => incremento columna
                Direccion.OESTE -> if (posicion.columna >= 0) nuevaPosicion.columna-- // Oeste => decremento columna
                Direccion.SALIR -> {
                    finalizar = Accion.SALIR
                    return
                }
            }

            if (nuevaPosicion.fila !in mazmorra.indices || nuevaPosicion.columna !in mazmorra.indices) {
                direccionCorrecta = false
                println("¡¡No puedes ir por ahí, hay una pared!!")
                // no me muevo
                nuevaPosicion.fila = posicion.fila
                nuevaPosicion.columna = posicion.columna
            } else {
                direccionCorrecta = true
                println("Nueva dirección, nos movemos al $direccionActual")
                // me muevo
                posicion.fila = nuevaPosicion.fila
                posicion.columna = nuevaPosicion.columna
            }

        } while (!direccionCorrecta)

        mazmorraBuffer[posicion.fila][posicion.columna].visitada = true

        // Vemos qué nos encontramos en la celda
        when (mazmorraBuffer[posicion.fila][posicion.columna].contenido){
            is Horrocrux -> {
                println("🎆 ¡¡¡Harry encuentra un Horrocrux!!! 🎆")
                mazmorraBuffer[posicion.fila][posicion.columna].contenido = null
                horrocruxesEncontrados++
                moverse = true
            }
            is Enemigo -> {
                val enemigo:Enemigo = mazmorraBuffer[posicion.fila][posicion.columna].contenido as Enemigo
                enemigo.identificarse()
                harryEligeAccion = preguntarAtacarODefenderse()
                if (harryEligeAccion == Accion.ATACAR) {
                    ataqueHarryExitoso = harry.atacar()
                    if (ataqueHarryExitoso) {
                        println("¡¡¡Harry lanza un hechizo con éxito!!!")
                        mazmorraBuffer[posicion.fila][posicion.columna].contenido = null // el enemigo morirá o saltará
                        moverse = true
                        when (enemigo) {
                            is Dementor -> { dementoresMuertos++ } // solo desaparece
                            is Voldemort -> { // salta
                                println("¡¡¡Voldemort huye!!!")
                                saltarEnemigoHuido(Voldemort(), mazmorraBuffer)
                            }
                            else -> { // salta
                                println("¡¡¡Bellatrix huye!!!")
                                saltarEnemigoHuido(Bellatrix(), mazmorraBuffer)
                            }
                        }
                    } else {
                        println("¡¡¡Harry ha fallado!!!")
                        if ((0..100).random()<harry.probabilidadSerAtacado) {
                            enemigo.atacar(harry)
                        } else { println("¡¡El enemigo no ataca!!") }
                        moverse = false
                    }
                } else {
                    harry.defenderse()
                    moverse = false
                }
            }
            is Amigo -> {
                when (val amigo = mazmorraBuffer[posicion.fila][posicion.columna].contenido as Amigo){
                    is Ron -> {
                        (amigo as Ron).curar(harry)
                        activoRon = false
                    }
                    is McGonagall -> {
                        amigo.curar(harry)
                        activoMcgonagall = false
                    }
                    is Hermione -> {
                        amigo.curar(harry)
                        activaHermione = false
                    }
                }
                mazmorraBuffer[posicion.fila][posicion.columna].contenido = null // amigo desaparece después de curar
                moverse = true
            }

            else -> {
                println("No había nada en la casilla")
                moverse = true
            }

        }

        // en los casos en que debemos movernos, vaciamos la casilla donde iniciamos el movimiento
        // y en los que no, deshacemos la actualización de posición
        if (moverse) {
            mazmorraBuffer[posicionInicialHarry.fila][posicionInicialHarry.columna].contenido = null
            mazmorraBuffer[posicionInicialHarry.fila][posicionInicialHarry.columna].visitada = true
        } else {
            posicion.fila = posicionInicialHarry.fila
            posicion.columna = posicionInicialHarry.columna
        }

        // Colocamos a Harry en la posición que corresponda, tanto si nos hemos movido como si no
        mazmorraBuffer[posicion.fila][posicion.columna].contenido = harry
        mazmorraBuffer[posicion.fila][posicion.columna].visitada = true
    }

    /**
     * Marca como visitadas las celdas adyacentes (que estén dentro de los límites de la mazmorra)
     * a la posición pasada por parámetro
     * @param posicion Posicion Posición para la que se marcarán como visitadas sus adyacentes
     */
    private fun marcarVisitadasAdyacentes(posicion:Posicion) {
        for (i in posicion.fila - 1..posicion.fila + 1)
            for (j in posicion.columna - 1..posicion.columna + 1) {
                if (i in mazmorra.indices && (j in mazmorra.indices)) mazmorra[i][j].visitada = true
            }
    }

    /**
     * Genera una posición aleatoria válida (no ocupada) dentro de los límites de la mazmorra
     * @param miMazmorra Mazmorra en la que buscamos la posición aleatoria libre
     * @return Posicion Posición aleatoria encontrada
     */
    private fun generarPosicionAleatoria(miMazmorra: Array<Array<Celda>>):Posicion{
        var fila: Int
        var columna: Int

        // Generamos posiciones aleatorias hasta que encontremos una vacía
        do {
            fila = (0 until TAM_MAZMORRA).random()
            columna = (0 until TAM_MAZMORRA).random()
        } while (miMazmorra[fila][columna].contenido != null)

        return Posicion(fila,columna)
    }

    /**
     * Colocamos el elemento pasado por parámetro en el objeto contenido de una celda aleatoria de la mazmorra
     * @param elemento Any? Elemento a colocar como contenido en la celda aleatoria
     * @param miMazmorra Array<Array<Celda>> Mazmorra en la que buscaremos la posición aleatoria y colocaremos el elemento
     * @see generarPosicionAleatoria
     * @see saltarEnemigoHuido
     */
    private fun posicionarAleatoriamente(elemento:Any?, miMazmorra: Array<Array<Celda>>){
        val posicion:Posicion = generarPosicionAleatoria(miMazmorra)
        miMazmorra[posicion.fila][posicion.columna].contenido = elemento
        miMazmorra[posicion.fila][posicion.columna].visitada = false
        log.debug { "elemento posicionado en [${posicion.fila}][${posicion.columna}]" }
    }

    /**
     * Colocamos el elemento pasado por parámetro en el objeto contenido de una celda aleatoria de la mazmorra
     * pero no modificamos la visibilidad de la celda. Si el enemigo huye a una posición visitada, esta permanecerá visitada
     * y si estaba oculta, permanecerá igualmente oculta.
     * @param elemento Any? Elemento a colocar como contenido en la celda aleatoria
     * @param miMazmorra Array<Array<Celda>> Mazmorra en la que buscaremos la posición aleatoria y colocaremos el elemento
     * @see generarPosicionAleatoria
     * @see posicionarAleatoriamente
     */private fun saltarEnemigoHuido(enemigo:Enemigo, miMazmorra: Array<Array<Celda>>){
        val posicion:Posicion = generarPosicionAleatoria(miMazmorra)
        miMazmorra[posicion.fila][posicion.columna].contenido = enemigo
        log.debug { "Elemento ${enemigo.nombre} ha huído a [${posicion.fila}][${posicion.columna}]" }
    }

    /**
     * Copia la mazmorra origen sobre una mazmorra destino pasada también por parámetro, copiando celda a celda
     * @param origen Array<Array<Celda>> Mazmorra desde la que copiaremos
     * @param destino Array<Array<Celda>> Mazmorra donde colocaremos las celdas copiadas
     * @return Array<Array<Celda>> Matriz Mazmorra de destino con las celdas copiadas
    */
    private fun copiarMazmorra(origen: Array<Array<Celda>>, destino: Array<Array<Celda>>): Array<Array<Celda>> {
        for (i in origen.indices) {
            for (j in origen[i].indices) {
                destino[i][j] = origen[i][j]
            }
        }
        return destino
    }

}

