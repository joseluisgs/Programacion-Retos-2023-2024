package services.personajes

import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.example.models.Personaje
import org.example.repositories.personajes.PersonajesRepository
import org.example.services.cache.base.Cache
import org.example.services.cache.personajes.PersonajesCache
import org.example.services.personajes.PersonajeServiceImpl
import org.example.services.storage.Storage
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
class PersonajeServiceImplTest {

    @MockK
    private lateinit var mockRepository: PersonajesRepository

    @MockK
    private lateinit var mockStorage : Storage<Personaje>

    @MockK
    private lateinit var mockCache : PersonajesCache

    @InjectMockKs
    private lateinit var service: PersonajeServiceImpl

    @Test
    fun loadFromCsv() {
    }

    @Test
    fun storeToCsv() {
    }

    @Test
    fun loadFromJson() {
    }

    @Test
    fun storeToJson() {
    }

    @Test
    fun findAll() {
        val mockPersonajes = listOf(
            Personaje(
                id = 1,
                tipo = "Guerrero",
                nombre = "PersonajePrueba",
                habilidad = "Habilidad prueba",
                ataque = "100",
                edad = 46,
                arma = "Arma prueba",
                createdAt = LocalDateTime.now().toString(),
                updatedAt = LocalDateTime.now().toString(),
                isDeleted = false
            )
        )

        every { mockRepository.findAll() } returns mockPersonajes

        val personajes = service.findAll()

        assertAll(
            { assertEquals(1, personajes.size) },
            { assertEquals("PersonajePrueba", personajes[0].nombre) }
        )

        verify(exactly = 1) { mockRepository.findAll() }
    }

    @Test
    fun findByTipo() {
        val tipo = "Guerrero"
        val mockPersonajes = listOf(
            Personaje(
                id = 1,
                tipo = "Guerrero",
                nombre = "PersonajePrueba",
                habilidad = "Habilidad prueba",
                ataque = "100",
                edad = 46,
                arma = "Arma prueba",
                createdAt = LocalDateTime.now().toString(),
                updatedAt = LocalDateTime.now().toString(),
                isDeleted = false
            )
        )

        every { mockRepository.findByTipo(tipo) } returns mockPersonajes

        val personajes = service.findByTipo(tipo)

        assertAll(
            { assertEquals(1, personajes.size) },
            { assertEquals("PersonajePrueba", personajes[0].nombre) }
        )

        verify(exactly = 1) { mockRepository.findByTipo(tipo) }
    }

    @Test
    fun findById() {
        val id = 1L
        val mockPersonaje = Personaje(
            id = 1,
            tipo = "Guerrero",
            nombre = "PersonajePrueba",
            habilidad = "Habilidad prueba",
            ataque = "100",
            edad = 46,
            arma = "Arma prueba",
            createdAt = LocalDateTime.now().toString(),
            updatedAt = LocalDateTime.now().toString(),
            isDeleted = false
        )

        every { mockCache.get(1) } returns mockPersonaje

        val personaje = service.findById(id)

        assertAll(
            { assertEquals(1, personaje.id) },
            { assertEquals("Guerrero", personaje.tipo) },
            { assertEquals("PersonajePrueba", personaje.nombre)},
            { assertEquals("Habilidad prueba", personaje.habilidad) },
            { assertEquals("100", personaje.ataque) },
            { assertEquals(46, personaje.edad)},
            { assertEquals("Arma prueba", personaje.arma) }
        )

        verify(exactly = 1) { mockCache.get(id) }
    }


    @Test
    fun save() {
        val newPersonaje = Personaje(
            tipo = "Guerrero",
            nombre = "PersonajePrueba2",
            habilidad = "Habilidad prueba2",
            ataque = "200",
            edad = 78,
            arma = "Arma prueba2"
        )

        every { mockRepository.save(newPersonaje) } returns newPersonaje.copy(id = 1)
        every { mockCache.put(any(), any()) } just Runs

        val personaje = service.save(newPersonaje)

        assertAll(
            { assertEquals(1, personaje.id) },
            { assertEquals("Guerrero", personaje.tipo) },
            { assertEquals("PersonajePrueba2", personaje.nombre)},
            { assertEquals("Habilidad prueba2", personaje.habilidad) },
            { assertEquals("200", personaje.ataque) },
            { assertEquals(78, personaje.edad)},
            { assertEquals("Arma prueba2", personaje.arma) }
        )

        verify(exactly = 1) { mockRepository.save(newPersonaje) }
        verify(exactly = 1) { mockCache.put(any(), any()) }
    }

    @Test
    fun update() {
        val id = 1L
        val updatedPersonaje = Personaje(
            id = 1,
            tipo = "Guerrero",
            nombre = "PersonajePrueba cambio",
            habilidad = "Habilidad prueba cambio",
            ataque = "500",
            edad = 70,
            arma = "Arma prueba cambio"
        )

        every { mockRepository.update(id, updatedPersonaje) } returns updatedPersonaje
        every { mockCache.put(id, updatedPersonaje) } just runs

        val personaje = service.update(id, updatedPersonaje)

        assertAll(
            { assertEquals(1, personaje.id) },
            { assertEquals("Guerrero", personaje.tipo) },
            { assertEquals("PersonajePrueba cambio", personaje.nombre)},
            { assertEquals("Habilidad prueba cambio", personaje.habilidad) },
            { assertEquals("500", personaje.ataque) },
            { assertEquals(70, personaje.edad)},
            { assertEquals("Arma prueba cambio", personaje.arma) }
        )

        verify(exactly = 1) { mockRepository.update(id, updatedPersonaje) }
        verify(exactly = 1) { mockCache.put(id, updatedPersonaje) }
    }

    @Test
    fun delete() {
        val id = 1L
        val deletedPersonaje = Personaje(
            id = 1,
            tipo = "Guerrero",
            nombre = "PersonajePrueba",
            habilidad = "Habilidad prueba",
            ataque = "100",
            edad = 46,
            arma = "Arma prueba"
        )

        every { mockRepository.delete(id) } returns deletedPersonaje
        every { mockCache.remove(1) } returns deletedPersonaje

        val personaje = service.delete(id)

        assertAll(
            { assertEquals(1, personaje.id) },
            { assertEquals("Guerrero", personaje.tipo) },
            { assertEquals("PersonajePrueba", personaje.nombre)},
            { assertEquals("Habilidad prueba", personaje.habilidad) },
            { assertEquals("100", personaje.ataque) },
            { assertEquals(46, personaje.edad)},
            { assertEquals("Arma prueba", personaje.arma) }
        )
    }
}