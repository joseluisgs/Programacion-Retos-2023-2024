package models

/**
 * Aliado Ron cura a Harry cuando esté herido y hay un 30% de posibilidades de que falle y le haga daño
 * @property nombre Ron
 * @property vida la vida del objeto
 * @property curacion la cantidad de vida que cura su curación
 */
class Ron(nombre: String = "Ron", vida: Int = 1, private var curacion: Int = 20) : Aliado(nombre, vida), Visibilidad {
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
     * La interacción que lleva a cabo el objeto al coincidir con Harry
     * @param mazmorra el objeto mazmorra
     * @param mazmorraMatriz la matriz donde se encuentran los objetos
     * @param fila la fila en la cual está Harry
     * @param columna la columna en la cual está Harry
     */
    fun interacción(mazmorra: Mazmorra ,mazmorraMatriz: Array<Array<Any?>>, fila: Int, columna: Int){
        if (mazmorra.harry.vida >= 100) {
            println("Harry que tienes toda la vida calla ya ")
            mazmorraMatriz[fila][columna] = Ron()
            cambiarVista(vista)
        } else {
            if ((0..100).random() < 30) {
                println("Ron ha metido la pata y ha fallado la curación haciendo daño a harry")
                mazmorra.harry.vida -= 10
            } else {
                println("Ron cura las heridas de Harry con un hechizo")
                    mazmorra.harry.vida += curacion
                mazmorraMatriz[fila][columna] = Harry()
            }
        }
    }
}