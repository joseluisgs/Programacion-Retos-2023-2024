package repository

import org.example.models.Heroe
import org.example.repository.DC
import org.example.repository.Marvel
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


/**
 * Clase que contiene casos de prueba para la clase Marvel.
 */
class MarvelTest {
    private val marvel = Marvel()
    private lateinit var repository: Marvel

    /**
     * Prueba la función getHeroes() para asegurar que devuelve correctamente la lista de héroes.
     */
    @Test
    fun testGetHeroes() {
        val heroes = marvel.getAllHeroes()
        assertEquals(5, heroes.size)
        assertEquals("Tony Stark", heroes[0].nombre)
        assertEquals("Ironman", heroes[0].alias)
        assertEquals(180, heroes[0].altura)
        assertEquals("Héroe de hierro", heroes[0].notas)
        assertEquals(1, heroes[0].id)
        val currentTimeMillis = System.currentTimeMillis()
        assertTrue(heroes[0].createdAt in currentTimeMillis - 1000..currentTimeMillis + 1000)
    }

    /**
     * Prueba la función addHeroe() para asegurar que agrega correctamente un nuevo héroe.
     */
    @Test
    fun testAddHeroe() {
        val newHeroe = Heroe("Oliver Queen", "Green Arrow", 185, 75, "Vengador de Star City", 6)
        val addedHeroe = marvel.addHero(newHeroe)
        val heroes = marvel.getAllHeroes()
        assertEquals(6, heroes.size)
        assertEquals(newHeroe.id, addedHeroe.id)
        assertEquals(newHeroe.nombre, addedHeroe.nombre)
        assertEquals(newHeroe.alias, addedHeroe.alias)
        assertEquals(newHeroe.altura, addedHeroe.altura)
        assertEquals(newHeroe.notas, addedHeroe.notas)
        assertEquals(newHeroe.createdAt, addedHeroe.createdAt)
    }

    /**
     * Prueba la función deleteHeroe() para asegurar que elimina correctamente un héroe.
     */
    @Test
    fun testDeleteHeroe() {
        val heroesBeforeDeletion = marvel.getAllHeroes()
        val indexToDelete = 2
        marvel.deleteHero(heroesBeforeDeletion[indexToDelete].id)
        val heroesAfterDeletion = marvel.getAllHeroes()
        assertArrayEquals(arrayOf(heroesBeforeDeletion[0], heroesBeforeDeletion[1], heroesBeforeDeletion[3], heroesBeforeDeletion[4]), heroesAfterDeletion)
    }

    /**
     * Prueba la función findHeroIndex() para asegurar que encuentra correctamente el índice de un héroe.
     */
    @Test
    fun testFindHeroIndex() {
        assertEquals(-1, marvel.getHeroById(6))
    }

    /**
     * Prueba la función viewHeroes() para verificar que muestra correctamente la lista de héroes en la consola.
     */
    @Test
    fun testViewHeroes() {
        marvel.viewHeroes()
        // Verifica que los héroes se muestren correctamente en la consola
    }

    /**
     * Prueba la función menu() para verificar que el menú funcione correctamente y que se puedan realizar las diferentes acciones.
     */
    @Test
    fun testMenu() {
        // Verifica que el menú funcione correctamente y que se puedan realizar las diferentes acciones
    }

    /**
     * Prueba la función modifyHeroe() para asegurar que modifica correctamente un héroe existente.
     */
    @Test
    fun testModifyHeroe() {
        val marvel = Marvel() // Instancia de la clase que contiene la lógica para manejar héroes
        val heroesBeforeModification = marvel.getAllHeroes()
        val indexToModify = 2
        val newName = "Steve Rogers"
        val newAlias = "Capitán América"

        val heroeToModify = heroesBeforeModification[indexToModify]
        val modifiedHeroe = Heroe(newName, newAlias, heroeToModify.altura, heroeToModify.edad, heroeToModify.notas, heroeToModify.id)

        marvel.updateHero(indexToModify, modifiedHeroe)

        val heroesAfterModification = marvel.getAllHeroes()
        assertEquals(newName, heroesAfterModification[indexToModify].nombre)
        assertEquals(newAlias, heroesAfterModification[indexToModify].alias)
    }
}