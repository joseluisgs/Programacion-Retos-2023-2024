package models

/**
 * Los Horrocruxes que tiene que destruir Harry
 */
class Horrocurxes {
    /**
     * La interacción que lleva a cabo el objeto al coincidir con Harry
     * @param mazmorra la matriz en la que se encuentra el objeto
     * @param fila la fila en la cual está Harry
     * @param columna la columna en la cual está Harry
     */
    fun interracción(mazmorra: Mazmorra ,mazmorraMatriz: Array<Array<Any?>>, fila: Int, columna: Int){
        println("Harry ha destruido un horrocrux")
        mazmorra.horrDestruidos++
        mazmorraMatriz[fila][columna] = Harry()
    }
}