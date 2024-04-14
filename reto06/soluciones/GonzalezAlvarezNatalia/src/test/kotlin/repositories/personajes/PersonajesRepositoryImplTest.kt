package repositories.personajes

import org.example.models.Personaje
import org.example.repositories.personajes.PersonajesRepositoryImpl
import org.example.services.database.DataBaseInitializer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class PersonajesRepositoryImplTest {

    private var repositorio = PersonajesRepositoryImpl()

    @BeforeEach
    fun setUp() {
        DataBaseInitializer.initialize()
    }

    @Test
    fun findAll() {
        val personajes = repositorio.findAll()
        assertEquals(1, personajes.size)
    }

    @Test
    fun findById() {
        val personaje = repositorio.findById(1)
        assertAll(
            { assertEquals(1, personaje?.id) },
            { assertEquals("Guerrero", personaje?.tipo) },
            { assertEquals("PersonajePrueba", personaje?.nombre)},
            { assertEquals("Habilidad prueba", personaje?.habilidad) },
            { assertEquals("100", personaje?.ataque) },
            { assertEquals(46, personaje?.edad)},
            { assertEquals("Arma prueba", personaje?.arma) }
        )
    }

    @Test
    fun findByIdNotFound() {
        val personaje = repositorio.findById(100)
        assertEquals(null, personaje)
    }

    @Test
    fun findByTipo() {
        val personajes = repositorio.findByTipo("Guerrero")
        assertEquals(1, personajes.size)
    }

    @Test
    fun findByTipoNotFound() {
        val personajes = repositorio.findByTipo("Enemigo")
        assertEquals(0, personajes.size)
    }

    @Test
    fun save() {
        val personaje = repositorio.save(
            Personaje(
                tipo = "Guerrero",
                nombre = "PersonajePrueba2",
                habilidad = "Habilidad prueba2",
                ataque = "200",
                edad = 78,
                arma = "Arma prueba2"
            )
        )
        assertAll(
            { assertEquals("Guerrero", personaje.tipo) },
            { assertEquals("PersonajePrueba2", personaje.nombre)},
            { assertEquals("Habilidad prueba2", personaje.habilidad) },
            { assertEquals("200", personaje.ataque) },
            { assertEquals(78, personaje.edad)},
            { assertEquals("Arma prueba2", personaje.arma) }
        )
    }

    @Test
    fun update() {
        val personaje = repositorio.update(
            1,
            Personaje(
                id = 1,
                tipo = "Guerrero",
                nombre = "PersonajePrueba cambio",
                habilidad = "Habilidad prueba cambio",
                ataque = "500",
                edad = 70,
                arma = "Arma prueba cambio"
            )
        )
        assertAll(
            { assertEquals(1, personaje?.id) },
            { assertEquals("Guerrero", personaje?.tipo) },
            { assertEquals("PersonajePrueba cambio", personaje?.nombre)},
            { assertEquals("Habilidad prueba cambio", personaje?.habilidad) },
            { assertEquals("500", personaje?.ataque) },
            { assertEquals(70, personaje?.edad)},
            { assertEquals("Arma prueba cambio", personaje?.arma) }
        )
    }

    @Test
    fun updateNotFound() {
        val personaje = repositorio.update(
            -1,
            Personaje(
                id = -1,
                tipo = "Guerrero",
                nombre = "PersonajePrueba",
                habilidad = "Habilidad prueba",
                ataque = "100",
                edad = 46,
                arma = "Arma prueba"
            )
        )
        assertEquals(null, personaje)
    }

    @Test
    fun delete() {
        val personaje = repositorio.delete(1)
        assertAll(
            { assertEquals(1, personaje?.id) },
            { assertEquals("Guerrero", personaje?.tipo) },
            { assertEquals("PersonajePrueba", personaje?.nombre)},
            { assertEquals("Habilidad prueba", personaje?.habilidad) },
            { assertEquals("100", personaje?.ataque) },
            { assertEquals(46, personaje?.edad)},
            { assertEquals("Arma prueba", personaje?.arma) }
        )
    }

    @Test
    fun deleteNotFound() {
        val personaje = repositorio.delete(-1)
        assertEquals(null, personaje)
    }
}