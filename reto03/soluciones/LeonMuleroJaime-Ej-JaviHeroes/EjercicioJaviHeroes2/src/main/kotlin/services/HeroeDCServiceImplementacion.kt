package org.example.services

import org.example.exceptions.HeroeException
import org.example.models.Heroe
import org.example.repositories.heroescompany.HeroesDCRepository
import org.example.validators.HeroeValidator

class HeroeDCServiceImplementacion (
    private val repoDC: HeroesDCRepository,
    private val validator: HeroeValidator
): HeroeService {
    override fun getAllHeroes(): Array<Heroe> {
        return repoDC.getAllHeroes()
    }

    override fun getHeroeById(id: Int): Heroe {
        return repoDC.getHeroeById(id) ?: throw HeroeException.NotFound(id)
    }

    override fun saveHeroe(heroe: Heroe): Heroe {
        validator.validate(heroe)
        return repoDC.saveHeroe(heroe)
    }

    override fun updateHeroe(id: Int, heroe: Heroe): Heroe {
        validator.validate(heroe)
        return repoDC.updateHeroe(id, heroe) ?: throw HeroeException.NotFound(id)
    }

    override fun deleteHeroe(id: Int): Heroe {
        return repoDC.deleteHeroe(id) ?: throw HeroeException.NotFound(id)
    }
}