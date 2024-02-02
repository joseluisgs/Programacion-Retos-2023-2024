package repositories.heroescompany

import org.example.models.Heroe
import org.example.repositories.heroescompany.HeroesDCRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals

class HeroesDCRepositoryTest {

    private val repo = HeroesDCRepository()

    @BeforeEach
    fun setUp() {
        repo.initRepository()
        repo.initExamples()
    }

    @Test
    fun getAllHeroes() {
        val heroes = repo.getAllHeroes()

        assertAll(
            { assertEquals(2, heroes.size) },
            { assertEquals(1, heroes[0].id) },
            { assertEquals(2, heroes[1].id) },
            { assertEquals("Test01DC", heroes[0].nombre) },
            { assertEquals(120, heroes[1].altura) },
            { assertEquals(30, heroes[0].edad) },
            { assertEquals("Test02DC", heroes[1].notas) }
        )
    }

    @Test
    fun getHeroeById() {
        val heroe = repo.getHeroeById(1)

        assertAll(
            { assertFalse(heroe == null) },
            { assertEquals(1, heroe?.id) },
            { assertEquals("Test01DC", heroe?.nombre) }
        )
    }

    @Test
    fun getByIdNotFound() {
        val heroe = repo.getHeroeById(3)

        assertTrue(heroe == null)
    }

    @Test
    fun deleteHeroe() {
        val heroe = repo.deleteHeroe(1)

        assertAll(
            { assertFalse(heroe == null) },
            { assertEquals(1, heroe?.id) },
            { assertEquals("Test01DC", heroe?.nombre) },
            { assertEquals(1, repo.getAllHeroes().size) }
        )
    }

    @Test
    fun deleteNotFound() {
        // Act
        val heroe = repo.deleteHeroe(3)

        // Assert
        assertAll(
            { assertTrue(heroe == null) },
            { assertEquals(2, repo.getAllHeroes().size) }
        )
    }

    @Test
    fun updateHeroe() {
        val heroe = repo.updateHeroe(1, value = Heroe("TestUpdate", "TestUpdate", 100, 20, "TestUpdate"))

        assertAll(
            { assertFalse(heroe == null) },
            { assertEquals(1, heroe?.id) },
            { assertEquals("TestUpdate", heroe?.nombre) },
            { assertEquals("TestUpdate", heroe?.alias) },
            { assertEquals(100, heroe?.altura) },
            { assertEquals(20, heroe?.edad) },
            { assertEquals("TestUpdate", heroe?.notas) },
            { assertEquals(2, repo.getAllHeroes().size) }
        )
    }

    @Test
    fun updateNotFound() {
        // Act
        val heroe = repo.updateHeroe(3, value = Heroe("TestUpdate", "TestUpdate", 100, 20, "TestUpdate"))

        // Assert
        assertAll(
            { assertTrue(heroe == null) },
            { assertEquals(2, repo.getAllHeroes().size) }
        )
    }

    @Test
    fun saveHeroe() {
        val heroe = repo.saveHeroe(Heroe(nombre = "testSave", alias = "testSave", altura = 100, edad = 20, notas = "testSave"))

        // Assert
        assertAll(
            { assertFalse(heroe == null) },
            { assertEquals(3, heroe.id) },
            { assertEquals("testSave", heroe.nombre) },
            { assertEquals("testSave", heroe.alias) },
            { assertEquals(100, heroe.altura) },
            { assertEquals(20, heroe.edad) },
            { assertEquals("testSave", heroe.notas) },
            { assertEquals(3, repo.getAllHeroes().size) }
        )
    }

    @Test
    fun repositoryResizedInSave() {
        for (i in 1..5) {
            repo.saveHeroe(Heroe(nombre = "testSave", alias = "testSave", altura = 100, edad = 20, notas = "testSave"))
        }

        val res = repo.getAllHeroes()

        assertAll(
            { assertEquals(7, res.size) },
            { assertEquals(1, res[0].id) },
            { assertEquals("Test01DC", res[0].nombre) },
            { assertEquals("Test01DC", res[0].alias) },
            { assertEquals(100, res[0].altura) },
            { assertEquals(30, res[0].edad) },
            { assertEquals("Test01DC", res[0].notas) },
            { assertEquals(2, res[1].id) },
            { assertEquals("Test02DC", res[1].nombre) },
            { assertEquals("Test02DC", res[1].alias) },
            { assertEquals(120, res[1].altura) },
            { assertEquals(40, res[1].edad) },
            { assertEquals("Test02DC", res[1].notas) },
            { assertEquals("testSave", res[2].nombre) },
            { assertEquals("testSave", res[2].alias) },
            { assertEquals(100, res[2].altura) },
            { assertEquals(20, res[2].edad) },
            { assertEquals("testSave", res[2].notas) },
            { assertEquals("testSave", res[6].nombre) },
            { assertEquals("testSave", res[6].alias) },
            { assertEquals(100, res[6].altura) },
            { assertEquals(20, res[6].edad) },
            { assertEquals("testSave", res[6].notas) },
        )
    }

    @Test
    fun repositoryResizedInDelete() {
        for (i in 1..15) {
            repo.saveHeroe(Heroe(nombre = "testSave", alias = "testSave", altura = 100, edad = 20, notas = "TestSave"))
        }

        for (i in 1..10) {
            repo.deleteHeroe(i)
        }

        val res = repo.getAllHeroes()

        assertEquals(7, res.size)
    }
}