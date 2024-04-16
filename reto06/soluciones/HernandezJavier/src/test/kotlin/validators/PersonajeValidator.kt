package validators

import org.example.exceptions.personajes.PersonajeError
import org.example.models.Guerrero
import org.example.validators.PersonajeValidator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonajeValidator {
    private val validator = PersonajeValidator()

    @Test
    fun validate(){
        val mocKPersonaje = Guerrero("PersonajeTest1","PersonajeExtra",10,30,"Extra1",false)
        val result = validator.validate(mocKPersonaje).value
        assertAll(
            {assertEquals("PersonajeTest1", result.nombre)},
            {assertEquals("Extra1", result.arma)}
        )
    }

    @Test
    fun validateErrorName(){
        val mocKPersonaje = Guerrero("","PersonajeExtra",10,30,"Extra1",false)
        val error = validator.validate(mocKPersonaje).error
        assertTrue { error is PersonajeError.PersonajeInvalido }
    }

    @Test
    fun validateErrorHabilidad(){
        val mocKPersonaje = Guerrero("PersonajeTest1","",10,30,"Extra1",false)
        val error = validator.validate(mocKPersonaje).error
        assertTrue { error is PersonajeError.PersonajeInvalido }
    }

    @Test
    fun validateErrorArma(){
        val mocKPersonaje = Guerrero("PersonajeTest1","PersonajeExtra",10,30,"",false)
        val error = validator.validate(mocKPersonaje).error
        assertTrue { error is PersonajeError.PersonajeInvalido }
    }

    @Test
    fun validateErrorEdad(){
        val mocKPersonaje = Guerrero("PersonajeTest1","PersonajeExtra",10,0,"Extra1",false)
        val error = validator.validate(mocKPersonaje).error
        assertTrue { error is PersonajeError.PersonajeInvalido }
    }
}