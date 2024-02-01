package validator
import org.example.validator.UsuarioValidator
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Clase de prueba para la validación de contraseñas en la clase UsuarioValidator.
 */
class UsuarioValidatorTest {
    private val validator = UsuarioValidator()

    /**
     * Prueba que una contraseña válida "MARVEL" pasa la validación.
     */
    @Test
    fun testValidPasswordMarvel() {
        val password = "MARVEL"
        assertTrue(validator.validate(password))
    }

    /**
     * Prueba que una contraseña válida "DC" pasa la validación.
     */
    @Test
    fun testValidPasswordDC() {
        val password = "DC"
        assertTrue(validator.validate(password))
    }

    /**
     * Prueba que una contraseña inválida no pasa la validación.
     */
    @Test
    fun testInvalidPassword() {
        val contraseña = "La contraseña no es válida"
        assertFalse(validator.validate(contraseña))
    }

    /**
     * Prueba que una contraseña vacía no pasa la validación.
     */
    @Test
    fun testInvalidPasswordEmpty() {
        val validator = UsuarioValidator() // Crear una instancia de UsuarioValidator
        val password = ""
        assertFalse(validator.validate(password))
    }
}