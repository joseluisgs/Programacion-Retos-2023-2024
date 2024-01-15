package models

/**
 * @author Mario de Domingo
 * @property tamMundo es el tamaño que tiene la matriz
 * @property finJuego booleano que empieza en false y cambia cuando finaliza el juego
 */
class Mundo (private val tamMundo: Int = 6, var finJuego: Boolean = false){
    private val mundo = Array(tamMundo) { arrayOfNulls<String?>(tamMundo) }
    private val mcGonagal = McGonagal()
    private val bellatrix = Bellatrix()
    private val voldemort = Voldemort()
    private val horrocrux = Horrocrux()
    private val dementor = Dementor()
    private val hermione = Hermione()
    private val harry = Harry()
    private val ron = Ron()

    /**
     * inicialiazamos y llamamos a las funciones colocar e imprimir
     * iniciamos la vida de harry a 100
     * simulamos
     *
     * @author Mario de Domingo
     * @version 1.0-SNAPSHOT
     */
    fun inicializarMundo() {
        harry.vida = 100
        colocarPersonajes()
        imprimirMatriz()
        val i = 0
        val j = 0
        simular(i, j)
    }

    /**
     * comienza en la posicion 0.0 y nos movemos por la matriz, depende de la casilla te lleva a una funcion u otra
     * y la casilla por la que hemos pasado la igualamos a "Vacio"
     *
     * @author Mario de Domingo
     * @version 1.0-SNAPSHOT
     * @return un bucle con distintas posibilidades hatsa que finJuego = true
     */
    private fun simular(i: Int, j: Int) {
        var i1 = i
        var j1 = j
        while (!finJuego) {
            mundo[i1][j1] = "Vacio"
            print(
                """Seleccione la nueva dirección:
                |   W- Arriba.   S- Abajo.   A- Izquierda.   D- Derecha.
                |   -> Elección:""".trimMargin()
            )
            val direccion = readln()
            var direccionValida = true
            when (direccion) {
                "a" -> j1 > 0
                "s" -> i1 < 5
                "d" -> j1 < 5
                "w" -> i1 > 0
                else -> direccionValida = false
            }
            if (direccionValida) {
                when (direccion) {
                    "a" -> if (j1 > 0) j1-- else println("¡DIRECCION NO VALIDA! Se sale de la Mazmorra")
                    "s" -> if (i1 < 5) i1++ else println("¡DIRECCION NO VALIDA! Se sale de la Mazmorra")
                    "d" -> if (j1 < 5) j1++ else println("¡DIRECCION NO VALIDA! Se sale de la Mazmorra")
                    "w" -> if (i1 > 0) i1-- else println("¡DIRECCION NO VALIDA! Se sale de la Mazmorra")
                    else -> println("¡DIRECCION NO VALIDA!")
                }
                funcionCasilla(i1, j1)
                mundo[i1][j1] = harry.nombre
                imprimirMatriz()
                println("Has conseguido ${horrocrux.conseguidos} Horrocrux")
                println("____________________________________________________________________")
            } else {
                println("¡DIRECCION NO VALIDA!")
            }
            harry.vida()
        }
        println()
        println(
            """_________________________________________
            |           ¡¡¡FIN DEL JUEGO!!!
            |_________________________________________
        """.trimMargin()
        )
    }

    /**
     * cada casilla en la que caes tiene una funcion distinta, con un when te devuelve que pasa segun el valor
     * que encuentres en la casilla
     *
     * @author Mario de Domingo
     * @version 1.0-SNAPSHOT
     * @return el resultado final de la vida de Harry y otros valores segun la casilla en la que estes
     */
    private fun funcionCasilla(i: Int, j: Int) {
        when (mundo[i][j]) {
            horrocrux.nombre -> horrocrux.casilla()
            hermione.nombre -> {
                hermione.ayudar()
                harry.variarVida(hermione.puntosVida)
            }
            mcGonagal.nombre -> {
                mcGonagal.ayudar()
                harry.variarVida(mcGonagal.puntosVida)
            }
            ron.nombre -> {
                ron.ayudar()
                harry.variarVida(ron.valor)
            }
            voldemort.nombre -> {
                voldemort.casilla()
                if (horrocrux.conseguidos == horrocrux.numHorrocrux){voldemort.atacar()}
                else { voldemort.perder() }
                harry.variarVida(voldemort.valor)
                }
            bellatrix.nombre -> {
                bellatrix.casilla()
                harry.variarVida(bellatrix.valor)
            }
            dementor.nombre -> {
                dementor.casilla()
                var opcion : String
                    do {
                        println("a-> Atacar    d-> Defenderse")
                        opcion = readln()
                        when (opcion) {
                            "a" -> atacar()
                            "d" -> defenderse()
                            else -> println("Opción no válida. Inténtalo de nuevo.")
                        }
                    } while (opcion != "a" && opcion != "d")
            }
        }
    }

    /**
     * @return posicion aleatoria que sea igual a null o "Vacio"
     * @author Mario de Domingo
     * @version 1.0-SNAPSHOT
     */
    fun casillaRandom(personaje: String) {
        var i: Int
        var j: Int
        do {
            i = (0 until tamMundo).random()
            j = (0 until tamMundo).random()
        } while (mundo[i][j] != null || mundo[i][j] == "Vacio")
        mundo[i][j] = personaje
    }

    /**
     * @return matrriz inicializada con todos los personajes colocados en posiciones aleatorias
     * @author Mario de Domingo
     * @version 1.0-SNAPSHOT
     */
    private fun colocarPersonajes() {
        mundo[0][0] = harry.nombre
        casillaRandom(bellatrix.nombre)
        casillaRandom(voldemort.nombre)
        casillaRandom(hermione.nombre)
        casillaRandom(ron.nombre)
        casillaRandom(mcGonagal.nombre)
        var dementoresPuestos = 0
        do {
            casillaRandom(dementor.nombre)
            dementoresPuestos++
        } while (dementoresPuestos != dementor.numDementores)
        var horrocruxPuestos = 0
        do {
            casillaRandom(horrocrux.nombre)
            horrocruxPuestos++
        } while (horrocruxPuestos != horrocrux.numHorrocrux)
    }

    /**
     * imprimimos la matriz en su estado actual
     *
     * @author Mario de Domingo
     * @version 1.0-SNAPSHOT
     * @return imprime la matriz con vacios y nulos admeas de harry
     */
    private fun imprimirMatriz() {
        println()
        for (i in mundo.indices) {
            for (j in mundo[i].indices) {
                when (mundo[i][j]) {
                    harry.nombre -> print("[🦉]")
                    /*bellatrix.nombre -> print("[🐍]")
                    dementor.nombre -> print("[👻]")
                    voldemort.nombre -> print("[☠️]")
                    hermione.nombre -> print("[🪄]")
                    ron.nombre -> print("[🐭]")
                    mcGonagal.nombre -> print("[🐈‍⬛]")
                    horrocrux.nombre -> print("[🪙]")*/
                    "Vacio" -> print("[◻️]")
                    null -> print("[❎]")
                    else -> print("[❎]")
                }
            }
            println()
        }
        println()
        println("Quedan ${dementor.numDementores} Dementores")
        harry.vida()
    }

    /**
     * @return el resultado de atacar a dementor
     * @author Mario de Domingo
     * @version 1.0-SNAPSHOT
     */
    private fun atacar() {
        val probExito = (1..2).random()
        if (probExito == 1) {
            println("¡Enhorabuena! Un ${dementor.nombre} menos")
            dementor.numDementores += -1
        } else {
            println("¡Has fallado! Pierdes 10 de vida y el ${dementor.nombre} se esconde")
            casillaRandom(dementor.nombre)
            harry.variarVida(dementor.danio)
        }
    }

    /**
     * @return nueva posicion para dementor
     * @author Mario de Domingo
     * @version 1.0-SNAPSHOT
     */
    private fun defenderse() {
        println("Te defiendes con un patronus🦌 y el ${dementor.nombre} se esfuma")
        casillaRandom(dementor.nombre)
    }
}
