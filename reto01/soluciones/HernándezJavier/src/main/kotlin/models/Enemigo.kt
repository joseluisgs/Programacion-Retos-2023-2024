package models

/**
 * Enemigo
 * @property nombre el nombre del enemigo
 * @property contraAtaque el daño de su contraataque
 */
abstract class Enemigo (var nombre: String = "models.Enemigo", var contraAtaque: Int = 1) {
    /**
     * Contraataque que lleva a cabo el objeto cuando es atacado
     * @return el daño de su contraataque
     */
    open fun contraAtaque(): Int {
        println("$nombre contraataca con un hechizo")
        return contraAtaque
    }
}