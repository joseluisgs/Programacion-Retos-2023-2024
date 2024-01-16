package models
import org.example.models.Personaje
/**
 * Clase abstracta que representa a los enemigos en la aventura.
 *
 * @property daño Valor de daño infligido por los enemigos.
 */
abstract class Enemigos(open var daño:Int): Personaje(){
    /**
     * Realiza la acción de atacar, devolviendo el valor de daño infligido por los enemigos.
     *
     * @return Valor de daño infligido por los enemigos.
     */
    open fun atacar():Int{
        return daño
    }
}