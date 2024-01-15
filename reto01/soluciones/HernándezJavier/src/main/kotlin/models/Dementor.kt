package models

/**
 * Enemigo dementor
 * @property nombre Dementor
 * @property contraAtaque el daño que hace su contraataque
 */
class Dementor(nombre: String = "models.Dementor", contraAtaque: Int = 10) : Enemigo(nombre, contraAtaque) {
    /**
     * Contraataque que lleva a cabo el dementor cuando es atacado
     * @return el daño de su contraataque
     */
    override fun contraAtaque(): Int {
        println("El dementor contraataca con un hechizo poco eficaz")
        return contraAtaque
    }
    /**
     * La interacción que lleva a cabo el objeto al coincidir con Harry
     * @param mazmorra la matriz en la que se encuentra el objeto
     * @param fila la fila en la cual está Harry
     * @param columna la columna en la cual está Harry
     */
    fun interaccion(mazmorra: Mazmorra ,mazmorraMatriz: Array<Array<Any?>>, fila: Int, columna: Int){
        do {
            val random = (0..100).random()
            if (random > 60) {
                println("Harry ha fallado")
                mazmorra.harry.vida -= contraAtaque()
            }else{
                println("Harry acierta el hechizo y ejecuta al dementor")
                mazmorra.dementoresEjecutados++
                mazmorraMatriz[fila][columna] = Harry()
            }
        } while (random > 60 &&  mazmorra.harry.vida > 0)
    }
}