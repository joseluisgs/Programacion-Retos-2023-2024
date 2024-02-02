package org.example.services

import org.example.exceptions.HeroException
import org.example.models.MarvelHero
import org.example.repositories.heroes.DcRepository
import org.example.repositories.héroes.MarvelRepository
import org.example.validators.HeroValidator

class MarvelServiceImpl(
    private val repository: MarvelRepository,
    private val validator: HeroValidator
) : MarvelHeroesService {

    /**
     * Nos da todos los registros del repositorio Marvel
     * @return un array con todos los registros
     */
    override fun getAll(): Array<MarvelHero?> {
        return repository.getAll()
    }

    /**
     * Busca por id a un registro del repositorio Marvel
     * @param id el id por el que se busca
     * @return el héroe que coincide con el id
     */
    override fun getById(id: Int): MarvelHero? {
        return repository.getById(id) ?: throw HeroException.NotFound(id)
    }

    /**
     * Guarda un héroe en el repositorio Marvel
     * @param hero el héroe a guardar
     * @return el héroe guardado
     */
    override fun save(hero: MarvelHero): MarvelHero {
        validator.validateHero(hero)
        return repository.save(hero)
    }

    /**
     * Actualiza los parámetros de un héroe en el repositorio de Marvel
     * @param id el id por el que se busca al registro
     * @param hero el héroe con los cambios actualizados
     * @return el héroe actualizado
     */
    override fun update(id: Int, hero: MarvelHero): MarvelHero? {
        validator.validateHero(hero)
        return repository.update(id ,hero)
    }

    /**
     * Elimina a un registro
     * @param id el id por el que se busca el registro
     * @return el héroe eliminado en caso de existir, si no HerException.NotFound
     */
    override fun delete(id: Int): MarvelHero {
        return repository.delete(id) ?: throw HeroException.NotFound(id)
    }

}