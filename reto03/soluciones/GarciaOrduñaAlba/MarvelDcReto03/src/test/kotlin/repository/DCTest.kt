package repository
import org.example.models.Heroe
import org.example.repository.DC
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * Clase que contiene casos de prueba para la clase DC.
 */
/**
 * Clase de prueba para la clase DC.
 */
class DCTest {
    // Instancia de la clase DC para realizar pruebas
    private val dc = DC()

    /**
     * Prueba la función getHeroes() para asegurar que devuelve correctamente la lista de héroes.
     */
    @Test
    fun testGetHeroes() {
        val heroes = dc.getHeroes()
        assertEquals(5, heroes.size)
        assertEquals("Bruce Wayne", heroes[0].nombre)
        assertEquals("Batman", heroes[0].alias)
        assertEquals(180, heroes[0].altura)
        assertEquals("Protector de Gotham", heroes[0].notas)
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
        val addedHeroe = dc.addHeroe(newHeroe)
        val heroes = dc.getHeroes()
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
        val heroesBeforeDeletion = dc.getHeroes()
        val indexToDelete = 2
        dc.deleteHeroe(heroesBeforeDeletion[indexToDelete].id)
        val heroesAfterDeletion = dc.getHeroes()
        assertArrayEquals(arrayOf(heroesBeforeDeletion[0], heroesBeforeDeletion[1], heroesBeforeDeletion[3], heroesBeforeDeletion[4]), heroesAfterDeletion)
    }

    /**
     * Prueba la función findHeroIndex() para asegurar que encuentra correctamente el índice de un héroe.
     */
    @Test
    fun testFindHeroIndex() {
        assertEquals(-1, dc.findHeroIndex(6))
    }

    /**
     * Prueba la función viewHeroes() para verificar que muestra correctamente la lista de héroes en la consola.
     */
    @Test
    fun testViewHeroes() {
        dc.viewHeroes()
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
        val heroesBeforeModification = dc.getHeroes()
        val indexToModify = 2
        val newName = "Princesa Diana"
        val newAlias = "Guerrera de Themyscira"

        dc.modifyHeroe(indexToModify, Heroe(newName, newAlias, heroesBeforeModification[indexToModify].altura, heroesBeforeModification[indexToModify].edad, heroesBeforeModification[indexToModify].notas, heroesBeforeModification[indexToModify].id))

        val heroesAfterModification = dc.getHeroes()
        assertEquals(newName, heroesAfterModification[indexToModify].nombre)
        assertEquals(newAlias, heroesAfterModification[indexToModify].alias)
    }
}