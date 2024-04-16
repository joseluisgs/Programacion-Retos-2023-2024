package services.personajes

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import models.Enemigo
import models.Guerrero
import models.Personaje
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import repositories.personajes.PersonajesRepository
import services.cache.PersonajesCache
import services.storage.StoragePersonajeJSON
import services.storage.StoragePersonajesCSV

@ExtendWith(MockKExtension::class)
class PersonajesServiceImplTest {

    @MockK
    private lateinit var storageCsv: StoragePersonajesCSV

    @MockK
    private lateinit var storageJson: StoragePersonajeJSON

    @MockK
    private lateinit var personajesRepo: PersonajesRepository

    @MockK
    private lateinit var personajesCache: PersonajesCache

    @InjectMockKs
    private lateinit var service: PersonajesServiceImpl


    @Test
    fun findAll() {
        var personajes:List<Personaje> = listOf(
            Enemigo("Mock1","Mock1",10,55,"Mock1",false),
            Guerrero("Mock2","Mock2",100,85,"Mock2",false)
            )
        every { personajesRepo.findAll() } returns personajes

        val result=service.findAll()

        assertAll(
            { assertEquals(personajes[0].nombre,result[0].nombre) },
            { assertEquals(personajes[0].habilidad,result[0].habilidad) },
            { assertEquals(personajes[0].ataque,result[0].ataque) },
            { assertEquals(personajes[0].edad,result[0].edad) },
            { assertEquals(personajes[0].arma,result[0].arma) },
            { assertEquals(personajes[0].isDeleted,result[0].isDeleted) },
            { assertEquals(personajes[1].nombre,result[1].nombre) },
            { assertEquals(personajes[1].habilidad,result[1].habilidad) },
            { assertEquals(personajes[1].ataque,result[1].ataque) },
            { assertEquals(personajes[1].edad,result[1].edad) },
            { assertEquals(personajes[1].arma,result[1].arma) },
            { assertEquals(personajes[1].isDeleted,result[1].isDeleted) }
        )

        verify(exactly = 1) {personajesRepo.findAll()  }
    }

    @Test
    fun findByName() {
        val personaje = Enemigo("Mock3","Mock3",69,45,"Mock3",false)

        every { personajesCache.get("Mock3") } returns personaje
        every { personajesRepo.findByName("Mock3") } returns personaje

        val result=service.findByName("Mock3")
        assertAll(
            { assertEquals(personaje.nombre,result.nombre) },
            { assertEquals(personaje.habilidad,result.habilidad) },
            { assertEquals(personaje.ataque,result.ataque) },
            { assertEquals(personaje.edad,result.edad) },
            { assertEquals(personaje.arma,result.arma) }
        )

        verify(exactly = 1) {personajesCache.get("Mock3")  }
        verify(exactly = 1) {personajesRepo.findByName("Mock3")  }
    }

    @Test
    fun save() {
    }

    @Test
    fun update() {
    }

    @Test
    fun delete() {
    }
}