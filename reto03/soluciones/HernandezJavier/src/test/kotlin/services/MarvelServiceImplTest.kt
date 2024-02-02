package services

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.example.exceptions.HeroException
import org.example.models.DcHero
import org.example.models.MarvelHero
import org.example.repositories.heroes.DcRepository
import org.example.repositories.héroes.MarvelRepository
import org.example.services.DcHeroServiceImpl
import org.example.services.MarvelServiceImpl
import org.example.validators.HeroValidator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class MarvelServiceImplTest {
    @MockK
    lateinit var marvelRepository: MarvelRepository

    @MockK
    lateinit var heroValidator: HeroValidator

    @InjectMockKs
    lateinit var marvelHeroServiceImpl: MarvelServiceImpl

    @Test
    fun getAll(){
        val heroes = arrayOf<MarvelHero?>(
            MarvelHero(id = 1, "Peter Parker", "Spiderman" ,183, 20, "Tu vecino y amigo", LocalDateTime.now(), LocalDateTime.now()),
            MarvelHero(id = 2,"Tony Stark", "Ironman" ,185, 40, "El jefe", LocalDateTime.now(), LocalDateTime.now())
        )
        every { marvelRepository.getAll() } returns heroes

        val result = marvelHeroServiceImpl.getAll()

        assertAll(
            { assert(result.size == 2)},
            { assert(result[0]?.nombre == heroes[0]?.nombre)},
            { assert(result[1]?.nombre == heroes[1]?.nombre)},
            { assert(result[0]?.id == heroes[0]?.id)},
            {assert(result[1]?.id == heroes[1]?.id)}
        )
        verify(exactly = 1) { marvelRepository.getAll() }
    }
    @Test
    fun getById(){
        val hero = MarvelHero(id = 1, "Peter Parker", "Spiderman" ,183, 20, "Tu vecino y amigo", LocalDateTime.now(), LocalDateTime.now())
        every { marvelRepository.getById(1) } returns hero

        val result = marvelHeroServiceImpl.getById(1)

        assertAll(
            { assert(result?.id == hero.id)},
            { assert(result?.nombre == hero.nombre)}
        )
        verify (exactly = 1) { marvelRepository.getById(1) }
    }

    @Test
    fun getByIdNotFound(){
        every { marvelRepository.getById(1) } returns null

        val result = assertThrows<HeroException.NotFound> {
            marvelHeroServiceImpl.getById(1)
        }

        assertEquals("No se ha encontrado al héroe con el id: 1", result.message)
        verify (exactly = 1) { marvelRepository.getById(1) }
    }

    @Test
    fun save(){
        val hero =  MarvelHero(id = 1, "Peter Parker", "Spiderman" ,183, 20, "Tu vecino y amigo", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } returns hero
        every { marvelRepository.save(hero) } returns hero

        val result = marvelHeroServiceImpl.save(hero)

        assertAll(
            { assert(result.id == hero.id) },
            { assert(result.nombre == hero.nombre) },
        )
        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 1) { marvelRepository.save(hero) }
    }

    @Test
    fun saveInvalidName(){
        val hero =   MarvelHero(id = 1, "", "Spiderman" ,183, 20, "Tu vecino y amigo", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.NameNotValid()

        val result = assertThrows<HeroException.NameNotValid> {
            marvelHeroServiceImpl.save(hero)
        }

        assertEquals("El nombre no es válido", result.message)
        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { marvelRepository.save(hero) }
    }

    @Test
    fun saveInvalidAlias(){
        val hero =   MarvelHero(id = 1, "Peter Parker", "" ,183, 20, "Tu vecino y amigo", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.AliasNotValid()

        val result = assertThrows<HeroException.AliasNotValid> {
            marvelHeroServiceImpl.save(hero)
        }

        assertEquals("El alias no es válido", result.message)
        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { marvelRepository.save(hero) }
    }

    @Test
    fun saveInvalidAltura(){
        val hero =   MarvelHero(id = 1, "Peter Parker", "Spiderman" ,0, 20, "Tu vecino y amigo", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.AlturaNotValid()

        val result = assertThrows<HeroException.AlturaNotValid> {
            marvelHeroServiceImpl.save(hero)
        }

        assertEquals("La altura no es válida", result.message)
        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { marvelRepository.save(hero) }
    }

    @Test
    fun saveInvalidNotas(){
        val hero =  MarvelHero(id = 1, "Peter Parker", "Spiderman" ,183, 20, "", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.NotaNotValid()

        val result = assertThrows<HeroException.NotaNotValid> {
            marvelHeroServiceImpl.save(hero)
        }

        assertEquals("La nota no es válida", result.message)
        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { marvelRepository.save(hero) }
    }

    @Test
    fun saveInvalidEdad(){
       val hero = MarvelHero(id = 1, "Peter Parker", "Spiderman" ,183, 0, "Tu vecino y amigo", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.EdadNotValid()

        val result = assertThrows<HeroException.EdadNotValid> {
            marvelHeroServiceImpl.save(hero)
        }

        assertEquals("La edad no es válida", result.message)
        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { marvelRepository.save(hero) }
    }

    @Test
    fun update(){
        val hero =  MarvelHero(id = 1, "Peter tester", "Spiderman" ,183, 20, "Tu vecino y amigo", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } returns hero
        every { marvelRepository.update(1, hero) } returns hero

        // Act
        val result = marvelHeroServiceImpl.update(1, hero)

        // Assert
        assertAll(
            { assert(result?.id == hero.id) },
            { assert(result?.nombre == hero.nombre) },
            { assert(result?.altura == hero.altura) },
        )

        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 1) { marvelRepository.update(1, hero) }
    }

    @Test
    fun updateNameInvalid(){
        val hero =  MarvelHero(id = 1, "", "Spiderman" ,183, 20, "Tu vecino y amigo", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.NameNotValid()

        // Act
        val result = assertThrows<HeroException.NameNotValid> {
            marvelHeroServiceImpl.update(1, hero)
        }

        // Assert
        assertEquals("El nombre no es válido", result.message)

        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { marvelRepository.update(1, hero) }
    }

    @Test
    fun updateInvalidAlias(){
        val hero =  MarvelHero(id = 1, "Peter tester", "" ,183, 20, "Tu vecino y amigo", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.AliasNotValid()

        // Act
        val result = assertThrows<HeroException.AliasNotValid> {
            marvelHeroServiceImpl.update(1, hero)
        }

        // Assert
        assertEquals("El alias no es válido", result.message)

        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { marvelRepository.update(1, hero) }
    }

    @Test
    fun updateInvalidAltura(){
        val hero =  MarvelHero(id = 1, "Peter tester", "Spiderman" ,0, 20, "Tu vecino y amigo", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.AlturaNotValid()

        val result = assertThrows<HeroException.AlturaNotValid> {
            marvelHeroServiceImpl.update(1, hero)
        }

        assertEquals("La altura no es válida", result.message)
        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { marvelRepository.update(1 ,hero) }
    }

    @Test
    fun updateInvalidNota(){
        val hero =  MarvelHero(id = 1, "Peter tester", "Spiderman" ,183, 20, "", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.NotaNotValid()

        val result = assertThrows<HeroException.NotaNotValid> {
            marvelHeroServiceImpl.update(1, hero)
        }

        assertEquals("La nota no es válida", result.message)
        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { marvelRepository.update(1, hero) }
    }

    @Test
    fun updateInvalidEdad(){
        val hero =  MarvelHero(id = 1, "Peter tester", "Spiderman" ,183, 0, "", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.EdadNotValid()

        val result = assertThrows<HeroException.EdadNotValid> {
            marvelHeroServiceImpl.update(1, hero)
        }

        assertEquals("La edad no es válida", result.message)
        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { marvelRepository.update(1, hero) }
    }

    @Test
    fun delete(){
        val hero =  MarvelHero(id = 1, "Peter tester", "Spiderman" ,183, 20, "Tu vecino y amigo", LocalDateTime.now(), LocalDateTime.now())
        every { marvelRepository.delete(1) } returns hero
        val result = marvelHeroServiceImpl.delete(1)
        assertAll(
            { assert(result.id == hero.id) },
            { assert(result.nombre == hero.nombre) },
            { assert(result.altura == hero.altura) },
        )
        verify(exactly = 1) { marvelRepository.delete(1) }
    }

    @Test
    fun deleteNotFound(){
        every { marvelRepository.delete(1) } returns null

        val result = assertThrows<HeroException.NotFound> {
            marvelHeroServiceImpl.delete(1)
        }
        assertEquals("No se ha encontrado al héroe con el id: 1", result.message)

        verify(exactly = 1) { marvelRepository.delete(1) }
    }
}