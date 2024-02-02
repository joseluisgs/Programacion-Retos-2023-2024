package validators

import org.example.exceptions.HeroException
import org.example.models.Base.Hero
import org.example.validators.HeroValidator
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class HeroValidatorTest {
    private val validator = HeroValidator()

    @Test
    @DisplayName("Validating a Hero with valid data, should reuturn the same Hero")
    fun validateCorrect() {
        val hero = Hero(nombre = "TestHero", alias = "SoyTest", altura = 180, edad = 19, notas = "Soy un test colega")
        val result = validator.validateHero(hero)
        assertEquals(hero, result)
    }

    @Test
    @DisplayName("Validating a Hero without name")
    fun validateWithoutName() {
        val hero = Hero(nombre = "", alias = "SoyTest", altura = 180, edad = 19, notas = "Soy un test colega")
        assertThrows<HeroException.NameNotValid> {
            validator.validateHero(hero)
        }
    }

    @Test
    @DisplayName("Validating with a name under de minimum limit name characters")
    fun validateUnderCharacterNameLimit(){
        val hero = Hero(nombre = "He", alias = "SoyTest", altura = 180, edad = 19, notas = "Soy un test colega")
        assertThrows<HeroException.NameNotValid> {
            validator.validateHero(hero)
        }
    }

    @Test
    @DisplayName("Validating with a name above de maximum limit name characters")
    fun validateAboveCharacterNameLimit(){
        val hero = Hero(nombre = "HolaSoyUnTestLargoDeNaricesYQuieroTenerMasDeCincuentaCaracteres", alias = "SoyTest", altura = 180, edad = 19, notas = "Soy un test colega")
        assertThrows<HeroException.NameNotValid> {
            validator.validateHero(hero)
        }
    }

    @Test
    @DisplayName("Validating a Hero without alias")
    fun validateWithoutAlias() {
        val hero = Hero(nombre = "TestHero", alias = "", altura = 180, edad = 19, notas = "Soy un test colega")
        assertThrows<HeroException.AliasNotValid> {
            validator.validateHero(hero)
        }
    }

    @Test
    @DisplayName("Validating with a name under de minimum limit alias characters")
    fun validateUnderCharacterAliasLimit(){
        val hero = Hero(nombre = "TestHero", alias = "So", altura = 180, edad = 19, notas = "Soy un test colega")
        assertThrows<HeroException.AliasNotValid> {
            validator.validateHero(hero)
        }
    }

    @Test
    @DisplayName("Validating with a name above de maximum limit alias characters")
    fun validateAboveCharacterAliasLimit(){
        val hero = Hero(nombre = "TestHero", alias = "HolaSoyUnTestLargoDeNaricesYQuieroTenerMasDeCincuentaCaracteres", altura = 180, edad = 19, notas = "Soy un test colega")
        assertThrows<HeroException.AliasNotValid> {
            validator.validateHero(hero)
        }
    }

    @Test
    @DisplayName("Validating with a height of 0 cm")
    fun validateWith0Altura(){
        val hero = Hero(nombre = "TestHero", alias = "SoyTest", altura = 0, edad = 19, notas = "Soy un test colega")
        assertThrows<HeroException.AlturaNotValid> {
            validator.validateHero(hero)
        }
    }

    @Test
    fun validateWithHeightAboveLimit(){
        val hero = Hero(nombre = "TestHero", alias = "SoyTest", altura = 501, edad = 19, notas = "Soy un test colega")
        assertThrows<HeroException.AlturaNotValid> {
            validator.validateHero(hero)
        }
    }

    @Test
    fun validateAlturaLimitMax(){
        val hero = Hero(nombre = "TestHero", alias = "SoyTest", altura = 500, edad = 19, notas = "Soy un test colega")
        val res = validator.validateHero(hero)
        assertEquals(hero, res)
    }

    @Test
    fun validateAlturaLimitMin(){
        val hero = Hero(nombre = "TestHero", alias = "SoyTest", altura = 1, edad = 19, notas = "Soy un test colega")
        val res = validator.validateHero(hero)
        assertEquals(hero, res)
    }





    @Test
    @DisplayName("Validating with a edad of 0 ")
    fun validateWith0Edad(){
        val hero = Hero(nombre = "TestHero", alias = "SoyTest", altura = 180, edad = 0, notas = "Soy un test colega")
        assertThrows<HeroException.EdadNotValid> {
            validator.validateHero(hero)
        }
    }

    @Test
    fun validateWithEdadAboveLimit(){
        val hero = Hero(nombre = "TestHero", alias = "SoyTest", altura = 180, edad = 501, notas = "Soy un test colega")
        assertThrows<HeroException.EdadNotValid> {
            validator.validateHero(hero)
        }
    }

    @Test
    fun validateEdadLimitMax(){
        val hero = Hero(nombre = "TestHero", alias = "SoyTest", altura = 180, edad = 500, notas = "Soy un test colega")
        val res = validator.validateHero(hero)
        assertEquals(hero, res)
    }

    @Test
    fun validateEdadLimitMin(){
        val hero = Hero(nombre = "TestHero", alias = "SoyTest", altura = 180, edad = 1, notas = "Soy un test colega")
        val res = validator.validateHero(hero)
        assertEquals(hero, res)
    }


}