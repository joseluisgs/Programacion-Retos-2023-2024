package org.example.validators

/**
 * Clase que representa el validador de la contraseña de las bases de datos.
 */
class ContraseñaValidator {
    /**
     * Función que valida una contraseña.
     *
     * @param contraseña contraseña introducida por el usuario.
     * @return true si la contraseña es "marvel" o "dc", false si no es ninguna de las dos.
     * @author Natalia Gonzalez
     * @since 1.0
     */
    fun validar(contraseña:String): Boolean {
        return contraseña == "marvel" || contraseña == "dc"
    }
}