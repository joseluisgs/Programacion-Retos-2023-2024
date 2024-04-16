package repositories.personaje

import org.example.models.Personaje
import org.example.repositories.personaje.PersonajeRepositoryImpl
import org.example.services.database.DataBaseInitializer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertAll
import kotlin.test.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class PersonajeRepositoryImplTest {

    private val repo = PersonajeRepositoryImpl()

    @BeforeEach
    fun setUp() {
        DataBaseInitializer.initialize()
    }

    @Test
    fun findAll() {
        val personajes = repo.findAll()

        assertEquals(1,personajes.size)
    }

    @Test
    fun findById() {

        val personaje = repo.findById(1)

        assertAll(
            { assertEquals(1,personaje?.id) },
            { assertEquals("TEST",personaje?.nombre) },
            { assertEquals("Guerrero",personaje?.tipo) },
            { assertEquals("Super Guerrero",personaje?.clase) },
            { assertEquals("HAB TEST",personaje?.habilidad) },
            { assertEquals(100,personaje?.ataque) },
            { assertEquals("ARMA TEST",personaje?.arma) },
            { assertEquals(100,personaje?.edad) },
            { assertEquals("2024-04-15",personaje?.createdAt.toString()) },
            { assertEquals("2024-04-15",personaje?.updatedAt.toString()) },
            { assertEquals(false,personaje?.isDeleted) }
        )
    }
    @Test
    fun findByIdNotFund(){
        val personaje = repo.findById(999)
        assertEquals(null, personaje)
    }

    @Test
    fun save() {
        val personaje = repo.save(
            Personaje(
                nombre="Test02",
                tipo="ENEMIGO",
                clase="Clase02",
                habilidad="Habilidad02",
                ataque=200,
                edad=300,
                arma="Arma 02"
            )
        )

        assertAll(
            { assertEquals(2,personaje?.id) },
            { assertEquals("Test02",personaje?.nombre) },
            { assertEquals("ENEMIGO",personaje?.tipo) },
            { assertEquals("Clase02",personaje?.clase) },
            { assertEquals("Habilidad02",personaje?.habilidad) },
            { assertEquals(200,personaje?.ataque) },
            { assertEquals(300,personaje?.edad) },
            { assertEquals("Arma 02",personaje?.arma) }
        )
    }

    @Test
    fun update() {
        val personaje = repo.update(1,
            Personaje(
                id = 1,
                nombre="Test UPDATE",
                tipo="ENEMIGO",
                clase="Clase UPDATE",
                habilidad="Habilidad UPDATE",
                ataque=400,
                edad=500,
                arma="Arma UPDATE"
            )
        )

        assertAll(
            { assertEquals(1,personaje?.id) },
            { assertEquals("Test UPDATE",personaje?.nombre) },
            { assertEquals("ENEMIGO",personaje?.tipo) },
            { assertEquals("Clase UPDATE",personaje?.clase) },
            { assertEquals("Habilidad UPDATE",personaje?.habilidad) },
            { assertEquals(400,personaje?.ataque) },
            { assertEquals(500,personaje?.edad) },
            { assertEquals("Arma UPDATE",personaje?.arma) }
        )
    }

    @Test
    fun updateNotFound() {
        val personaje = repo.update(999,
            Personaje(
                id = 1,
                nombre="Test UPDATE",
                tipo="ENEMIGO",
                clase="Clase UPDATE",
                habilidad="Habilidad UPDATE",
                ataque=400,
                edad=500,
                arma="Arma UPDATE"
            )
        )

        assertEquals(null,personaje)
    }
    @Test
    fun delete() {
        val personaje = repo.delete(1)

        assertAll(
            { assertEquals(1,personaje?.id) },
            { assertEquals("TEST",personaje?.nombre) },
            { assertEquals("Guerrero",personaje?.tipo) },
            { assertEquals("Super Guerrero",personaje?.clase) },
            { assertEquals("HAB TEST",personaje?.habilidad) },
            { assertEquals(100,personaje?.ataque) },
            { assertEquals("ARMA TEST",personaje?.arma) },
            { assertEquals(100,personaje?.edad) },
            { assertEquals("2024-04-15",personaje?.createdAt.toString()) },
            { assertEquals("2024-04-15",personaje?.updatedAt.toString()) },
            { assertEquals(false,personaje?.isDeleted) }
        )
    }
    @Test
    fun deleteNotFound(){
        val personaje = repo.delete(999)
        assertEquals(null,personaje)
    }

    @Test
    fun findByType() {
        val personajes = repo.findByType("Guerrero")
        assertEquals(1,personajes.size)
    }
    @Test
    fun findByTypeNotFound() {
        val personajes = repo.findByType("Otro")
        assertEquals(0,personajes.size)
    }
}