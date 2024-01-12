package models
/**
 * Clase que representa al personaje McGonagall, un aliado en la aventura.
 *
 * @param cura Valor de curación proporcionado por McGonagall.
 */

class McGonagall:Aliados(cura = 70){
    /**
     * Realiza la acción de curar, devolviendo el valor de curación proporcionado por McGonagall y mostrando un mensaje.
     *
     * @return Valor de curación proporcionado por McGonagall.
     */
    override fun curar(): Int {
        println("McGonagall te cura $cura")
        return cura
    }

}



