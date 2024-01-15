package models

open class Voldemort(nombre: String = "Voldemort", contraAtaque: Int = 70) : Enemigo(nombre, contraAtaque), Visibilidad {


    /**
     * Contraataque que lleva a cabo voldemort cuando es atacado
     * @return el daño de su contraataque
     */
    override fun contraAtaque(): Int {
        println("$nombre contraataca con un hechizo")
        return contraAtaque
    }
    override var vista: Boolean = false

    /**
     * Cambia el estado de la vista del objeto
     * @param vista el estado de la vista del objeto
     */
    override fun cambiarVista(vista: Boolean){
        if(!vista){
            this.vista = true
        }
    }

    /**
     * Cambia al objeto a una posición aleatoria de la matriz y en caso de estar marcada como visitada se cambia el estado de vista del objeto
     * @param mazmorra la matriz
     * @return la mazmorra con el objeto cambiado de posición
     */
    fun teletransporte(mazmorra: Array<Array<Any?>>): Array<Array<Any?>> {
        var fila: Int
        var columna: Int
        do {
            fila = (0..mazmorra.size - 1).random()
            columna = (0..mazmorra.size - 1).random()
        } while (mazmorra[fila][columna] != null && mazmorra[fila][columna] != true)
        when(mazmorra[fila][columna]){
            true -> cambiarVista(vista)
        }
        mazmorra[fila][columna] = this
        return mazmorra
    }

    /**
     * La interacción que lleva a cabo el objeto al coincidir con Harry
     * @param mazmorra el objeto mazmorra
     * @param mazmorraMatriz la matriz donde se encuentran los objetos
     * @param fila la fila en la cual está Harry
     * @param columna la columna en la cual está Harry
     */
    fun interacción(mazmorra: Mazmorra, mazmorraMatriz: Array<Array<Any?>>, fila: Int, columna: Int){
        println("Harry se ha encontrado a Voldemort")
        do {
            val random = (0..100).random()
            if (random > 60) {
                println("Harry ha fallado")
                mazmorra.harry.vida -= contraAtaque()
            }else{
                println("Harry acierta el hechizo y voldemort huye")
                mazmorraMatriz[fila][columna] = Harry()
                teletransporte(mazmorraMatriz)
            }
        } while (random > 60 && mazmorra.harry.vida > 0)
    }
}