package validators

import org.junit.jupiter.api.Test
import org.example.exceptions.HeroeException
import org.example.models.Heroe
import org.example.validators.HeroeValidator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows

class HeroeValidatorTest {

    private val validator = HeroeValidator()

    @Test
    fun `heroe correcto`() {
        val heroe = Heroe(nombre = "Parker", alias = "Spiderman", altura = 175, edad = 21, notas = "hombre araña")

        val result = validator.validate(heroe)

        assertEquals(heroe, result)
    }

    @Test
    fun `heroe sin nombre`() {
        val heroe = Heroe(nombre = "", alias = "Spiderman", altura = 175, edad = 21, notas = "hombre araña")

        val res = assertThrows<HeroeException.NombreNotValid> {
            validator.validate(heroe)
        }

        assertEquals("El nombre no puede estar vacío", res.message)
    }

    @Test
    fun `heroe con longitud de nombre fuere de (3-15)`() {
        val heroe = Heroe(nombre = "SpidermanHombreAraña", alias = "Spiderman", altura = 175, edad = 21, notas = "hombre araña")

        val res = assertThrows<HeroeException.NombreNotValid> {
            validator.validate(heroe)
        }

        assertEquals("El nombre debe tener entre 3 y 15 caracteres", res.message)
    }

    @Test
    fun `heroe con caracteres en nombre distintos de alfanumerico`() {
        val heroe = Heroe(nombre = "Spiderman-_-", alias = "Spiderman", altura = 175, edad = 21, notas = "hombre araña")

        val res = assertThrows<HeroeException.NombreNotValid> {
            validator.validate(heroe)
        }

        assertEquals("El nombre solo puede contener caracteres alfanuméricos", res.message)
    }

    @Test
    fun `heroe sin alias`() {
        val heroe = Heroe(nombre = "Parker", alias = "", altura = 175, edad = 21, notas = "hombre araña")

        val res = assertThrows<HeroeException.AliasNotValid> {
            validator.validate(heroe)
        }

        assertEquals("El alias no puede estar vacío", res.message)
    }

    @Test
    fun `heroe con longitud de alias fuere de (3-15)`() {
        val heroe = Heroe(nombre = "Parker", alias = "SpidermanHombreAraña", altura = 175, edad = 21, notas = "hombre araña")

        val res = assertThrows<HeroeException.AliasNotValid> {
            validator.validate(heroe)
        }

        assertEquals("El alias debe tener entre 3 y 15 caracteres", res.message)
    }

    @Test
    fun `heroe con caracteres en alias distintos de alfanumerico`() {
        val heroe = Heroe(nombre = "Parker", alias = "Spiderman-_-", altura = 175, edad = 21, notas = "hombre araña")

        val res = assertThrows<HeroeException.AliasNotValid> {
            validator.validate(heroe)
        }

        assertEquals("El alias solo puede contener caracteres alfanuméricos", res.message)
    }

    @Test
    fun `heroe con altura negativa`() {
        val heroe = Heroe(nombre = "Parker", alias = "Spiderman", altura = -5, edad = 21, notas = "hombre araña")

        val res = assertThrows<HeroeException.AlturaNotValid> {
            validator.validate(heroe)
        }

        assertEquals("La altura no puede ser negativa", res.message)
    }

    @Test
    fun `heroe con edad negativa`() {
        val heroe = Heroe(nombre = "Parker", alias = "Spiderman", altura = 175, edad = -21, notas = "hombre araña")

        val res = assertThrows<HeroeException.EdadNotValid> {
            validator.validate(heroe)
        }

        assertEquals("La edad no puede ser negativa", res.message)
    }
}