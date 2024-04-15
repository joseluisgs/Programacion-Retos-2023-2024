import org.example.exceptions.personajes.PersonajeExceptions
import org.example.models.Guerrero
import org.example.repositories.personajes.PersonajesRepositoryImpl
import org.example.services.database.DataBaseManager
import org.junit.jupiter.api.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonajesRepositoryImplTest {
    private val personajesRepo = PersonajesRepositoryImpl()

    @BeforeEach
    fun setUp() {
        DataBaseManager.initialize()
    }

    @Test
    fun findAll() {
        val personajes = personajesRepo.findAll()

        assertEquals(4, personajes.size)
    }

    @Test
    fun findByTipo() {
        val personaje = personajesRepo.findByTipo("Guerrero")

        assertEquals(2, personaje.size)
    }

    @Test
    fun findByTipoNotFound() {
        val personaje = personajesRepo.findByTipo("Aliado")

        assertEquals(0, personaje.size)
    }

    @Test
    fun findById() {
        val personaje = personajesRepo.findById(1)

        assertAll(
            { assertEquals(1, personaje?.id) },
            { assertEquals("Test1", personaje?.nombre) },
            { assertEquals("Test1", personaje?.habilidad) },
            { assertEquals(10, personaje?.ataque) },
            { assertEquals(10, personaje?.edad) },
            { assertEquals("Test1", personaje?.arma) },
        )
    }

    @Test
    fun findByIdNotFound() {
        val personaje = personajesRepo.findById(50)

        assertEquals(null, personaje)
    }

    @Test
    fun save() {
        val personaje = personajesRepo.save(Guerrero(100, "TestSave", "TestSave", 100, 100, "TestSave"))

        assertAll(
            { assertEquals(5, personaje.id) },
            { assertEquals("TestSave", personaje.nombre) },
            { assertEquals("TestSave", personaje.habilidad) },
            { assertEquals(100, personaje.ataque) },
            { assertEquals(100, personaje.edad) },
            { assertEquals("TestSave", personaje.arma) },
        )
    }

    @Test
    fun update() {
        val personaje = personajesRepo.update(1, Guerrero(100, "TestUpdate", "TestUpdate", 100, 100, "TestUpdate"))

        assertAll(
            { assertEquals(1, personaje?.id) },
            { assertEquals("TestUpdate", personaje?.nombre) },
            { assertEquals("TestUpdate", personaje?.habilidad) },
            { assertEquals(100, personaje?.ataque) },
            { assertEquals(100, personaje?.edad) },
            { assertEquals("TestUpdate", personaje?.arma) },
        )
    }

    @Test
    fun updateNotFound() {
        val personaje = personajesRepo.update(100, Guerrero(100, "TestUpdate", "TestUpdate", 100, 100, "TestUpdate"))

        assertEquals(null, personaje)
    }

    @Test
    fun deleteFisico() {
        val personaje = personajesRepo.deleteFisico(1)

        assertAll(
            { assertEquals(null, personajesRepo.findById(1)) },
        )
    }

    @Test
    fun deleteFisicoNotFound() {
        val personaje = personajesRepo.deleteFisico(100)

        assertAll(
            { assertEquals(null, personaje) },
        )
    }

    @Test
    fun deleteLogico() {
        val personaje = personajesRepo.deleteLogico(1)

        assertTrue { personajesRepo.findById(1)?.is_deleted == true }
    }

    @Test
    fun deleteLogicoNotFound() {
        val personaje = personajesRepo.deleteLogico(100)

        assertEquals(null, personaje)
    }
}