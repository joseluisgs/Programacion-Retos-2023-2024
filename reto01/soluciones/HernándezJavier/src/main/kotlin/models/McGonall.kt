package models

/**
 * Aliado McGonall que cura a harry cuando esté herido
 * @property nombre McGonall
 * @property vida la vida que tiene
 * @property curacion la cantidad de vida que cura
 */
open class McGonall(nombre: String ="McGonall", vida: Int = 1, var curacion: Int = 70) : Aliado(nombre, vida), Visibilidad {
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
            println("Harry ya tienes toda la vida")
            mazmorraMatriz[fila][columna] = McGonall()
            cambiarVista(vista)
        } else {
            println("McGonall cura las heridas de Harry con un hechizo")
            mazmorra.harry.vida += curacion
            mazmorraMatriz[fila][columna] = Harry()
        }
    }
}