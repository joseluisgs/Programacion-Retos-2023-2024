package org.example.validators

import org.example.exceptions.HeroException
import org.example.models.Base.Hero

class HeroValidator {
    /**
     * Válida la entrada de un héroe, en caso de que alguna entrada no sea válida se lanzará una excepción
     * @param hero el héroe a comprobar
     */
    fun validateHero(hero: Hero): Hero {
        val nameAliasRegex = Regex("[a-zA-Z0-9 ]{3,50}")
        if(!hero.nombre.matches(nameAliasRegex) || hero.nombre.isEmpty()){
            throw HeroException.NameNotValid()
        }
        if(!hero.alias.matches(nameAliasRegex) || hero.alias.isEmpty()){
            throw HeroException.AliasNotValid()
        }
        val notaRegex = Regex(".{0,150}")
        if(!hero.notas.matches(notaRegex) || hero.notas.isEmpty()){
            throw HeroException.NotaNotValid()
        }
        val alturaRegex = Regex("(?:[1-9]|[1-9][0-9]|[1-4][0-9]{2}|500)")
        if(!hero.altura.toString().matches(alturaRegex)){
            throw HeroException.AlturaNotValid()
        }
        val edadRegex = Regex("(?:[1-9]|[1-9][0-9]|[1-4][0-9]{2}|500)")
        if(!hero.edad.toString().matches(edadRegex)){
            throw HeroException.EdadNotValid()
        }
        return hero
    }
}