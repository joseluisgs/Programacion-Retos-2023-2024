package models

const val NUM_HORROCUXES=7
const val NUM_DEMENTORES=6
const val FILA=6
const val COL=6


class Mazmorra {

    private val mazmorra: Array<Array<Celdas?>> = Array(FILA){Array(COL){null} }

    private val harry=Harry()
    private val voldemort=Voldemort()
    private val bellatrix=Bellatrix()
    private val dementor=Dementor()
    private val hermione=Hermione()
    private val mcgonagall=McGonagall()
    private val ron=Ron()
    private val horrocruxes=Horrocruxes()
    private val visitada=Visitada()

    /**
     * Es la funcion que realiza toda la simulacuion
     * @see imprimirMazmorraInt
     * @see colocarEnemigos
     * @see colocarAmigos
     * @see moverHarry
     * @see ataqueVoldemort
     * @see ataqueHorrocruxes
     * @see ataqueBellatrix
     * @see ataqueDementor
     * @see ayudaHermione
     * @see ayudaMcgonagall
     * @see ayudaRon
     * @see informe
     * @since version 1.0
     * @author Yahya el hadri el bakkali
     */
    fun iniciarMazmorra(){
        mazmorra[harry.fila][harry.col]=harry
        imprimirMazmorraInt()
        println("HAN APARECIDO ENEMIGOS Y AMIGOS")
        colocarHorrocruxes()
        colocarEnemigos()
        colocarAmigos()
       do {

           //imprimirMazmorra()
           println()
           imprimirMazmorraInt()
           moverHarry()
           when(mazmorra[harry.fila][harry.col]){
               voldemort->ataqueVoldemort()
               horrocruxes-> ataqueHorrocruxes()
               bellatrix->ataqueBellatrix()
               dementor->ataqueDementor()
               mcgonagall->ayudaMcgonagall()
               hermione->ayudaHermione()
               ron->ayudaRon()
               else->mazmorra[harry.fila][harry.col]=harry
           }
           informe()
       }while (harry.vida>0&&horrocruxes.numHorro!=0)
       println("EL JUEGO A TERMINADO")
        informe()
    }

    /**
     * Imprime la el array dependiendo de si la casila que recorre esta harry o es visitada
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun imprimirMazmorraInt() {
        for (i in mazmorra.indices){
            for (j in mazmorra[i].indices){
                when(mazmorra[i][j]){
                    harry-> print("[H]")
                    visitada-> print("[ ]")
                    else-> print("[?]")
                }
            }
            println()
        }
    }

    /**
     * Nos imprime por pantalla un informe general de las vidas de los horrocruxes,
     * los dementores vivos y la vida de harry
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun informe() {
        println("QUEDAN ${horrocruxes.numHorro} HORROCRUXES VIVOS")
        println("QUEDAN ${dementor.numDem} DEMENTORES VIVOS")
        println("TE QUEDAN ${harry.vida} PUNTOS DE VIDA")
    }

    /**
     * Si la casilla en la que se encuentra harry esta Ron realizamos un random
     * para ver si ron le ataca o le añade puntos de vida
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun ayudaRon() {
        println("TE HAS ENCONTRADO CON RON")
        if ((0..100).random()<30){
            println("RON TE HA ATACADO")
            harry.vida-=ron.daño
            mazmorra[harry.fila][harry.col]=harry
        }else{
            println("RON TE HA AYUDADO CON 20 PUNTOS")
            harry.vida+=ron.curar
            mazmorra[harry.fila][harry.col]=harry
        }
    }

    /**
     * Si la casilla en la que esta Harry esta hermione le añade puntos a harry.vida
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun ayudaHermione() {
        harry.vida+=hermione.curar
        mazmorra[harry.fila][harry.col]=harry
        println("TE HAS ENCONTRADO CON HERMIONE")
        println("HERMIONE TE A AYUDADO CON 30 PUNTOS")
    }

    /**
     * Si la casilla en la que esta harry esta McGonagall le añadimos vida a harry
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun ayudaMcgonagall() {
        harry.vida+=mcgonagall.curar
        mazmorra[harry.fila][harry.col]=harry
        println("TE HAS ENCONTRADO CON MCGONAGALL")
        println("MCGONAGALL TE A AYUDADO CON 70 PUNTOS")
    }

    /**
     * si Harry se encuentra con un dementor hacemos un random con el 50% de probabilidad de matarle
     * y si no le mato hacemos un randome con el 60% de probabiidad de que me ataque yluego desaparece
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun ataqueDementor() {
        println("TE HAS ENCONTRADO CON UN DEMENTOR")
        if ((0..100).random()<50){
            println("HAS MATADO UN DEMENTOR")
            dementor.numDem-=1
            mazmorra[harry.fila][harry.col]=harry
        }else {
            if ((0..100).random()<60){
                harry.vida -= dementor.daño
                println("UN DEMENTOR TE HA ATACADO Y DESAPARECE")
                mazmorra[harry.fila][harry.col]=harry
                dementor.numDem-=1
            }else{
                println("EL DEMENTOR NO A SUFRIDO DAÑO PERO DESAPARECE")
                dementor.numDem-=1
                mazmorra[harry.fila][harry.col]=harry
            }
        }
    }
    /**
     * si Harry se encuentra con Bellatrix hacemos un random con el 50% de probabilidad de atacrla y si
     * ataco bellatrix se mueve de posicion ya que no puedo matarla
     * y si no ataco hacemos un randome con el 60% de probabiidad de que me ataque y cambia de posicion
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun ataqueBellatrix() {
        println("TE HAS ENCONTRADO CON BELLATRIX")
        if ((0..100).random()<50){
            println("TU HECHIZO A SIDO EFECTIVO PERO BELLATRIX A ESCAPADO")
            colocarBellatrix()
            mazmorra[harry.fila][harry.col]=harry
        }else {
            if ((0..100).random()<60){
                harry.vida -= bellatrix.daño
                println("BELLATRIX TE HA ATACADO Y ESCAPA")
                mazmorra[harry.fila][harry.col]=harry
                colocarBellatrix()
            }else{
                println("BELLATRIX SE A ESCAPADO")
                colocarBellatrix()
                mazmorra[harry.fila][harry.col]=harry
            }
        }
    }

    /**
     * si nos encontramos con un horrocruxe lo matamos directamente y quitamos del contador
     * de horrocruxes uno
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun ataqueHorrocruxes() {
        println("TE HAS ENCONTRADO CON UN HORROCRUXE")
        println("HAS MATADO UN HORROCRUXE")
        mazmorra[harry.fila][harry.col]=harry
        horrocruxes.numHorro-=1
    }
    /**
     * si Harry se encuentra con Voldemort hacemos un random con el 50% de probabilidad de atacrle y si
     * ataco Voldemort se mueve de posicion ya que no puedo matarla
     * y si no ataco hacemos un random con el 60% de probabiidad de que me ataque y cambia de posicion
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun ataqueVoldemort() {
        println("TE HAS ENCONTRADO CON VOLDEMORT")
        if ((0..100).random()<50){
            println("TU HECHIZO A SIDO EFECTIVO PERO VOLDEMORT A ESCAPADO")
            colocarVoldemort()
            mazmorra[harry.fila][harry.col]=harry
        }else {
            if ((0..100).random()<60){
                harry.vida -= voldemort.daño
                println("VOLDEMORT TE HA ATACADO Y ESCAPA")
                mazmorra[harry.fila][harry.col]=harry
                colocarVoldemort()
            }else{
                println("VOLDEMORT SE A ESCAPADO")
                colocarVoldemort()
                mazmorra[harry.fila][harry.col]=harry
            }
        }
    }

    /**
     * esta seria una de las funciones principales que es con la que interactua el usurio
     * para mover al personaje
     * @see moverAbajo
     * @see moverArriba
     * @see moverDerecha
     * @see moverIzquierda
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun moverHarry(){
        var opcion:String
        var opcionValida=false
        var expOpcion:Regex="^[aA]||[sS]||[wW]||[dD]$".toRegex()
        do{
            do {
                println("PARA MOVER LA PERSONAJE UTILIZA W/S/A/D")
                opcion= readln()
                opcionValida=expOpcion.matches(opcion)
                if (!opcionValida) {
                    println("INTRODUCE UNA LETRA CORRECTA")
                    imprimirMazmorraInt()
                }
            }while (!opcionValida)
            var posicionValida=false
                    when(opcion.toUpperCase()){
                        "W"->posicionValida=moverArriba()
                        "S"->posicionValida=moverAbajo()
                        "A"->posicionValida=moverIzquierda()
                        "D"->posicionValida=moverDerecha()
                    }
            if (!posicionValida){
                println("RECUERDA QUE NO PUEDES SALIRTE DEL LIMITE")
                imprimirMazmorraInt()
            }
        }while (!posicionValida)
    }

    /**
     * si queremos mover hacia arriba le restamos 1 a la fila de la posicion de harry
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun moverArriba() :Boolean{
        var fila=harry.fila

        var newFil=fila-1
        if (newFil in mazmorra.indices){
            mazmorra[fila][harry.col]=visitada
            harry.fila=newFil
            return true
        }
        else return false
    }

    /**
     * si queremos mover hacia abajo le sumamos 1 a la fila de la posicion de harry
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun moverAbajo(): Boolean {
        var fila=harry.fila

        var newFil=fila+1
        if (newFil in mazmorra.indices){
            mazmorra[fila][harry.col]=visitada
            harry.fila=newFil
            return true
        }
        else return false
    }

    /**
     * si queremos mover hacia la izquierda le restamos 1 a la columna de la posicion de harry
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun moverIzquierda(): Boolean {

        var columna=harry.col
        var newCol=columna-1
        if (newCol in mazmorra.indices){
            mazmorra[harry.fila][columna]=visitada
            harry.col=newCol
            return true
        }
        else return false
    }

    /**
     * si queremos mover hacia la derecha le sumamos 1 a la columna de la posicion de harry
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun moverDerecha(): Boolean {
        var columna=harry.col
        var newCol=columna+1
        if (newCol in mazmorra.indices){
            mazmorra[harry.fila][columna]=visitada
            harry.col=newCol
            return true
        }
        else return false
    }

    /**
     * la funcion en la que almacenamos la funcion de colocar a sus amigos
     * @see colocarRon
     * @see colocarHermione
     * @see colocarMcgonagall
     */
    private fun colocarAmigos() {
        colocarMcgonagall()
        colocarHermione()
        colocarRon()
    }

    /**
     * para posicionar a nuestro personaje buscamos una casilla que sea null
     * hacemos un random de fila y columna para encontrar esta casilla y lo posicionamos
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun colocarRon() {
        var esCorrecto=false
        do {
            val fila=(0..5).random()
            val columna=(0..5).random()
            if (mazmorra[fila][columna]==null){
                mazmorra[fila][columna]=ron
                esCorrecto=true
            }
        }while (!esCorrecto)
    }
    /**
     * para posicionar a nuestro personaje buscamos una casilla que sea null
     * hacemos un random de fila y columna para encontrar esta casilla y lo posicionamos
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun colocarHermione() {
        var esCorrecto=false
        do {
            val fila=(0..5).random()

            val columna=(0..5).random()

            if (mazmorra[fila][columna]==null){
                mazmorra[fila][columna]=hermione
                esCorrecto=true
            }

        }while (!esCorrecto)
    }
    /**
     * para posicionar a nuestro personaje buscamos una casilla que sea null
     * hacemos un random de fila y columna para encontrar esta casilla y lo posicionamos
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun colocarMcgonagall() {
        var esCorrecto=false
        do {
            val fila=(0..5).random()

            val columna=(0..5).random()

            if (mazmorra[fila][columna]==null){
                mazmorra[fila][columna]=mcgonagall
                esCorrecto=true
            }

        }while (!esCorrecto)
    }
    /**
     * la funcion en la que almacenamos la funcion de colocar a sus enemigos
     * @see colocarVoldemort
     * @see colocarBellatrix
     * @see colocarDementores
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun colocarEnemigos() {
        colocarDementores()
        colocarVoldemort()
        colocarBellatrix()
    }
    /**
     * para posicionar a nuestro personaje buscamos una casilla que sea null
     * hacemos un random de fila y columna para encontrar esta casilla y lo posicionamos
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun colocarBellatrix() {
        var esCorrecto=false
        do {
            val fila=(0..5).random()

            val columna=(0..5).random()

            if (mazmorra[fila][columna]==null||mazmorra[fila][columna]==visitada){
                mazmorra[fila][columna]=bellatrix
                esCorrecto=true
            }

        }while (!esCorrecto)
    }
    /**
     * para posicionar a nuestro personaje buscamos una casilla que sea null
     * hacemos un random de fila y columna para encontrar esta casilla y lo posicionamos
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun colocarVoldemort() {
        var esCorrecto=false
        do {
            val fila=(0..5).random()

            val columna=(0..5).random()

            if (mazmorra[fila][columna]==null||mazmorra[fila][columna]==visitada){
                mazmorra[fila][columna]=voldemort
                esCorrecto=true
            }

        }while (!esCorrecto)
    }
    /**
     * para posicionar a nuestros personajes buscamos una casilla que sea null
     * hacemos un random de fila y columna para encontrar esta casilla, lo posicionamos y
     * sumamos uno al contador y repetimos esto hasta el numero del contador sea igual al de los
     * dementores
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun colocarDementores() {
        var contador=0
        do {
            val fila=(0..5).random()

            val columna=(0..5).random()

            if (mazmorra[fila][columna]==null){
                mazmorra[fila][columna]=dementor
            }
            contador++
        }while (contador!= NUM_DEMENTORES)
    }
    /**
     * para posicionar a nuestros personajes buscamos una casilla que sea null
     * hacemos un random de fila y columna para encontrar esta casilla, lo posicionamos y
     * sumamos uno al contador y repetimos esto hasta el numero del contador sea igual al de los
     * horrocruxes
     * @author Yahya el hadri el bakkali
     * @since version 1.0
     */
    private fun colocarHorrocruxes() {
        var contador=0
        do {
            var fila:Int
            var columna:Int
            do {
                fila=(0..5).random()
                columna=(0..5).random()

            }while (mazmorra[fila][columna]!=null)

            mazmorra[fila][columna]=horrocruxes
            contador++

        }while (contador!= NUM_HORROCUXES)
    }
}
