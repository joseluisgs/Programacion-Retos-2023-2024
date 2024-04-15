package repositories


import org.example.models.Guerrero
import org.example.repositories.personaje.PersonajeRepositoryImpl
import org.example.service.database.DataBaseInitializer
import org.junit.jupiter.api.*
import kotlin.test.assertEquals


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonajeRepositoryImplTest {
    private val personajesRepository = PersonajeRepositoryImpl()

    @BeforeEach
    fun setUp() {
        DataBaseInitializer.initialize()
    }

    @Test
    fun findAll() {
        val personajes = personajesRepository.findAll()

        assertEquals(2, personajes.size)
    }

    @Test
    fun findByTipo() {
        val personaje = personajesRepository.findByTipo("Guerrero")

        assertEquals(1, personaje.size)
    }

    @Test
    fun findByTipoNotFound() {
        val personaje = personajesRepository.findByTipo("Aliado")

        assertEquals(0, personaje.size)
    }

    @Test
    fun findById() {
        val personaje = personajesRepository.findById(1)

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
        val personaje = personajesRepository.findById(50)

        assertEquals(null, personaje)
    }

    @Test
    fun save() {
        val personaje = personajesRepository.save(Guerrero(100, "TestSave", "TestSave", 100, 100, "TestSave"))

        assertAll(
            { assertEquals(3, personaje.id) },
            { assertEquals("TestSave", personaje.nombre) },
            { assertEquals("TestSave", personaje.habilidad) },
            { assertEquals(100, personaje.ataque) },
            { assertEquals(100, personaje.edad) },
            { assertEquals("TestSave", personaje.arma) },
        )
    }

    @Test
    fun update() {
        val personaje = personajesRepository.update(1, Guerrero(100, "TestUpdate", "TestUpdate", 100, 100, "TestUpdate"))

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
        val personaje = personajesRepository.update(100, Guerrero(100, "TestUpdate", "TestUpdate", 100, 100, "TestUpdate"))

        assertEquals(null, personaje)
    }








}