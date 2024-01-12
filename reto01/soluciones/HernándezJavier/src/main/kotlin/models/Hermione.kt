package models

/**
 * Hermione, un aliado que cura la vida de harry en caso de que esté herido
 * @property nombre Hermione
 * @property curacion la cantidad de vida que cura
 */
class Hermione(nombre: String = "Hermione", vida: Int = 1, var curacion: Int = 30) : Aliado(nombre, vida), Visibilidad {
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
            println("Harry ya tienes toda la vida lokete")
            mazmorraMatriz[fila][columna] = Hermione()
            cambiarVista(vista)
        } else {
            println("Hermione cura las heridas de Harry con un hechizo")
            mazmorra.harry.vida += curacion
            mazmorraMatriz[fila][columna] = Harry()
        }
    }
}