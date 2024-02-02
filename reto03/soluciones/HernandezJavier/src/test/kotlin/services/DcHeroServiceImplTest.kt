package services

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.example.exceptions.HeroException
import org.example.models.DcHero
import org.example.repositories.heroes.DcRepository
import org.example.services.DcHeroServiceImpl
import org.example.validators.HeroValidator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class DcHeroServiceImplTest {

    @MockK
    lateinit var dcRepository: DcRepository

    @MockK
    lateinit var heroValidator: HeroValidator

    @InjectMockKs
    lateinit var dcHeroServiceImpl: DcHeroServiceImpl

    @Test
    fun getAll(){
        val heroes = arrayOf<DcHero?>(
            DcHero(id = 1, "Bruce Wayne", "Batman" ,183, 30, "Es la venganza", LocalDateTime.now(), LocalDateTime.now()),
            DcHero(id = 2,"Oliver Queen", "Arrow" ,185, 25, "Green Arrow", LocalDateTime.now(), LocalDateTime.now())
        )
        every { dcRepository.getAll() } returns heroes

        val result = dcHeroServiceImpl.getAll()

        assertAll(
            { assert(result.size == 2)},
            { assert(result[0]?.nombre == heroes[0]?.nombre)},
            { assert(result[1]?.nombre == heroes[1]?.nombre)},
            { assert(result[0]?.id == heroes[0]?.id)},
            {assert(result[1]?.id == heroes[1]?.id)}
        )
        verify(exactly = 1) { dcRepository.getAll() }
    }

    @Test
    fun getById(){
        val hero =  DcHero(id = 1, "Bruce Wayne", "Batman" ,183, 30, "Es la venganza", LocalDateTime.now(), LocalDateTime.now())
        every { dcRepository.getById(1) } returns hero

        val result = dcHeroServiceImpl.getById(1)

        assertAll(
            { assert(result?.id == hero.id)},
            { assert(result?.nombre == hero.nombre)}
        )
        verify (exactly = 1) { dcRepository.getById(1) }
    }

    @Test
    fun getByIdNotFound(){
        every { dcRepository.getById(1) } returns null

        val result = assertThrows<HeroException.NotFound> {
            dcHeroServiceImpl.getById(1)
        }

       assertEquals("No se ha encontrado al héroe con el id: 1", result.message)
        verify (exactly = 1) { dcRepository.getById(1) }
    }

    @Test
    fun save(){
        val hero =  DcHero(id = 1, "Bruce Wayne", "Batman" ,183, 30, "Es la venganza", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } returns hero
        every { dcRepository.save(hero) } returns hero

        val result = dcHeroServiceImpl.save(hero)

        assertAll(
            { assert(result.id == hero.id) },
            { assert(result.nombre == hero.nombre) },
        )
        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 1) { dcRepository.save(hero) }
    }

    @Test
    fun saveInvalidName(){
        val hero =  DcHero(id = 1, "", "Batman" ,183, 30, "Es la venganza", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.NameNotValid()

        val result = assertThrows<HeroException.NameNotValid> {
            dcHeroServiceImpl.save(hero)
        }

        assertEquals("El nombre no es válido", result.message)
        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { dcRepository.save(hero) }
    }

    @Test
    fun saveInvalidAlias(){
        val hero =  DcHero(id = 1, "Bruce Wayne", "" ,183, 30, "Es la venganza", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.AliasNotValid()

        val result = assertThrows<HeroException.AliasNotValid> {
            dcHeroServiceImpl.save(hero)
        }

        assertEquals("El alias no es válido", result.message)
        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { dcRepository.save(hero) }
    }

    @Test
    fun saveInvalidAltura(){
        val hero =  DcHero(id = 1, "Bruce Wayne", "Batman" ,0, 30, "Es la venganza", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.AlturaNotValid()

        val result = assertThrows<HeroException.AlturaNotValid> {
            dcHeroServiceImpl.save(hero)
        }

        assertEquals("La altura no es válida", result.message)
        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { dcRepository.save(hero) }
    }

    @Test
    fun saveInvalidNotas(){
        val hero =  DcHero(id = 1, "Bruce Wayne", "Batman" ,183, 30, "", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.NotaNotValid()

        val result = assertThrows<HeroException.NotaNotValid> {
            dcHeroServiceImpl.save(hero)
        }

        assertEquals("La nota no es válida", result.message)
        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { dcRepository.save(hero) }
    }

    @Test
    fun saveInvalidEdad(){
        val hero =  DcHero(id = 1, "Bruce Wayne", "Batman" ,183, 0, "Es la venganza", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.EdadNotValid()

        val result = assertThrows<HeroException.EdadNotValid> {
            dcHeroServiceImpl.save(hero)
        }

        assertEquals("La edad no es válida", result.message)
        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { dcRepository.save(hero) }
    }

    @Test
    fun update(){
        val hero =  DcHero(id = 1, "Bruce Test", "Batman" ,183, 23, "Es la venganza", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } returns hero
        every { dcRepository.update(1, hero) } returns hero

        // Act
        val result = dcHeroServiceImpl.update(1, hero)

        // Assert
        assertAll(
            { assert(result?.id == hero.id) },
            { assert(result?.nombre == hero.nombre) },
            { assert(result?.altura == hero.altura) },
        )

        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 1) { dcRepository.update(1, hero) }
    }

    @Test
    fun updateNameInvalid(){
        val hero =  DcHero(id = 1, "", "Batman" ,183, 30, "Es la venganza", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.NameNotValid()

        // Act
        val result = assertThrows<HeroException.NameNotValid> {
            dcHeroServiceImpl.update(1, hero)
        }

        // Assert
        assertEquals("El nombre no es válido", result.message)

        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { dcRepository.update(1, hero) }
    }

    @Test
    fun updateInvalidAlias(){
        val hero =  DcHero(id = 1, "Bruce Wayne", "" ,183, 30, "Es la venganza", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.AliasNotValid()

        // Act
        val result = assertThrows<HeroException.AliasNotValid> {
            dcHeroServiceImpl.update(1, hero)
        }

        // Assert
        assertEquals("El alias no es válido", result.message)

        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { dcRepository.update(1, hero) }
    }

    @Test
    fun updateInvalidAltura(){
        val hero =  DcHero(id = 1, "Bruce Wayne", "Batman" ,0, 30, "Es la venganza", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.AlturaNotValid()

        val result = assertThrows<HeroException.AlturaNotValid> {
            dcHeroServiceImpl.update(1, hero)
        }

        assertEquals("La altura no es válida", result.message)
        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { dcRepository.update(1 ,hero) }
    }

    @Test
    fun updateInvalidNota(){
        val hero =  DcHero(id = 1, "Bruce Wayne", "Batman" ,183, 30, "", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.NotaNotValid()

        val result = assertThrows<HeroException.NotaNotValid> {
            dcHeroServiceImpl.update(1, hero)
        }

        assertEquals("La nota no es válida", result.message)
        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { dcRepository.update(1, hero) }
    }

    @Test
    fun updateInvalidEdad(){
        val hero =  DcHero(id = 1, "Bruce Wayne", "Batman" ,183, 0, "Es la venganza", LocalDateTime.now(), LocalDateTime.now())
        every { heroValidator.validateHero(hero) } throws HeroException.EdadNotValid()

        val result = assertThrows<HeroException.EdadNotValid> {
            dcHeroServiceImpl.update(1, hero)
        }

        assertEquals("La edad no es válida", result.message)
        verify(exactly = 1) { heroValidator.validateHero(hero) }
        verify(exactly = 0) { dcRepository.update(1, hero) }
    }

    @Test
    fun delete(){
        val hero =  DcHero(id = 1, "Bruce Test", "Batman" ,183, 23, "Es la venganza", LocalDateTime.now(), LocalDateTime.now())
        every { dcRepository.delete(1) } returns hero
        val result = dcHeroServiceImpl.delete(1)
        assertAll(
            { assert(result.id == hero.id) },
            { assert(result.nombre == hero.nombre) },
            { assert(result.altura == hero.altura) },
        )
        verify(exactly = 1) { dcRepository.delete(1) }
    }

    @Test
    fun deleteNotFound(){
        every { dcRepository.delete(1) } returns null

        val result = assertThrows<HeroException.NotFound> {
            dcHeroServiceImpl.delete(1)
        }
        assertEquals("No se ha encontrado al héroe con el id: 1", result.message)

        verify(exactly = 1) { dcRepository.delete(1) }
    }

}

