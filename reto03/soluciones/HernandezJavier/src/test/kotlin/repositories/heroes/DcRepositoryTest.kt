package repositories.heroes

import org.example.models.DcHero
import org.example.repositories.heroes.DcRepository
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DcRepositoryTest {
    private val repo = DcRepository()

    @BeforeEach
    fun setUp(){
        repo.initRepository()
        repo.initExamples()
    }

    @Test
    fun getAll(){
        val heroes = repo.getAll()
        assertAll(
            { assertEquals(2, heroes.size)},
            { assertEquals(1, heroes[0]?.id)},
            { assertEquals("Bruce Wayne", heroes[0]?.nombre)},
            { assertEquals("Oliver Queen", heroes[1]?.nombre)},
        )
    }

    @Test
    fun getById(){
        val heroe = repo.getById(1)
        assertAll(
            {assertFalse(heroe == null)},
            { assertEquals(1, heroe?.id)},
            { assertEquals("Bruce Wayne", heroe?.nombre)}
        )
    }

    @Test
    fun getByIdNotFound(){
        val hero = repo.getById(3)
        assertTrue(hero == null)
    }

    @Test
    fun delete(){
        val hero = repo.delete(1)
        assertAll(
            { assertFalse(hero == null) },
            { assertEquals(1, hero?.id) },
            { assertEquals("Bruce Wayne", hero?.nombre) },
            { assertEquals(1, repo.getAll().size) }
        )
    }

    @Test
    fun deleteNotFound(){
        val hero = repo.delete(3)
        assertAll(
            { assertTrue(hero == null)},
            { assertEquals(2, repo.getAll().size)}
        )
    }

    @Test
    fun update(){
        val hero = repo.update(2,
            DcHero(id = 2,"Clark Kent", "Superman" ,185, 30, "Me hace daño la kriptonita", LocalDateTime.now(), LocalDateTime.now() )
        )
        assertAll(
            { assertFalse(hero == null)},
            { assertEquals("Clark Kent", hero?.nombre)},
            { assertEquals(2, hero?.id)},
            { assertEquals("Superman", hero?.alias)},
            { assertEquals(2, repo.getAll().size)}
        )
    }

    @Test
    fun updateNotFound(){
        val hero = repo.update(3, DcHero(1,"Dana White", "White Shark" ,184, 54, "UFC Owner", LocalDateTime.now(), LocalDateTime.now()  ))
        assertAll(
            { assertTrue { hero == null }},
            { assertEquals(2, repo.getAll().size)}
        )
    }

    @Test
    fun save(){
        val hero = repo.save(DcHero(nombre = "Clark Kent", alias = "Superman" , altura =  185, edad =  30, notas =  "Me hace daño la kriptonita", updatedAt =  LocalDateTime.now(), createdAt =  LocalDateTime.now() ))
        assertAll(
            { assertFalse(hero == null)},
            { assertEquals(3, hero.id)},
            { assertEquals("Clark Kent", hero.nombre)},
            { assertEquals(3, repo.getAll().size)}
        )
    }

    @Test
    @DisplayName("Redimensión del repositorio en Save")
    fun repositoryResizedInSave(){
        for(i in 1..10){
            repo.save(DcHero(nombre = "Test", alias = "Test" , altura =  185, edad =  30, notas =  "Soy un test", updatedAt =  LocalDateTime.now(), createdAt =  LocalDateTime.now()))
        }
        val res = repo.getAll()
        assertAll(
            { assertEquals(12, res.size)},
            { assertEquals(1, res[0]?.id)},
            { assertEquals("Test",res[11]?.nombre)}
        )
    }

    @Test
    @DisplayName("Redimensión del repositorio en Delete")
    fun repositoryResizedInDelete(){
        for (i in 1..30){
            repo.save(DcHero(nombre = "Test", alias = "Test" , altura =  185, edad =  30, notas =  "Soy un test", updatedAt =  LocalDateTime.now(), createdAt =  LocalDateTime.now()))
        }
        for(i in 1..20){
            repo.delete(i)
        }
        val res = repo.getAll()
        assertEquals(12, res.size)
    }
}