package controllers

/*import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test*/

import org.example.controllers.MarvelManager
import org.example.marvelManager
import org.example.models.Marvel
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Para que se cree una instancia por clase y no por método
class MarvelManagerTest {

    val db = MarvelManager

    @BeforeAll
    fun setUpAll() {


    }

    @AfterAll
    fun tearDownAll() {
        println("Después de todos los test")
    }

    @BeforeEach
    fun setUp() {
        println("Antes de todos los test")
        /*val shM = Marvel("Tony Stark","Iron Man",180, "Notas Iron Man")
        val shM2: Marvel? = null
        val shDC = Marvel("Clark Kent","Superman",191, "Notas Superman")
        val shDC2: Marvel? = null*/
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    @DisplayName("Inserción de un superhéroe de Marvel")
    fun insertarSuperHeroeDeMarvel() {
        // Arrange
        // tenemos shM y el array dbMarvel
        val posic = 0
        val shM = Marvel("Tony Stark","Iron Man",180, "Notas Iron Man")

        // Act
        marvelManager.insertarSuperHeroe(posic,shM)

        // Assert
        Assertions.assertEquals(shM, marvelManager.dbMarvel[posic])
    }

    @Test
    @DisplayName("Inserción de un superhéroe en una posición incorrecta (Negativa) del repositorio")
    fun insertarSuperHeroeEnPosicionMenorQueCero(){
        TODO("No Implementada")
    }

    @Test
    @DisplayName("Inserción de un superhéroe en una posición incorrecta (mayor que el tamaño del repositorio)")
    fun insertarSuperHeroeEnPosicionMayorQueTamanioRepositorio(){
        TODO("No Implementada")
    }

    @Test
    @DisplayName("Inserción de un superhéroe de cualquier tipo con un objeto null")
    fun insertarSuperHeroeQueEsNull(){
        // Arrange
        val posic = 0

        // Act
        val resul = assertThrows<IllegalArgumentException> { marvelManager.insertarSuperHeroe(posic,null) }

        // Assert
        Assertions.assertEquals("Error. Se ha intentado insertar un superhéroe con datos nulos.",resul.message)
    }
}