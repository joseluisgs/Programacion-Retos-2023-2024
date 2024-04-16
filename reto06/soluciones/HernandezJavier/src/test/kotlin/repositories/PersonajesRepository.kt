package repositories

import org.example.models.Guerrero
import org.example.models.Personaje
import org.example.repositories.Personajes.PersonajesRepository
import org.example.services.database.DataBaseManager
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonajesRepository {
    private val personajesRepository = PersonajesRepository()

    @BeforeEach
    fun setUp() {
        DataBaseManager.initialize()
    }

    @Test
    fun findAll() {
        val personajes = personajesRepository.findAll()
        assertEquals(4, personajes.size)
    }

    @Test
    fun findByName() {
        val personaje = personajesRepository.getByName("Test1")
        assertEquals("Test1", personaje?.nombre)
    }

    @Test
    fun findByNameNotFound() {
        val personaje = personajesRepository.getByName("Test43")
        assertEquals(null, personaje)
    }

    @Test
    fun save(){
        val personaje = personajesRepository.save(Guerrero("GuerreroTest1", "superTest", 10, 48, "superTest"))
        assertAll(
            { assertEquals("GuerreroTest1", personaje.nombre)},
            { assertEquals("superTest", personaje.habilidad)},
            { assertEquals(48, personaje.edad)}
        )
    }

    @Test
    fun update(){
        val personaje = personajesRepository.update("Test1", Guerrero("Test12", "test12", 99, 100, "MegaTest"))
        assertAll(
            { assertEquals("Test12", personaje?.nombre)},
            { assertEquals("test12", personaje?.habilidad)},
            { assertEquals(99, personaje?.ataque)},
            { assertEquals(100, personaje?.edad)}
        )
    }

    @Test
    fun notUpdated(){
        val personaje = personajesRepository.update("Test202",Guerrero("Test12", "test12", 99, 100, "MegaTest") )
        assertAll(
            { assertEquals(null, personaje)}
        )
    }

    @Test
    fun delete(){
        val personaje = personajesRepository.delete("Test2")
        assertEquals(true, personaje?.isDeleted)
    }

    @Test
    fun notDelete(){
        val personaje = personajesRepository.delete("Test31")
        assertEquals(null, personaje)
    }


}