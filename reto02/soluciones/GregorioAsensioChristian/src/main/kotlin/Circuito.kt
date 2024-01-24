class Circuito(val filas: Int, val columnas: Int) {

    private val circuito: Array<Array<String>> = Array(filas) { Array(columnas) { "[   ]" } }
    private var lluvia = false

    fun agregarPiloto(piloto: Piloto) {
        circuito[piloto.i][piloto.j] = "[${piloto.nombre}]"
    }

    fun imprimirCircuito() {
        for (fila in circuito) {
            for (posicion in fila) {
                print("$posicion ")
            }
            println()
        }
    }
}
