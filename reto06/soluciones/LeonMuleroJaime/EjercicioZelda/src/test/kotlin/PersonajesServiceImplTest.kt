import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.example.models.Enemigo
import org.example.models.Guerrero
import org.example.repositories.personajes.PersonajesRepository
import org.example.services.cache.personajes.PersonajesCache
import org.example.services.personajes.PersonajesServiceImpl
import org.example.services.storage.StoragePersonajesCsv
import org.example.services.storage.StoragePersonajesJson
import org.example.validators.PersonajeValidator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNull

@ExtendWith(MockKExtension::class)
class PersonajesServiceImplTest {

    @MockK
    private lateinit var storagePersonajesCsv: StoragePersonajesCsv

    @MockK
    private lateinit var storagePersonajesJson: StoragePersonajesJson

    @MockK
    private lateinit var personajesRepo: PersonajesRepository

    @MockK
    private lateinit var personajesCache: PersonajesCache

    @MockK
    private lateinit var personajeValidator: PersonajeValidator

    @InjectMockKs
    private lateinit var service: PersonajesServiceImpl

    @Test
    fun findAll() {
        val personajes = listOf(
            Guerrero(1,"PersonajeExtra1","PersonajeExtra1",1,1,"PersonajeExtra1"),
            Guerrero(2,"PersonajeExtra2","PersonajeExtra2",2,2,"PersonajeExtra2"),
            Enemigo(3,"PersonajeExtra3","PersonajeExtra3",3,3,"PersonajeExtra3"),
            Enemigo(4,"PersonajeExtra4","PersonajeExtra4",4,4,"PersonajeExtra4")
        )

        every { personajesRepo.findAll() } returns personajes

        val result = service.findAll()

        assertAll(
            { assertEquals(4, result.size) },
            { assertEquals(1, result[0].id) },
            { assertEquals(2, result[1].id) },
            { assertEquals(3, result[2].id) },
            { assertEquals(4, result[3].id) },
            { assertEquals("PersonajeExtra1", result[0].nombre) },
            { assertEquals("PersonajeExtra2", result[1].nombre) },
            { assertEquals("PersonajeExtra3", result[2].nombre) },
            { assertEquals("PersonajeExtra4", result[3].nombre) },
            { assertEquals("PersonajeExtra1", result[0].habilidad) },
            { assertEquals("PersonajeExtra2", result[1].habilidad) },
            { assertEquals("PersonajeExtra3", result[2].habilidad) },
            { assertEquals("PersonajeExtra4", result[3].habilidad) },
            { assertEquals(1, result[0].ataque) },
            { assertEquals(2, result[1].ataque) },
            { assertEquals(3, result[2].ataque) },
            { assertEquals(4, result[3].ataque) },
            { assertEquals(1, result[0].edad) },
            { assertEquals(2, result[1].edad) },
            { assertEquals(3, result[2].edad) },
            { assertEquals(4, result[3].edad) },
            { assertEquals("PersonajeExtra1", result[0].arma) },
            { assertEquals("PersonajeExtra2", result[1].arma) },
            { assertEquals("PersonajeExtra3", result[2].arma) },
            { assertEquals("PersonajeExtra4", result[3].arma) },
        )

        verify(exactly = 1) { personajesRepo.findAll() }
    }

    @Test
    fun findByTipo() {
        val tipo = "Guerrero"
        val personajes = listOf(
            Guerrero(1,"PersonajeExtra1","PersonajeExtra1",1,1,"PersonajeExtra1"),
            Guerrero(2,"PersonajeExtra2","PersonajeExtra2",2,2,"PersonajeExtra2"),
        )

        every { personajesRepo.findByTipo(tipo) } returns personajes

        val result = service.findByTipo(tipo)

        assertAll(
            { assertEquals(2, result.size) },
            { assertEquals(1, result[0].id) },
            { assertEquals(2, result[1].id) },
            { assertEquals("PersonajeExtra1", result[0].nombre) },
            { assertEquals("PersonajeExtra2", result[1].nombre) },
            { assertEquals("PersonajeExtra1", result[0].habilidad) },
            { assertEquals("PersonajeExtra2", result[1].habilidad) },
            { assertEquals(1, result[0].ataque) },
            { assertEquals(2, result[1].ataque) },
            { assertEquals(1, result[0].edad) },
            { assertEquals(2, result[1].edad) },
            { assertEquals("PersonajeExtra1", result[0].arma) },
            { assertEquals("PersonajeExtra2", result[1].arma) },
        )

        verify(exactly = 1) { personajesRepo.findByTipo(tipo) }
    }

    @Test
    fun findById() {
        val id = 1
        val personaje = Guerrero(1,"PersonajeExtra1","PersonajeExtra1",1,1,"PersonajeExtra1")

        every { personajesCache.get(id) } returns personaje
        every { personajesRepo.findById(id) } returns personaje

        val result = service.findById(id)

        assertAll(
            { assertEquals(1, result.id) },
            { assertEquals("PersonajeExtra1", result.nombre) },
            { assertEquals("PersonajeExtra1", result.habilidad) },
            { assertEquals(1, result.ataque) },
            { assertEquals(1, result.edad) },
            { assertEquals("PersonajeExtra1", result.arma) },
        )

    }

    @Test
    fun save() {
        val personaje = Guerrero(1,"PersonajeExtra1","PersonajeExtra1",1,1,"PersonajeExtra1")

        every { personajeValidator.validate(personaje) } returns personaje
        every { personajesRepo.save(personaje) } returns personaje
        every { personajesCache.put(personaje.id, personaje) } returns Unit

        val result = service.save(personaje)

        assertAll(
            { assertEquals(1, result.id) },
            { assertEquals("PersonajeExtra1", result.nombre) },
            { assertEquals("PersonajeExtra1", result.habilidad) },
            { assertEquals(1, result.ataque) },
            { assertEquals(1, result.edad) },
            { assertEquals("PersonajeExtra1", result.arma) }
        )

        verify(exactly = 1) { personajesRepo.save(personaje) }
    }

    @Test
    fun update() {
        val id = 1
        val personaje = Guerrero(1,"PersonajeUpdate1","PersonajeUpdate1",1,1,"PersonajeUpdate1")

        every { personajeValidator.validate(personaje) } returns personaje
        every { personajesRepo.update(id, personaje) } returns personaje
        every { personajesCache.put(id, personaje) } returns Unit

        val result = service.update(id, personaje)

        assertAll(
            { assertEquals(1, result.id) },
            { assertEquals("PersonajeUpdate1", result.nombre) },
            { assertEquals("PersonajeUpdate1", result.habilidad) },
            { assertEquals(1, result.ataque) },
            { assertEquals(1, result.edad) },
            { assertEquals("PersonajeUpdate1", result.arma) },
        )

        verify(exactly = 1) { personajesRepo.update(id, personaje) }
    }

    @Test
    fun deleteLogico() {
        val id = 1
        val personaje = Guerrero(1,"PersonajeExtra1","PersonajeExtra1",1,1,"PersonajeExtra1", is_deleted = true)

        every { personajesRepo.deleteLogico(id) } returns personaje
        every { personajesCache.remove(id) } returns personaje

        val result = service.deleteLogico(id)

        assertAll(
            { assertEquals(1, result.id) },
            { assertEquals(true, result.is_deleted) },
        )

        verify(exactly = 1) { personajesRepo.deleteLogico(id) }
    }

    @Test
    fun deleteFisico() {
        val id = 1
        val personaje = Guerrero(1,"PersonajeExtra1","PersonajeExtra1", 1, 1 , "PersonajeExtra1")

        every { personajesRepo.deleteFisico(id) } returns personaje
        every { personajesCache.remove(id) } returns personaje

        val result = service.deleteFisico(id)

        assertEquals(1, result.id)

        verify(exactly = 1) { personajesRepo.deleteFisico(id) }
    }

}