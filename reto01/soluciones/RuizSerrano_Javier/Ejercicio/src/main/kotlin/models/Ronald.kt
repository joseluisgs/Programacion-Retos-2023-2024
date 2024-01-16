package org.example.models


/**
 * Clase del personaje aliado Ronald
 * @param heal Cantidad de vida que nos recupera si acierta
 * @param dmg Cantidad de vida que nos resta si falla
 * @see Ministerio
 * @author Javier Ruiz
 * @since 1.0.0
 */
class Ronald: Aliado() {

    var heal=20
    var dmg=10

    /**
     * Funcion que determina si Ronald nos cura o nos daña
     * @author Javier Ruiz
     * @since 1.0.0
     */
    fun liarla(): Boolean {
        if ((1..100).random() < 30) {
            return true
        } else {
            return false
        }
    }
}