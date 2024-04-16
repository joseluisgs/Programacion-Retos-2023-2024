package repositories.personajes


import models.Enemigo
import models.Guerrero
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance
import services.database.DataBaseManager

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonajesRepositoryImplTest {

    private val repoPersonajes= PersonajesRepositoryImpl()
    @BeforeEach
    fun setUp() {
        DataBaseManager.inizializar()
    }

    @Test
    fun findAll() {
        val lista = repoPersonajes.findAll()
        assertEquals(4, lista.size)
    }

    @Test
    fun findByName() {
        val personaje= repoPersonajes.findByName("Test 1")
        assertEquals("Test 1",personaje?.nombre)
    }

    @Test
    fun save() {
        val personaje=Enemigo("Test 5","Test 5",50,98,"Test 5",false)
        val personajeSave=repoPersonajes.save(personaje)
        assertAll(
            { assertEquals(personaje.nombre,personajeSave.nombre) },
            { assertEquals(personaje.habilidad,personajeSave.habilidad) },
            { assertEquals(personaje.ataque,personajeSave.ataque) },
            { assertEquals(personaje.edad,personajeSave.edad) },
            { assertEquals(personaje.arma,personajeSave.arma) },
            { assertEquals(personaje.isDeleted,personajeSave.isDeleted) }
        )
    }

    @Test
    fun update() {
        val personaje=Guerrero("Test 6","Test 6",50,98,"Test 6",false)
        val personajeUpdate=repoPersonajes.update("Test 4",personaje)
        assertAll(
            { assertEquals(personaje.nombre,personajeUpdate?.nombre) },
            { assertEquals(personaje.habilidad,personajeUpdate?.habilidad) },
            { assertEquals(personaje.ataque,personajeUpdate?.ataque) },
            { assertEquals(personaje.edad,personajeUpdate?.edad) },
            { assertEquals(personaje.arma,personajeUpdate?.arma) },
            { assertEquals(personaje.isDeleted,personajeUpdate?.isDeleted) }
        )

    }

    @Test
    fun delete() {
        val personaje= repoPersonajes.delete("Test 3",false)
        assertEquals("Test 3",personaje?.nombre)
    }
}