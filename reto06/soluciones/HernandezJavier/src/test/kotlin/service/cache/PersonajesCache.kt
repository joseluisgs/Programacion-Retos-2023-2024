package service.cache

import org.example.config.Config
import org.example.models.Enemigo
import org.example.models.Guerrero
import org.example.services.cache.errors.CacheError
import org.example.services.cache.personajes.PersonajesCache

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonajesCache {
    private val cache = PersonajesCache(size = Config.cacheSize)

    @BeforeEach
    fun init(){
        val mocKPersonaje = Guerrero("PersonajeTest1","PersonajeExtra",10,30,"Extra1",false)
        cache.put(mocKPersonaje.nombre, mocKPersonaje)
    }
    @Test
    fun get(){
        val result = cache.get("PersonajeTest1").value
        assertEquals("PersonajeTest1", result.nombre)
    }

    @Test
    fun notGet(){
        val error = cache.get("TestFail").error
        assertAll(
            { assertTrue { error is CacheError }}
        )
    }

    @Test
    fun put(){
        val mockPersonaje = Enemigo("PersonajeTest3","PersonajeExtra",10,30,"Extra3",false)
        val result = cache.put(mockPersonaje.nombre, mockPersonaje).value
        assertAll(
            { assertEquals("PersonajeTest3", result.nombre)},
            { assertEquals(10, result.ataque)},
            { assertEquals("Extra3", result.arma)}
        )
    }

    @Test
    fun putAlreadyIn(){
        val mocKPersonaje = Guerrero("PersonajeTest1","PersonajeExtra",10,30,"Extra1",false)
        val result = cache.put(mocKPersonaje.nombre, mocKPersonaje).value
        assertAll(
            { assertEquals("PersonajeTest1", result.nombre)},
            { assertEquals("Extra1", result.arma)}
        )
    }

    @Test
    fun remove(){
        val mocKPersonaje = Guerrero("PersonajeTest1","PersonajeExtra",10,30,"Extra1",false)
        val result = cache.remove(mocKPersonaje.nombre).value
        val result2 = cache.get("PersonajeTest1").error
        assertAll(
            { assertEquals("PersonajeTest1", result.nombre)},
            { assertTrue { result2 is CacheError }}
        )
    }

    @Test
    fun notRemove(){
        val error = cache.remove("SoyError").error
        assertTrue { error is CacheError }
    }

    @Test
    fun clear(){
        val result = cache.clear().value
        val error = cache.get("PersonajeTest1").error
        assertAll(
            { assertEquals(Unit, result)},
            { assertTrue { error is CacheError } }
        )
    }


}