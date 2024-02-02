package org.example.services

import org.example.exceptions.HeroException
import org.example.models.Base.Hero
import org.example.models.DcHero
import org.example.repositories.heroes.DcRepository
import org.example.validators.HeroValidator

class DcHeroServiceImpl(
    private val repository: DcRepository,
    private val validator: HeroValidator
) : DcHeroesService {
    /**
     * Nos da todos los registros del repositorio Dc
     * @return un array con todos los registros
     */
    override fun getAll(): Array<DcHero?> {
        return repository.getAll()
    }

    /**
     * Busca por id a un registro del repositorio DC
     * @param id el id por el que se busca
     * @return el héroe que coincide con el id
     */
    override fun getById(id: Int): DcHero? {
        return repository.getById(id) ?: throw HeroException.NotFound(id)
    }

    /**
     * Guarda un héroe en el repositorio DC
     * @param hero el héroe a guardar
     * @return el héroe guardado
     */
    override fun save(hero: DcHero): DcHero {
        validator.validateHero(hero)
        return repository.save(hero)
    }

    /**
     * Actualiza los parámetros de un héroe en el repositorio de DC
     * @param id el id por el que se busca al registro
     * @param hero el héroe con los cambios actualizados
     * @return el héroe actualizado
     */
    override fun update(id: Int, hero: DcHero): DcHero? {
        validator.validateHero(hero)
        return repository.update(id ,hero)
    }

    /**
     * Elimina a un registro
     * @param id el id por el que se busca el registro
     * @return el héroe eliminado en caso de existir, si no HerException.NotFound
     */
    override fun delete(id: Int): DcHero {
        return repository.delete(id) ?: throw HeroException.NotFound(id)
    }
}