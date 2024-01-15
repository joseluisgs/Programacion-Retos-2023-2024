package models
/**
 * Clase que representa al personaje Hermione, un aliado en la aventura.
 *
 * @property cura Valor de curación proporcionado por Hermione.
 */
class Hermione:Aliados(cura=30) {

    /**
     * Realiza la acción de curar, devolviendo el valor de curación proporcionado por Hermione y mostrando un mensaje.
     *
     * @return Valor de curación proporcionado por Hermione.
     */
     override fun curar(): Int {
        println("Hermione cura $cura")
         return cura
    }
    }



