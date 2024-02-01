package org.example.validator

class UsuarioValidator {
    // Función para validar la contraseña
    fun validate(contraseña: String): Boolean {
        // Utiliza una expresión 'when' para determinar si la contraseña cumple con los criterios requeridos
        return when (contraseña.uppercase()) {
            "MARVEL", "DC" -> true // Si la contraseña es "MARVEL" o "DC", retorna true
            else -> false // Si la contraseña no coincide con ninguna opción válida, retorna false
        }
    }
}
