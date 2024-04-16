package validators.personaje

import org.example.exceptions.personajes.PersonajesException
import org.example.models.Personaje
import org.example.validators.personaje.PersonajesValidator
import org.junit.jupiter.api.*

import java.time.LocalDate
import kotlin.test.assertEquals

class PersonajesValidatorTest {

    private lateinit var validator: PersonajesValidator
    private lateinit var personajeValido:Personaje
    private lateinit var fechaValida:LocalDate

    /*
        val id: Long = -1,
    val nombre: String,
    val tipo: String,
    val clase: String,
    val habilidad: String,
    val ataque: Int,
    val edad: Int,
    val arma: String,
    val createdAt: LocalDate,
    val updatedAt: LocalDate?,
    val isDeleted: Boolean?
     */
    @BeforeEach
    fun setUp() {
        validator = PersonajesValidator()
        fechaValida = LocalDate.of(2024,1,1)
        personajeValido = Personaje(1,"NombreTest","Guerrero","Clase Test","Habilidad Test",50,100,"Arma Test",fechaValida,fechaValida,false)
    }

    @Test
    fun validateNombreOk() {
        assertDoesNotThrow { validator.validate(personajeValido) }
    }

    @Test
    fun validateNombreInvalid(){

        val persNotValid = Personaje(
            1,
            "",
            "Guerrero",
            "Clase Test",
            "Habilidad Test",
            50,
            100,
            "Arma Test",
            fechaValida,
            fechaValida,
            false)

        val resul = assertThrows<PersonajesException> { validator.validate(persNotValid) }

        assertEquals(resul.message,"El nombre del personaje no es válido, debe tener entre 4 y 50 caracteres")


    }
    @Test
    fun validateTipoOk() {
        assertDoesNotThrow { validator.validate(personajeValido) }
    }

    @Test
    fun validateTipoInvalid(){
        val persNotValid = Personaje(
            1,
            "NombreTest",
            "Tipo Invalido",
            "Clase Test",
            "Habilidad Test",
            50,
            100,
            "Arma Test",
            fechaValida,
            fechaValida,
            false
        )


    val resul = assertThrows<PersonajesException.PersonajeNotValid> {
            validator.validate(persNotValid)
        }

        assertEquals(resul.message,"El tipo del personaje no es válido, solo puede ser 'Guerrero' o 'Enemigo'")
    }

    @Test
    fun validateClaseOk(){
        assertDoesNotThrow { validator.validate(personajeValido) }
    }

    @Test
    fun validateClaseVaciaOk(){
        val persValidClaseVacia = Personaje(1,"NombreTest","Guerrero","","Habilidad Test",50,100,"Arma Test",fechaValida,fechaValida,false)
        assertDoesNotThrow { validator.validate(personajeValido) }
    }

    @Test
    fun validateClaseNotValid(){

        val persNotValid=Personaje(
            1,
            "NombreTest",
            "Guerrero",
            "aa",
            "Habilidad Test",
            50,
            100,
            "Arma Test",
            fechaValida,
            fechaValida,
            false
        )
        val resul = assertThrows<PersonajesException> { validator.validate(persNotValid) }

        assertEquals(resul.message,"La clase del personaje no es válida, debe ser vacía o si no, tener entre 4 y 40 caracteres")
    }

    @Test
    fun validateHabilidadOk(){
        assertDoesNotThrow { validator.validate(personajeValido) }
    }

    @Test
    fun validateHabilidadInvalid(){
        val persNotValid = Personaje(
            1,
            "NombreTest",
            "Guerrero",
            "Clase Test",
            "",
            50,
            100,
            "Arma Test",
            fechaValida,
            fechaValida,
            false
        )
        val resul = assertThrows<PersonajesException> { validator.validate(persNotValid) }

        assertEquals(resul.message,"La habilidad del personaje no es válida, debe tener entre 5 y 50 caracteres")
    }

    @Test
    fun validateAtaqueOk() {
        assertDoesNotThrow { validator.validate(personajeValido) }
    }

    @Test
    fun validateAtaqueNotValid(){
        val persNotValid = Personaje(
            1,
            "NombreTest",
            "Guerrero",
            "Clase Test",
            "Habilidad Test",
            0,
            100,
            "Arma Test",
            fechaValida,
            fechaValida,
            false
        )
        val resul = assertThrows<PersonajesException.PersonajeNotValid> { validator.validate(persNotValid) }

        assertEquals(resul.message,"El ataque del personaje no es válido, debe estar entre 1 y 2147483647")
    }
    @Test
    fun validateEdadOk() {
        assertDoesNotThrow { validator.validate(personajeValido) }
    }

    @Test
    fun validateEdadNotValid(){
        val persNotValid = Personaje(
            1,
            "NombreTest",
            "Guerrero",
            "Clase Test",
            "Habilidad Test",
            50,
            0,
            "Arma Test",
            fechaValida,
            fechaValida,
            false
        )
        val resul = assertThrows<PersonajesException.PersonajeNotValid> { validator.validate(persNotValid) }

        assertEquals(resul.message,"La edad del personaje no es válida, debe tener entre 1 y 5000 años")
    }

    @Test
    fun validateArmaOk(){
        assertDoesNotThrow { validator.validate(personajeValido) }
    }

    @Test
    fun validateArmaInvalid(){
        val persNotValid = Personaje(
            1,
            "NombreTest",
            "Guerrero",
            "Clase Test",
            "Habilidad test",
            50,
            100,
            "",
            fechaValida,
            fechaValida,
            false
        )
        val resul = assertThrows<PersonajesException> { validator.validate(persNotValid) }

        assertEquals(resul.message,"El arma del personaje no es válida, debe tener entre 4 y 50 caracteres")
    }

   /* @Test
    fun validateIdOk() {
        assertDoesNotThrow { validator.validate(personajeValido) }
    }

    @Test
    fun validateIdInvalid(){
        val persNotValid = Personaje(
            -1,
            "NombreTest",
            "Guerrero",
            "Clase Test",
            "Habilidad Test",
            50,
            100,
            "Arma Test",
            fechaValida,
            fechaValida,
            false
        )

        val resul = assertThrows<PersonajesException.PersonajeNotValid> {
            validator.validate(persNotValid)
        }

        assertEquals(resul.message, "Id del personaje no válido, debe ser mayor que 0")

    }*/
}