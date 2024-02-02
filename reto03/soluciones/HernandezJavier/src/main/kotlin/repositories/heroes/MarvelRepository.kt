package org.example.repositories.héroes

import org.example.models.DcHero
import org.example.models.MarvelHero
import org.example.repositories.base.CrudRepository
import java.time.LocalDateTime

class MarvelRepository: CrudRepository<MarvelHero, Int> {
    private var marvelHeroes: Array<MarvelHero?> = arrayOfNulls<MarvelHero>(10)
    private var key: Int = 0

    /**
     * Devuelve todos los registro filtrados por no nulos
     * @return el array con los registros
     */
    override fun getAll(): Array<MarvelHero?> {
        val heroesNoNulos = Array<MarvelHero?>(marvelHeroes.size - contarNulos()){null}
        var index = 0
        for (i in marvelHeroes.indices){
            if(marvelHeroes[i] != null){
                heroesNoNulos[index] = marvelHeroes[i]!!
                index++
            }
        }
        return heroesNoNulos
    }

    /**
     * Cuuenta los nulos de un array
     * @return los nulos de un array
     */
    private fun contarNulos(): Int {
        var nulos = 0
        for(i in marvelHeroes.indices){
            if(marvelHeroes[i] == null){
                nulos++
            }
        }
        return nulos
    }

    /**
     * Devuelve todos los registro filtrados por no nulos
     * @return el array con los registros
     */
    override fun delete(key: Int): MarvelHero? {
        var heroe: MarvelHero? = null
        var index = -1
        for (i in marvelHeroes.indices){
            if(marvelHeroes[i]?.id == key){
                index = i
            }
        }
        if(index != -1){
            heroe = marvelHeroes[index]
            marvelHeroes[index] = null
        }
        if(contarNulos() > 10){
            marvelHeroes = filtrarArrayPorNoNulos(marvelHeroes)
        }
        return heroe
    }

    /**
     * Filtra el array por no nulos
     * @param array el array que queremos filtrar
     * @return el array filtrado
     */
    private fun filtrarArrayPorNoNulos(array: Array<MarvelHero?>):  Array<MarvelHero?>{
        var newSize = 0
        for (i in array){
            if(i != null){
                newSize++
            }
        }
        val newArray = arrayOfNulls<MarvelHero?>(newSize)
        var index = 0
        for(i in array){
            if(i != null){
                newArray[index] = i
                index++
            }
        }
        return newArray
    }

    /**
     * Devuelve todos los registro filtrados por no nulos
     * @return el array con los registros
     */
    override fun update(key: Int, value: MarvelHero): MarvelHero? {
        val heroe = getById(key)
        if(heroe != null){
            heroe.nombre = value.nombre
            heroe.alias = value.alias
            heroe.altura = value.altura
            heroe.edad = value.edad
            heroe.notas = value.notas
            heroe.updatedAt = LocalDateTime.now()
        }
        return heroe
    }

    /**
     * Guarda un nuevo héroe
     * @param value el héroe a guardar
     * @return el héroe guardado
     */
    override fun save(value: MarvelHero): MarvelHero {
        var nullCount = 0
        for(i in marvelHeroes.indices){
            if(marvelHeroes[i] == null){
                nullCount++
            }
        }
        if(nullCount == 0){
            marvelHeroes = marvelHeroes.copyOf(marvelHeroes.size + 10)
        }

        var index = -1
        for (i in  marvelHeroes.indices){
            if(marvelHeroes[i] == null){
                index = i
            }
        }
        key++
        value.id = key
        if(index != -1){
            marvelHeroes[index] = value
            value.createdAt = LocalDateTime.now()
        }
        return value
    }

    /**
     * Busca por id un registro del array de heroes
     * @param key el id del registro
     */
    override fun getById(key: Int): MarvelHero? {
        for (i in marvelHeroes.indices){
            if(marvelHeroes[i]?.id == key)
                return marvelHeroes[i]
        }
        return null
    }

    /**
     * Inicia un array de héroes para los tests
     */
    fun initRepository(){
        marvelHeroes = arrayOfNulls<MarvelHero>(10)
        key = 0
    }

    /**
     * Crea dos registros de prueba para los tests
     */
    fun initExamples(){
        marvelHeroes[0] = MarvelHero(id = 1, "Peter Parker", "Spiderman" ,183, 20, "Tu vecino y amigo", LocalDateTime.now(), LocalDateTime.now())
        marvelHeroes[1] = MarvelHero(id = 2,"Tony Stark", "Ironman" ,185, 40, "El jefe", LocalDateTime.now(), LocalDateTime.now())
        key = 2
    }
}