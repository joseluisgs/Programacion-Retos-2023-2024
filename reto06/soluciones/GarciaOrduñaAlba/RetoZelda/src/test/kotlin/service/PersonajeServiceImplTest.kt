
package service
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.example.models.Enemigo
import org.example.models.Guerrero
import org.example.models.Personaje
import org.example.repositories.personaje.PersonajeRepository
import org.example.service.cache.personaje.PersonajeCache
import org.example.service.personaje.PersonajeServiceImpl
import org.example.service.storage.Storage
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class)
class ProductosServicesImplTest {

    @MockK
    private lateinit var mockRepository: PersonajeRepository

    @MockK
    private lateinit var mockStorage : Storage<Personaje>

    @MockK
    private lateinit var mockCache : PersonajeCache

    @InjectMockKs
    private lateinit var service: PersonajeServiceImpl


    @Test
    fun findAll() {
        val mockPersonajes = listOf(
            Guerrero(
                id = 1,
                nombre = "Prueba1",
                habilidad = "Prueba",
                ataque = 100,
                edad = 46,
                arma = "Prueba",

            )

        )

        every { mockRepository.findAll() } returns mockPersonajes

        val personajes = service.findAll()

        assertAll(
            { assertEquals(1, personajes.size) },
            { assertEquals("Prueba1", personajes[0].nombre) }
        )

        verify(exactly = 1) { mockRepository.findAll() }
    }

    @Test
    fun findByTipo() {
        val tipo = "Enemigo"
        val mockPersonajes = listOf(
            Enemigo(
                id = 1,
                nombre = "Prueba1",
                habilidad = "Prueba",
                ataque = 100,
                edad = 46,
                arma = "Prueba"
            )
        )

        every { mockRepository.findByTipo(tipo) } returns mockPersonajes

        val personajes = service.findByTipo(tipo)

        assertAll(
            { assertEquals(1, personajes.size) },
            { assertEquals("Prueba1", personajes[0].nombre) }
        )

        verify(exactly = 1) { mockRepository.findByTipo(tipo) }
    }

    @Test
    fun findById() {
        val id = 1
        val mockPersonaje = Guerrero(
            id = 1,
            nombre = "Prueba1",
            habilidad = "Prueba1",
            ataque = 100,
            edad = 46,
            arma = "Prueba1"
        )

        every { mockCache.get(1) } returns mockPersonaje

        val personaje = service.findById(id)

        assertAll(
            { assertEquals(1, personaje.id) },
            { assertEquals("Prueba1", personaje.nombre)},
            { assertEquals("Prueba1", personaje.habilidad) },
            { assertEquals(100, personaje.ataque) },
            { assertEquals(46, personaje.edad)},
            { assertEquals("Prueba1", personaje.arma) }
        )

        verify(exactly = 1) { mockCache.get(id) }
    }


    @Test
    fun save() {
        val newPersonaje = Enemigo(
            id = 1,
            nombre = "Prueba1",
            habilidad = "Prueba1",
            ataque = 560,
            edad = 54,
            arma = "Prueba1"
        )

        every { mockRepository.save(newPersonaje) } returns newPersonaje
        every { mockCache.put(any(), any()) } returns Unit

        val personaje = service.save(newPersonaje)

        assertAll(
            { assertEquals(1, personaje.id) },
            { assertEquals("Prueba1", personaje.nombre)},
            { assertEquals("Prueba1", personaje.habilidad) },
            { assertEquals(560, personaje.ataque) },
            { assertEquals(54, personaje.edad)},
            { assertEquals("Prueba1", personaje.arma) }
        )

        verify(exactly = 1) { mockRepository.save(newPersonaje) }
        verify(exactly = 1) { mockCache.put(any(), any()) }
    }

    @Test
    fun update() {
        val id = 1
        val updatedPersonaje = Enemigo(
            id = 1,
            nombre = "Prueba1",
            habilidad = "Prueba1",
            ataque = 500,
            edad = 70,
            arma = "Prueba1"
        )

        every { mockRepository.update(id, updatedPersonaje) } returns updatedPersonaje
        every { mockCache.put(id, updatedPersonaje) } just runs

        val personaje = service.update(id, updatedPersonaje)

        assertAll(
            { assertEquals(1, personaje.id) },
            { assertEquals("Prueba1", personaje.nombre)},
            { assertEquals("Prueba1", personaje.habilidad) },
            { assertEquals(500, personaje.ataque) },
            { assertEquals(70, personaje.edad)},
            { assertEquals("Prueba1", personaje.arma) }
        )

        verify(exactly = 1) { mockRepository.update(id, updatedPersonaje) }
        verify(exactly = 1) { mockCache.put(id, updatedPersonaje) }
    }

    @Test
    fun delete() {
        val id = 1
        val deletedPersonaje = Guerrero(
            id = 1,
            nombre = "Prueba1",
            habilidad = "Prueba1",
            ataque = 100,
            edad = 46,
            arma = "Prueba1"
        )

        every { mockRepository.delete(id) } returns deletedPersonaje
        every { mockCache.remove(1) } returns deletedPersonaje

        val personaje = service.delete(id)

        assertAll(
            { assertEquals(1, personaje.id) },
            { assertEquals("Prueba1", personaje.nombre)},
            { assertEquals("Prueba1", personaje.habilidad) },
            { assertEquals(100, personaje.ataque) },
            { assertEquals(46, personaje.edad)},
            { assertEquals("Prueba1", personaje.arma) }
            )
    }
}