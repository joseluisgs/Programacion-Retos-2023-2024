package services

import org.example.repositories.heroescompany.HeroesDCRepository
import org.example.services.HeroeDCServiceImplementacion
import org.example.validators.HeroeValidator
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.example.exceptions.HeroeException
import org.example.models.Heroe
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class HeroeDCServiceImplementacionTest {

    @MockK
    lateinit var heroesDCRepository: HeroesDCRepository

    @MockK
    lateinit var heroeValidator: HeroeValidator

    @InjectMockKs
    lateinit var heroeDCServiceImplementacion: HeroeDCServiceImplementacion

    @Test
    fun getAll() {
        // Arrange
        val heroes = arrayOf(
            Heroe("Test01", "Test01", 100, 20, "Test01"),
            Heroe("Test02", "Test02", 200, 40, "Test02")
        )

        every { heroesDCRepository.getAllHeroes() } returns heroes

        val result = heroeDCServiceImplementacion.getAllHeroes()

        assertAll(
            { assert(result.size == 2) },
            { assert(result[0].id == heroes[0].id) },
            { assert(result[1].id == heroes[1].id) },
            { assert(result[0].nombre == heroes[0].nombre) },
            { assert(result[1].nombre == heroes[1].nombre) },
            { assert(result[0].alias == heroes[0].alias) },
            { assert(result[1].alias == heroes[1].alias) },
            { assert(result[0].altura == heroes[0].altura) },
            { assert(result[1].altura == heroes[1].altura) },
            { assert(result[0].edad == heroes[0].edad) },
            { assert(result[1].edad == heroes[1].edad) },
            { assert(result[0].notas == heroes[0].notas) },
            { assert(result[1].notas == heroes[1].notas) }
        )

        verify(exactly = 1) { heroesDCRepository.getAllHeroes() }
    }

    @Test
    fun getById() {
        val heroe = Heroe("Test01", "Test01", 100, 20, "Test01")

        every { heroesDCRepository.getHeroeById(1) } returns heroe

        val result = heroeDCServiceImplementacion.getHeroeById(1)

        assertAll(
            { assert(result.id == heroe.id) },
            { assert(result.nombre == heroe.nombre) },
            { assert(result.alias == heroe.alias) },
            { assert(result.altura == heroe.altura) },
            { assert(result.edad == heroe.edad) },
            { assert(result.notas == heroe.notas) },
        )

        verify(exactly = 1) { heroesDCRepository.getHeroeById(1) }
    }

    @Test
    fun getByIdNotFound() {
        // Arrange
        every { heroesDCRepository.getHeroeById(1) } returns null

        // Act
        val result = assertThrows<HeroeException.NotFound> {
            heroeDCServiceImplementacion.getHeroeById(1)
        }
        // Assert
        assertEquals("No se ha encontrado ningún héroe con la id 1", result.message)

        verify(exactly = 1) { heroesDCRepository.getHeroeById(1) }
    }

    @Test
    fun save() {
        val heroe = Heroe("Test01", "Test01", 100, 20, "Test01")

        every { heroeValidator.validate(heroe) } returns heroe
        every { heroesDCRepository.saveHeroe(heroe) } returns heroe

        // Act
        val result = heroeDCServiceImplementacion.saveHeroe(heroe)

        // Assert
        assertAll(
            { assert(result.id == heroe.id) },
            { assert(result.nombre == heroe.nombre) },
            { assert(result.alias == heroe.alias) },
            { assert(result.altura == heroe.altura) },
            { assert(result.edad == heroe.edad) },
            { assert(result.notas == heroe.notas) },
        )

        verify(exactly = 1) { heroeValidator.validate(heroe) }
        verify(exactly = 1) { heroesDCRepository.saveHeroe(heroe) }
    }

    @Test
    fun saveNameEmpty() {
        val heroe = Heroe("", "Test01", 100, 20, "Test01")

        every { heroeValidator.validate(heroe) } throws HeroeException.NombreNotValid("El nombre no puede estar vacío")

        val result = assertThrows<HeroeException.NombreNotValid> {
            heroeDCServiceImplementacion.saveHeroe(heroe)
        }

        assertEquals("El nombre no puede estar vacío", result.message)

        verify(exactly = 1) { heroeValidator.validate(heroe) }
        verify(exactly = 0) { heroesDCRepository.saveHeroe(heroe) }
    }

    @Test
    fun saveNameLenght() {
        val heroe = Heroe("TestNumeroCeroUno", "Test01", 100, 20, "Test01")

        every { heroeValidator.validate(heroe) } throws HeroeException.NombreNotValid("El nombre debe tener una longitud entre 3 y 15 caracteres")

        val result = assertThrows<HeroeException.NombreNotValid> {
            heroeDCServiceImplementacion.saveHeroe(heroe)
        }

        assertEquals("El nombre debe tener una longitud entre 3 y 15 caracteres", result.message)

        verify(exactly = 1) { heroeValidator.validate(heroe) }
        verify(exactly = 0) { heroesDCRepository.saveHeroe(heroe) }
    }

    @Test
    fun saveNameRegex() {
        val heroe = Heroe("Test-01", "Test01", 100, 20, "Test01")

        every { heroeValidator.validate(heroe) } throws HeroeException.NombreNotValid("El nombre solo puede contener caracteres alfanuméricos")

        val result = assertThrows<HeroeException.NombreNotValid> {
            heroeDCServiceImplementacion.saveHeroe(heroe)
        }

        assertEquals("El nombre solo puede contener caracteres alfanuméricos", result.message)

        verify(exactly = 1) { heroeValidator.validate(heroe) }
        verify(exactly = 0) { heroesDCRepository.saveHeroe(heroe) }
    }

    @Test
    fun saveAliasEmpty() {
        val heroe = Heroe("Test01", "", 100, 20, "Test01")

        every { heroeValidator.validate(heroe) } throws HeroeException.AliasNotValid("El nombre no es válido")

        val result = assertThrows<HeroeException.AliasNotValid> {
            heroeDCServiceImplementacion.saveHeroe(heroe)
        }

        assertEquals("El nombre no es válido", result.message)

        verify(exactly = 1) { heroeValidator.validate(heroe) }
        verify(exactly = 0) { heroesDCRepository.saveHeroe(heroe) }
    }

    @Test
    fun saveAliasLenght() {
        val heroe = Heroe("Test01", "TestNumeroCeroUno", 100, 20, "Test01")

        every { heroeValidator.validate(heroe) } throws HeroeException.AliasNotValid("El nombre debe tener una longitud entre 3 y 15 caracteres")

        val result = assertThrows<HeroeException.AliasNotValid> {
            heroeDCServiceImplementacion.saveHeroe(heroe)
        }

        assertEquals("El nombre debe tener una longitud entre 3 y 15 caracteres", result.message)

        verify(exactly = 1) { heroeValidator.validate(heroe) }
        verify(exactly = 0) { heroesDCRepository.saveHeroe(heroe) }
    }

    @Test
    fun saveAliasRegex() {
        val heroe = Heroe("Test01", "Test-01", 100, 20, "Test01")

        every { heroeValidator.validate(heroe) } throws HeroeException.AliasNotValid("El nombre solo puede contener caracteres alfanuméricos")

        val result = assertThrows<HeroeException.AliasNotValid> {
            heroeDCServiceImplementacion.saveHeroe(heroe)
        }

        assertEquals("El nombre solo puede contener caracteres alfanuméricos", result.message)

        verify(exactly = 1) { heroeValidator.validate(heroe) }
        verify(exactly = 0) { heroesDCRepository.saveHeroe(heroe) }
    }

    @Test
    fun saveAltura() {
        val heroe = Heroe("Test01", "Test01", -5, 20, "Test01")

        every { heroeValidator.validate(heroe) } throws HeroeException.AlturaNotValid("La altura no puede ser menor que 1")

        val result = assertThrows<HeroeException.AlturaNotValid> {
            heroeDCServiceImplementacion.saveHeroe(heroe)
        }

        assertEquals("La altura no puede ser menor que 1", result.message)

        verify(exactly = 1) { heroeValidator.validate(heroe) }
        verify(exactly = 0) { heroesDCRepository.saveHeroe(heroe) }
    }

    @Test
    fun saveEdad() {
        val heroe = Heroe("Test01", "Test01", 100, -5, "Test01")

        every { heroeValidator.validate(heroe) } throws HeroeException.EdadNotValid("La edad no puede ser menor que 1")

        val result = assertThrows<HeroeException.EdadNotValid> {
            heroeDCServiceImplementacion.saveHeroe(heroe)
        }

        assertEquals("La edad no puede ser menor que 1", result.message)

        verify(exactly = 1) { heroeValidator.validate(heroe) }
        verify(exactly = 0) { heroesDCRepository.saveHeroe(heroe) }
    }

    @Test
    fun update() {
        val persona = Heroe("Test01", "Test01", 100, 20, "Test01")

        every { heroeValidator.validate(persona) } returns persona
        every { heroesDCRepository.updateHeroe(1, persona) } returns persona

        val result = heroeDCServiceImplementacion.updateHeroe(1, persona)

        assertAll(
            { assert(result.id == persona.id) },
            { assert(result.nombre == persona.nombre) },
            { assert(result.alias == persona.alias) },
            { assert(result.altura == persona.altura) },
            { assert(result.edad == persona.edad) }
        )

        verify(exactly = 1) { heroeValidator.validate(persona) }
        verify(exactly = 1) { heroesDCRepository.updateHeroe(1, persona) }
    }

    @Test
    fun updateNameEmpty(){
        val heroe = Heroe("", "Test01", 100, 20, "Test01")

        every { heroeValidator.validate(heroe) } throws HeroeException.NombreNotValid("El nombre no puede estar vacío")

        val result = assertThrows<HeroeException.NombreNotValid> {
            heroeDCServiceImplementacion.updateHeroe(1, heroe)
        }

        assertEquals("El nombre no puede estar vacío", result.message)

        verify(exactly = 1) { heroeValidator.validate(heroe) }
        verify(exactly = 0) { heroesDCRepository.updateHeroe(1, heroe) }
    }

    @Test
    fun updateNameLenght() {
        val heroe = Heroe("TestNumeroCeroUno", "Test01", 100, 20, "Test01")

        every { heroeValidator.validate(heroe) } throws HeroeException.NombreNotValid("El nombre debe tener una longitud entre 3 y 15 caracteres")

        val result = assertThrows<HeroeException.NombreNotValid> {
            heroeDCServiceImplementacion.updateHeroe(1, heroe)
        }

        assertEquals("El nombre debe tener una longitud entre 3 y 15 caracteres", result.message)

        verify(exactly = 1) { heroeValidator.validate(heroe) }
        verify(exactly = 0) { heroesDCRepository.updateHeroe(1, heroe) }
    }

    @Test
    fun updateNameRegex() {
        val heroe = Heroe("Test-01", "Test01", 100, 20, "Test01")

        every { heroeValidator.validate(heroe) } throws HeroeException.NombreNotValid("El nombre solo puede contener caracteres alfanuméricos")

        val result = assertThrows<HeroeException.NombreNotValid> {
            heroeDCServiceImplementacion.updateHeroe(1, heroe)
        }

        assertEquals("El nombre solo puede contener caracteres alfanuméricos", result.message)

        verify(exactly = 1) { heroeValidator.validate(heroe) }
        verify(exactly = 0) { heroesDCRepository.updateHeroe(1, heroe) }
    }

    @Test
    fun updateAliasEmpty() {
        val heroe = Heroe("Test01", "", 100, 20, "Test01")

        every { heroeValidator.validate(heroe) } throws HeroeException.AliasNotValid("El nombre no es válido")

        val result = assertThrows<HeroeException.AliasNotValid> {
            heroeDCServiceImplementacion.updateHeroe(1, heroe)
        }

        assertEquals("El nombre no es válido", result.message)

        verify(exactly = 1) { heroeValidator.validate(heroe) }
        verify(exactly = 0) { heroesDCRepository.updateHeroe(1, heroe) }
    }

    @Test
    fun updateAliasLenght() {
        val heroe = Heroe("Test01", "TestNumeroCeroUno", 100, 20, "Test01")

        every { heroeValidator.validate(heroe) } throws HeroeException.AliasNotValid("El nombre debe tener una longitud entre 3 y 15 caracteres")

        val result = assertThrows<HeroeException.AliasNotValid> {
            heroeDCServiceImplementacion.updateHeroe(1, heroe)
        }

        assertEquals("El nombre debe tener una longitud entre 3 y 15 caracteres", result.message)

        verify(exactly = 1) { heroeValidator.validate(heroe) }
        verify(exactly = 0) { heroesDCRepository.updateHeroe(1, heroe) }
    }

    @Test
    fun updateAliasRegex() {
        val heroe = Heroe("Test01", "Test-01", 100, 20, "Test01")

        every { heroeValidator.validate(heroe) } throws HeroeException.AliasNotValid("El nombre solo puede contener caracteres alfanuméricos")

        val result = assertThrows<HeroeException.AliasNotValid> {
            heroeDCServiceImplementacion.updateHeroe(1, heroe)
        }

        assertEquals("El nombre solo puede contener caracteres alfanuméricos", result.message)

        verify(exactly = 1) { heroeValidator.validate(heroe) }
        verify(exactly = 0) { heroesDCRepository.updateHeroe(1, heroe) }
    }

    @Test
    fun updateAltura() {
        val heroe = Heroe("Test01", "Test01", -5, 20, "Test01")

        every { heroeValidator.validate(heroe) } throws HeroeException.AlturaNotValid("La altura no puede ser menor que 1")

        val result = assertThrows<HeroeException.AlturaNotValid> {
            heroeDCServiceImplementacion.updateHeroe(1, heroe)
        }

        assertEquals("La altura no puede ser menor que 1", result.message)

        verify(exactly = 1) { heroeValidator.validate(heroe) }
        verify(exactly = 0) { heroesDCRepository.updateHeroe(1, heroe) }
    }

    @Test
    fun updateEdad() {
        val heroe = Heroe("Test01", "Test01", 100, -5, "Test01")

        every { heroeValidator.validate(heroe) } throws HeroeException.EdadNotValid("La edad no puede ser menor que 1")

        val result = assertThrows<HeroeException.EdadNotValid> {
            heroeDCServiceImplementacion.updateHeroe(1, heroe)
        }

        assertEquals("La edad no puede ser menor que 1", result.message)

        verify(exactly = 1) { heroeValidator.validate(heroe) }
        verify(exactly = 0) { heroesDCRepository.updateHeroe(1, heroe) }
    }

    @Test
    fun delete() {
        val heroe = Heroe("Test01", "Test01", 100, -5, "Test01")

        every { heroesDCRepository.deleteHeroe(1) } returns heroe

        val result = heroeDCServiceImplementacion.deleteHeroe(1)

        assertAll(
            { assert(result.id == heroe.id) },
            { assert(result.nombre == heroe.nombre) },
            { assert(result.alias == heroe.alias) },
            { assert(result.altura == heroe.altura) },
            { assert(result.edad == heroe.edad) },
        )

        verify(exactly = 1) { heroesDCRepository.deleteHeroe(1) }
    }

    @Test
    fun delenteNotFound(){
        every { heroesDCRepository.deleteHeroe(1) } returns null

        val result = assertThrows<HeroeException.NotFound> {
            heroeDCServiceImplementacion.deleteHeroe(1)
        }

        assertEquals("No se ha encontrado ningún héroe con la id 1", result.message)

        verify(exactly = 1) { heroesDCRepository.deleteHeroe(1) }
    }
}