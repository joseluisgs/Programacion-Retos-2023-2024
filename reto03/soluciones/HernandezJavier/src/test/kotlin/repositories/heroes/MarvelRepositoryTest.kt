package repositories.heroes

import org.example.models.DcHero
import org.example.models.MarvelHero
import org.example.repositories.héroes.MarvelRepository
import org.junit.jupiter.api.*
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MarvelRepositoryTest {
    private val repo = MarvelRepository()

    @BeforeEach
    @Test
    fun setUp(){
        repo.initRepository()
        repo.initExamples()
    }

    @Test
    fun getAll(){
        val heroes = repo.getAll()
        assertAll(
            { assertEquals(2, heroes.size) },
            { assertEquals(1, heroes[0]?.id) },
            { assertEquals("Peter Parker", heroes[0]?.nombre) },
            { assertEquals("Tony Stark", heroes[1]?.nombre) },
        )
    }

    @Test
    fun getById(){
        val heroe = repo.getById(1)
        assertAll(
            { Assertions.assertFalse(heroe == null) },
            { assertEquals(1, heroe?.id)},
            { assertEquals("Peter Parker", heroe?.nombre)}
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
            { Assertions.assertFalse(hero == null) },
            { assertEquals(1, hero?.id) },
            { assertEquals("Peter Parker", hero?.nombre) },
            { assertEquals(1, repo.getAll().size) }
        )
    }

    @Test
    fun deleteNotFound(){
        val hero = repo.delete(3)
        assertAll(
            { assertTrue(hero == null) },
            { assertEquals(2, repo.getAll().size)}
        )
    }

    @Test
    fun update(){
        val hero = repo.update(2,
            MarvelHero(id = 2,"Steve Rogers", "Capitán América" ,185, 30, "Tengo un escudo", LocalDateTime.now(), LocalDateTime.now() )
        )
        assertAll(
            { Assertions.assertFalse(hero == null) },
            { assertEquals("Steve Rogers", hero?.nombre)},
            { assertEquals(2, hero?.id)},
            { assertEquals("Capitán América", hero?.alias)},
            { assertEquals(2, repo.getAll().size)}
        )
    }

    @Test
    fun updateNotFound(){
        val hero = repo.update(3, MarvelHero(1,"Dana White", "White Shark" ,184, 54, "UFC Owner", LocalDateTime.now(), LocalDateTime.now()  ))
        assertAll(
            { assertTrue { hero == null } },
            { assertEquals(2, repo.getAll().size)}
        )
    }

    @Test
    fun save(){
        val hero = repo.save(MarvelHero(id = 3 ,nombre = "Steve Rogers", alias = "Capitán América" , altura =  185, edad =  30, notas = "Tengo un escudo", updatedAt =  LocalDateTime.now(), createdAt =  LocalDateTime.now()))
        assertAll(
            { Assertions.assertFalse(hero == null) },
            { assertEquals(3, hero.id)},
            { assertEquals("Steve Rogers", hero.nombre)},
            { assertEquals(3, repo.getAll().size)}
        )
    }

    @Test
    @DisplayName("Redimensión del repositorio en Save")
    fun repositoryResizedInSave(){
        for(i in 1..10){
            repo.save(MarvelHero(id = 1 ,nombre = "Test", alias = "Test" , altura =  185, edad =  30, notas =  "Soy un test", updatedAt =  LocalDateTime.now(), createdAt =  LocalDateTime.now()))
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
            repo.save(MarvelHero(nombre = "Test", alias = "Test" , altura =  185, edad =  30, notas =  "Soy un test", updatedAt =  LocalDateTime.now(), createdAt =  LocalDateTime.now()))
        }
        for(i in 1..20){
            repo.delete(i)
        }
        val res = repo.getAll()
        assertEquals(12, res.size)
    }
}