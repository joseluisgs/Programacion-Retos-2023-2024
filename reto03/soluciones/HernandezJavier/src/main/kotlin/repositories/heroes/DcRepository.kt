package org.example.repositories.heroes

import org.example.models.DcHero
import org.example.repositories.base.CrudRepository
import java.time.LocalDateTime
class DcRepository : CrudRepository<DcHero, Int> {
    private var dcheroes: Array<DcHero?> = arrayOfNulls(10)
    private var key = 0

    /**
     * Devuelve todos los registro filtrados por no nulos
     * @return el array con los registros
     */
    override fun getAll(): Array<DcHero?> {
        return dcheroes.filterNotNull().toTypedArray()
    }

    /**
     * Devuelve todos los registro filtrados por no nulos
     * @return el array con los registros
     */
    override fun delete(key: Int): DcHero? {
        var hero: DcHero? = null
        this.dcheroes.indexOfFirst { it?.id == key }
            .takeIf { it != -1 }
            ?.let {
                hero = dcheroes[it]
                dcheroes[it] = null
            }. also {
                if(dcheroes.count(){it == null} > 10){
                    dcheroes.filterNotNull().toTypedArray()
                }
            }
        return hero
    }

    /**
     * Devuelve todos los registro filtrados por no nulos
     * @return el array con los registros
     */
    override fun update(key: Int, value: DcHero): DcHero? {
        return getById(key)?.apply {
            nombre = value.nombre
            alias = value.alias
            altura = value.altura
            edad = value.edad
            notas = value.notas
            updatedAt = LocalDateTime.now()
        }
    }

    /**
     * Guarda un nuevo héroe
     * @param value el héroe a guardar
     * @return el héroe guardado
     */
    override fun save(value: DcHero): DcHero {
        if(dcheroes.count(){it == null} == 0){
            dcheroes = dcheroes.copyOf(dcheroes.size + 10)
        }
        val index = dcheroes.indexOfFirst { it == null }
        key++
        value.id = key
        value.createdAt = LocalDateTime.now()
        dcheroes[index] = value
        return value
    }

    /**
     * Busca por id un registro del array de heroes
     * @param key el id del registro
     */
    override fun getById(key: Int): DcHero? {
        return dcheroes.firstOrNull{it?.id == key}
    }

    /**
     * Inicia un array de héroes para los tests
     */
    fun initRepository(){
        dcheroes = arrayOfNulls<DcHero>(10)
        key = 0
    }

    /**
     * Crea dos registros de prueba para los tests
     */
    fun initExamples(){
        dcheroes[0] = DcHero(id = 1, "Bruce Wayne", "Batman" ,183, 30, "Es la venganza", LocalDateTime.now(), LocalDateTime.now())
        dcheroes[1] = DcHero(id = 2,"Oliver Queen", "Arrow" ,185, 25, "Green Arrow", LocalDateTime.now(), LocalDateTime.now())
        key = 2
    }
}