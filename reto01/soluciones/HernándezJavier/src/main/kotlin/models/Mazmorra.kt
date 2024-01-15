package models

/**
 * La mazmorra donde se dearrolla la acción del juego
 * @property mapSize el tamaño de la matriz
 * @property numHorro el número de Horrocruxues de la matriz
 * @property numDementors el número de Dementores de la matriz
 * @author javierhvicente
 */
class Mazmorra (val mapSize: Int = 6, val numHorro: Int = 7, val numDementors: Int = 6) {
    private var mazmorra = Array(mapSize) { arrayOfNulls<Any>(mapSize) }
    val harry = Harry()
    private val horrocurxes = Horrocurxes()
    private val dementor = Dementor()
    private val voldemort = Voldemort()
    private val bellatrix = Bellatrix()
    private val mcGonall = McGonall()
    private val hermione = Hermione()
    private val ron = Ron()
    var horrDestruidos: Int = 0
    var dementoresEjecutados:Int = 0

    init {
        println("Bienvenido Harry")
        mazmorra[harry.fila][harry.columna] = Harry()
        mazmorra = inicializarMazmorra(mazmorra)
    }

    /**
     * La ejecución del juego
     */
    fun aventura() {
        do {
            printCamino(mazmorra)
            juego()
            println("$horrDestruidos Horrocruxes destruidos")
            println("${harry.vida} puntos de vida")
            println("Dementores ejecutados: $dementoresEjecutados")
        } while (horrDestruidos < 7 && harry.vida > 0)
        if (harry.vida <= 0) {
            println("Harry Potter ha muerto en combate")
        }else{
            println("Harry ha conseguido salir de la mazmorra con vida y ha cunplido su misión de acabar con los siete Horrocruxes")

        }

    }

    /**
     * Acción que se lleva a cabo cuando Harry coincide con los demás objetos en la mazmorra
     * @param fila la fila en la que se encuentra Harry
     * @param columna la columna en la que se encuentra Harry
     */
    private fun accion(fila: Int, columna: Int) {
        when (mazmorra[fila][columna]) {
            is Voldemort -> voldemort.interacción(this, mazmorra, fila, columna)
            is Bellatrix -> bellatrix.interacción(this, mazmorra, fila, columna)
            is Dementor -> dementor.interaccion(this, mazmorra, fila, columna)
            is Horrocurxes -> horrocurxes.interracción(this, mazmorra, fila, columna)
            is McGonall -> mcGonall.interacción(this, mazmorra, fila, columna)
            is Hermione -> hermione.interacción(this , mazmorra, fila, columna)
            is Ron -> ron.interacción(this, mazmorra, fila, columna)
            true -> mazmorra[fila][columna] = Harry()
            null -> mazmorra[fila][columna] = Harry()
        }
    }

    /**
     * Permite a Harry moverse por la mazmorra y llevar a cabo la interacción con los demás objetos
     */
    private fun juego() {
        var nuevaFila = harry.fila
        var nuevaCol = harry.columna
        println("¿Harry hacia donde quieres ir?")
        println(
            "N" +
                    "\nS" +
                    "\nW" +
                    "\nE"
        )
        val direccion = readln().trim().uppercase()
        if (direccion != "N" && direccion != "S" && direccion != "W" && direccion != "E") {
            println("Solo puedes moverte en las direcciones N, S, W, E.")
        } else {
            if (mazmorra[harry.fila][harry.columna] is Harry) {
                mazmorra[harry.fila][harry.columna] = true
            }
            when (direccion) {
                "N" -> if (harry.fila > 0) {
                    nuevaFila--
                }

                "S" -> if (harry.fila < mazmorra.size - 1) {
                    nuevaFila++
                }

                "W" -> if (harry.columna > 0) {
                    nuevaCol--
                }

                "E" -> if (harry.columna < mazmorra.size - 1) {
                    nuevaCol++
                }
            }
        }
        harry.fila = nuevaFila
        harry.columna = nuevaCol
        accion(harry.fila, harry.columna)
    }

    /**
     * Inicializa los objetos necesarios para el funcionamiento del juego en la mazmorra
     * @param mazmorra una matriz de Any donde se lleva a cabo el juego
     * @return la mazmorra con los objetoos inicializados
     */
    private fun inicializarMazmorra(mazmorra: Array<Array<Any?>>): Array<Array<Any?>> {
        repeat(numHorro) {
            colocarPosAleatoria(Horrocurxes(), mazmorra)
        }
        repeat(numDementors) {
            colocarPosAleatoria(Dementor(), mazmorra)
        }
        colocarPosAleatoria(Voldemort(), mazmorra)
        colocarPosAleatoria(Bellatrix(), mazmorra)
        colocarPosAleatoria(McGonall(), mazmorra)
        colocarPosAleatoria(Hermione(), mazmorra)
        colocarPosAleatoria(Ron(), mazmorra)
        return mazmorra
    }

    /**
     * Coloca en una posición aleatoria de una matriz a un objeto
     * @param item el objeto que queremos colocar
     * @param mazmorra la matriz en la que queremos insertar el objeto
     * @return la mazmorra con el objeto introducido
     */
    private fun colocarPosAleatoria(item: Any, mazmorra: Array<Array<Any?>>): Array<Array<Any?>> {
        var fila: Int
        var columna: Int
        do {
            fila = (0..mazmorra.size - 1).random()
            columna = (0..mazmorra.size - 1).random()
        } while (mazmorra[fila][columna] != null)
        mazmorra[fila][columna] = item
        return mazmorra
    }

    /**
     * Imprime la matriz como si estuviera oculta, y solo muestra las posiciones por las que pasa Harry
     * @param mazmorra la matriz
     */
    private fun printCamino(mazmorra: Array<Array<Any?>>) {
        for (i in mazmorra.indices) {
            for (j in mazmorra[i].indices) {
                when (mazmorra[i][j]) {
                    is Harry -> print("[ \uD83E\uDD13 ]")
                    is McGonall ->{
                        if(!mcGonall.vista){
                            print("[ ? ]")
                        }else{
                            print("[ \uD83E\uDDD9 ]")

                        }
                    }
                    is Hermione ->{
                        if(!hermione.vista){
                            print("[ ? ]")
                        }else{
                            print("[ 🧝‍♀️ ]")

                        }
                    }
                    is Ron ->{
                        if(!ron.vista){
                            print("[ ? ]")
                        }else{
                            print("[ \uD83D\uDC68\u200D\uD83E\uDDB0 ]")

                        }
                    }
                    is Voldemort ->{
                        if(!voldemort.vista){
                            print("[ ? ]")
                        }else{
                            print("[ \uD83D\uDC7D ]")
                        }
                    }
                    is Bellatrix ->{
                        if(!bellatrix.vista){
                            print("[ ? ]")
                        }else{
                            print("[ \uD83D\uDC80 ]")
                        }
                    }
                    true -> print("[   ]")
                    else -> print("[ ? ]")
                }
            }
            println()
        }
        println()
    }
}



