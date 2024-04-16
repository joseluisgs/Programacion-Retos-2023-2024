package services.personaje

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.example.cache.Cache
import org.example.exceptions.personajes.PersonajesException
import org.example.models.Personaje
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

import org.example.repositories.personaje.PersonajeRepository
import org.example.services.personaje.PersonajesServiceImpl
import org.example.services.storage.base.Storage
import org.example.validators.personaje.PersonajesValidator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)

class PersonajesServiceImplTest {

    @MockK
    private lateinit var mockRepo:PersonajeRepository

    @MockK
    private lateinit var mockStorageCsv:Storage<Personaje>

    @MockK
    private lateinit var mockStorageJson:Storage<Personaje>

    @MockK
    private lateinit var mockCache: Cache<Personaje, Long>

    @MockK
    private lateinit var mockValidator: PersonajesValidator

    @InjectMockKs
    private lateinit var service: PersonajesServiceImpl




    @Test
    fun findAll() {
        val expectedPersonajes = listOf(Personaje(
            nombre="Test01",
            tipo="ENEMIGO",
            clase="Clase01",
            habilidad="Habilidad01",
            ataque=200,
            edad=300,
            arma="Arma 01"
        ))
        every { mockRepo.findAll() } returns expectedPersonajes

        val actualPersonajes = service.findAll()

        assertEquals(expectedPersonajes,actualPersonajes)

        verify(exactly = 1) { mockRepo.findAll() }
    }

    @Test
    fun findByIdCacheFound(){
        val expectedPersonaje = Personaje(
            id=1,
            nombre="Test01",
            tipo="ENEMIGO",
            clase="Clase01",
            habilidad="Habilidad01",
            ataque=200,
            edad=300,
            arma="Arma 01")

        every { mockCache.get(1) } returns expectedPersonaje

        val actualPersonaje = service.findById(1)

        assertEquals(expectedPersonaje,actualPersonaje)

        verify(exactly = 1) {mockCache.get(1)}
        verify (exactly = 0) {mockRepo.findById(1 )}
    }

    @Test
    // No encuentra en caché y sí en repositorio
    fun findByIdRepositoryFound(){
        val expectedPersonaje = Personaje(
            id=1,
            nombre="Test01",
            tipo="ENEMIGO",
            clase="Clase01",
            habilidad="Habilidad01",
            ataque=200,
            edad=300,
            arma="Arma 01")

        every { mockCache.get(1) } returns null
        every { mockRepo.findById(1) } returns expectedPersonaje
        every { mockCache.put(1, expectedPersonaje) } returns Unit

        val actualPersonaje = service.findById(1)

        assertEquals(expectedPersonaje,actualPersonaje)

        verify(exactly = 1) {mockCache.get(1)}
        verify(exactly = 1) {mockRepo.findById(1)}
        verify(exactly = 1) {mockCache.put(1,expectedPersonaje)}
    }

    @Test
    fun findByIdNotFound() {

        every { mockCache.get(1) } returns null
        every { mockRepo.findById(1) } returns null

        val resul = assertThrows<PersonajesException.PersonajeNotFound> {
            service.findById(1)
        }

        assertEquals("Personaje no encontrado con id 1",resul.message)

        verify(exactly = 1) { mockCache.get(1) }
        verify(exactly = 1) { mockRepo.findById(1) }
    }

    @Test
    fun saveOk() {
        val personaje = Personaje(
            id=1,
            nombre="Test01",
            tipo="ENEMIGO",
            clase="Clase01",
            habilidad="Habilidad01",
            ataque=200,
            edad=300,
            arma="Arma 01")

        every { mockValidator.validate(personaje) } returns Unit
        every { mockRepo.save(personaje) } returns personaje
        every { mockCache.put(1,personaje) } returns Unit

        val actualPersonaje = service.save(personaje)

        assertEquals(personaje,actualPersonaje)

        verify(exactly = 1) { mockRepo.save(personaje) }
        verify(exactly = 1) { mockCache.put(1,personaje) }
        verify(exactly = 1) { mockValidator.validate(personaje) }

    }

    @Test
    fun `save Validate Ok but Save Exception`(){
        val personaje = Personaje(
            id=1,
            nombre="Test01",
            tipo="ENEMIGO",
            clase="Clase01",
            habilidad="Habilidad01",
            ataque=200,
            edad=300,
            arma="Arma 01")

        every { mockValidator.validate(personaje) } returns Unit
        every { mockRepo.save(personaje) } throws PersonajesException.PersonajeNotSavedException ("Error al guardar personaje $personaje")
        every { mockCache.put(1,personaje) } returns Unit

        val resul = assertThrows<PersonajesException.PersonajeNotSavedException> {
            service.save(personaje)
        }

        assertEquals("Error al guardar personaje $personaje",resul.message)
        verify (exactly = 1) { mockValidator.validate(personaje)  }
        verify (exactly = 1) { mockRepo.save(personaje)  }
        verify (exactly = 0) {mockCache.put(1,personaje)  }

    }

    @Test
    fun saveNombreNotValid(){
        val personaje = Personaje(
            id=1,
            nombre="",
            tipo="ENEMIGO",
            clase="Clase01",
            habilidad="Habilidad01",
            ataque=200,
            edad=300,
            arma="Arma 01")

        every { mockValidator.validate(personaje) } throws
                PersonajesException.PersonajeNotValid("El nombre del personaje no es válido, debe tener entre 4 y 50 caracteres")

        val resul = assertThrows<PersonajesException.PersonajeNotValid> {
            service.save(personaje)
        }

        assertEquals("El nombre del personaje no es válido, debe tener entre 4 y 50 caracteres",resul.message)
        verify (exactly = 1) { mockValidator.validate(personaje)  }
        verify (exactly = 0) { mockRepo.save(personaje)  }
        verify (exactly = 0) {mockCache.put(1,personaje)  }

    }

    @Test
    fun saveTipoNotValid(){
        val personaje = Personaje(
            id=1,
            nombre="Test",
            tipo="",
            clase="Clase01",
            habilidad="Habilidad01",
            ataque=200,
            edad=300,
            arma="Arma 01")

        every { mockValidator.validate(personaje) } throws
                PersonajesException.PersonajeNotValid("El tipo del personaje no es válido, solo puede ser 'Guerrero' o 'Enemigo'")

        val resul = assertThrows<PersonajesException.PersonajeNotValid> {
            service.save(personaje)
        }

        assertEquals("El tipo del personaje no es válido, solo puede ser 'Guerrero' o 'Enemigo'",resul.message)
        verify (exactly = 1) { mockValidator.validate(personaje)  }
        verify (exactly = 0) { mockRepo.save(personaje)  }
        verify (exactly = 0) {mockCache.put(1,personaje)  }

    }

    @Test
    fun saveClaseNotValid(){
        val personaje = Personaje(
            id=1,
            nombre="Test",
            tipo="GUERRERO",
            clase="",
            habilidad="Habilidad01",
            ataque=200,
            edad=300,
            arma="Arma 01")

        every { mockValidator.validate(personaje) } throws
                PersonajesException.PersonajeNotValid("La clase del personaje no es válida, debe ser vacía o si no, tener entre 4 y 40 caracteres")

        val resul = assertThrows<PersonajesException.PersonajeNotValid> {
            service.save(personaje)
        }

        assertEquals("La clase del personaje no es válida, debe ser vacía o si no, tener entre 4 y 40 caracteres",resul.message)
        verify (exactly = 1) { mockValidator.validate(personaje)  }
        verify (exactly = 0) { mockRepo.save(personaje)  }
        verify (exactly = 0) {mockCache.put(1,personaje)  }

    }

    @Test
    fun saveAtaqueNotValid(){
        val personaje = Personaje(
            id=1,
            nombre="Test",
            tipo="GUERRERO",
            clase="Clase01",
            habilidad="Habilidad01",
            ataque=0,
            edad=300,
            arma="Arma 01")

        every { mockValidator.validate(personaje) } throws
                PersonajesException.PersonajeNotValid("El ataque del personaje no es válido, debe estar entre 1 y ${Int.MAX_VALUE}")

        val resul = assertThrows<PersonajesException.PersonajeNotValid> {
            service.save(personaje)
        }

        assertEquals("El ataque del personaje no es válido, debe estar entre 1 y ${Int.MAX_VALUE}",resul.message)
        verify (exactly = 1) { mockValidator.validate(personaje)  }
        verify (exactly = 0) { mockRepo.save(personaje)  }
        verify (exactly = 0) {mockCache.put(1,personaje)  }

    }

    @Test
    fun saveEdadNotValid(){
        val personaje = Personaje(
            id=1,
            nombre="Test",
            tipo="GUERRERO",
            clase="Clase01",
            habilidad="Habilidad01",
            ataque=200,
            edad=0,
            arma="Arma 01")

        every { mockValidator.validate(personaje) } throws
                PersonajesException.PersonajeNotValid("La edad del personaje no es válida, debe tener entre 1 y 5000 años")

        val resul = assertThrows<PersonajesException.PersonajeNotValid> {
            service.save(personaje)
        }

        assertEquals("La edad del personaje no es válida, debe tener entre 1 y 5000 años",resul.message)
        verify (exactly = 1) { mockValidator.validate(personaje)  }
        verify (exactly = 0) { mockRepo.save(personaje)  }
        verify (exactly = 0) {mockCache.put(1,personaje)  }

    }

    @Test
    fun saveArmaNotValid(){
        val personaje = Personaje(
            id=1,
            nombre="Test",
            tipo="GUERRERO",
            clase="Clase01",
            habilidad="Habilidad01",
            ataque=200,
            edad=100,
            arma="")

        every { mockValidator.validate(personaje) } throws
                PersonajesException.PersonajeNotValid("El arma del personaje no es válida, debe tener entre 4 y 50 caracteres")

        val resul = assertThrows<PersonajesException.PersonajeNotValid> {
            service.save(personaje)
        }

        assertEquals("El arma del personaje no es válida, debe tener entre 4 y 50 caracteres",resul.message)
        verify (exactly = 1) { mockValidator.validate(personaje)  }
        verify (exactly = 0) { mockRepo.save(personaje)  }
        verify (exactly = 0) {mockCache.put(1,personaje)  }

    }

    @Test
    fun saveHabilidadNotValid(){
        val personaje = Personaje(
            id=1,
            nombre="Test",
            tipo="GUERRERO",
            clase="Clase01",
            habilidad="",
            ataque=200,
            edad=100,
            arma="Armma 01")

        every { mockValidator.validate(personaje) } throws
                PersonajesException.PersonajeNotValid("La habilidad del personaje no es válida, debe tener entre 5 y 50 caracteres")

        val resul = assertThrows<PersonajesException.PersonajeNotValid> {
            service.save(personaje)
        }

        assertEquals("La habilidad del personaje no es válida, debe tener entre 5 y 50 caracteres",resul.message)
        verify (exactly = 1) { mockValidator.validate(personaje)  }
        verify (exactly = 0) { mockRepo.save(personaje)  }
        verify (exactly = 0) {mockCache.put(1,personaje)  }

    }


    @Test
    fun updateOk() {
        val updatedPersonaje = Personaje(
            id=1,
            nombre="Test modif",
            tipo="GUERRERO",
            clase="Clase modif",
            habilidad="Habilidad modif",
            ataque=100,
            edad=50,
            arma="Arma modif")

            //validate, update, put
        every { mockValidator.validate(updatedPersonaje) } returns Unit
        every { mockRepo.update(1,updatedPersonaje) } returns updatedPersonaje
        every { mockCache.put(1,updatedPersonaje) } returns Unit

        val personaje = service.update(1,updatedPersonaje)

        assertEquals(personaje,updatedPersonaje)

        verify (exactly = 1) { mockValidator.validate(updatedPersonaje) }
        verify (exactly = 1) { mockRepo.update(1,updatedPersonaje) }
        verify (exactly = 1) { mockCache.put(1,updatedPersonaje) }

    }

    @Test
    fun updatePersonajeNotUpdated(){
        val updatedPersonaje = Personaje(
            id=1,
            nombre="Test modif",
            tipo="GUERRERO",
            clase="Clase modif",
            habilidad="Habilidad modif",
            ataque=100,
            edad=50,
            arma="Arma modif")

        //validate, update, put
        every { mockValidator.validate(updatedPersonaje) } returns Unit
        every { mockRepo.update(1,updatedPersonaje) }  throws PersonajesException.PersonajeNotUpdatedException ("Personaje no se pudo modificar, id ${updatedPersonaje.id}")
        every { mockCache.put(1,updatedPersonaje) } returns Unit

        val resul = assertThrows<PersonajesException.PersonajeNotUpdatedException> {
            service.update(1,updatedPersonaje)
        }

        assertEquals("Personaje no se pudo modificar, id 1",resul.message)

        verify (exactly = 1) { mockValidator.validate(updatedPersonaje) }
        verify (exactly = 1) { mockRepo.update(1,updatedPersonaje) }
        verify (exactly = 0) { mockCache.put(1,updatedPersonaje) }


    }

    // TODO completar los update y demás, y casos posibles: ojo a los saveNombreNoValido (cuando el validador detecta error en save y update)
    @Test
    fun deleteOk() {
        val personajeDelete = Personaje(
            id=1,
            nombre="Test01",
            tipo="ENEMIGO",
            clase="Clase01",
            habilidad="Habilidad01",
            ataque=200,
            edad=300,
            arma="Arma 01")

        every { mockCache.remove(personajeDelete.id) } returns Unit
        every { mockRepo.delete(personajeDelete.id) } returns personajeDelete

        val personaje = service.delete(personajeDelete.id)

        assertEquals(personajeDelete,personaje)

        verify (exactly = 1) { mockRepo.delete(personajeDelete.id) }
        verify (exactly = 1) { mockCache.remove(personajeDelete.id) }

    }

    @Test
    fun deletePersonajeNotFound(){
        val personajeDelete = Personaje(
            id=1,
            nombre="Test01",
            tipo="ENEMIGO",
            clase="Clase01",
            habilidad="Habilidad01",
            ataque=200,
            edad=300,
            arma="Arma 01")

        every { mockCache.remove(personajeDelete.id) } returns Unit
        every { mockRepo.delete(personajeDelete.id) } throws PersonajesException.PersonajeNotDeletedException ("Error al borrar personaje con id ${personajeDelete.id}")

        val resul = assertThrows<PersonajesException.PersonajeNotDeletedException> {
            service.delete(personajeDelete.id) }

        assertEquals("Error al borrar personaje con id ${personajeDelete.id}", resul.message)

        verify (exactly = 1) { mockRepo.delete(personajeDelete.id) }
        verify (exactly = 0) { mockCache.remove(personajeDelete.id) }

    }
    @Test
    fun findByType() {
        val expectedPersonajes = listOf(Personaje(
            nombre="Test01",
            tipo="ENEMIGO",
            clase="Clase01",
            habilidad="Habilidad01",
            ataque=200,
            edad=300,
            arma="Arma 01"
        ))
        every { mockRepo.findByType("ENEMIGO") } returns expectedPersonajes

        val actualPersonajes = service.findByType("ENEMIGO")

        assertEquals(expectedPersonajes,actualPersonajes)

        verify(exactly = 1) { mockRepo.findByType("ENEMIGO") }
    }

    @Test
    fun findByTypeNotFound(){

        every {mockRepo.findByType("ENEMIGO")} throws PersonajesException.PersonajeNotFetchedException("Error buscando personajes por tipo ENEMIGO")

        val resul = assertThrows<PersonajesException.PersonajeNotFetchedException> {
            service.findByType("ENEMIGO")
        }
        assertEquals("Error buscando personajes por tipo ENEMIGO",resul.message)

        verify (exactly = 1) { mockRepo.findByType("ENEMIGO") }
    }
    @Test
    fun storeToCsv() {
    }

    @Test
    fun storeToJson() {
    }

    @Test
    fun loadFromCsv() {
    }

    @Test
    fun loadFromJson() {
    }
}