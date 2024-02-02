package org.example.services

import org.example.exceptions.HeroeException
import org.example.models.Heroe
import org.example.repositories.heroescompany.HeroesMarvelRepository
import org.example.validators.HeroeValidator

class HeroeMarvelServiceImplementacion (
    private val repoMarvel: HeroesMarvelRepository,
    private val validator: HeroeValidator
): HeroeService {
    override fun getAllHeroes(): Array<Heroe> {
        return repoMarvel.getAllHeroes()
    }

    override fun getHeroeById(id: Int): Heroe {
        return repoMarvel.getHeroeById(id) ?: throw HeroeException.NotFound(id)
    }

    override fun saveHeroe(heroe: Heroe): Heroe {
        validator.validate(heroe)
        return repoMarvel.saveHeroe(heroe)
    }

    override fun updateHeroe(id: Int, heroe: Heroe): Heroe {
        validator.validate(heroe)
        return repoMarvel.updateHeroe(id, heroe) ?: throw HeroeException.NotFound(id)
    }

    override fun deleteHeroe(id: Int): Heroe {
        return repoMarvel.deleteHeroe(id) ?: throw HeroeException.NotFound(id)
    }
}