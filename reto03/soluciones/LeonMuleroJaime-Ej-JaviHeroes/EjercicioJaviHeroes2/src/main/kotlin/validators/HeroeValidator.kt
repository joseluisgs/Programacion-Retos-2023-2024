package org.example.validators

import org.example.exceptions.HeroeException
import org.example.models.Heroe

/**
 * Clase con una función que valida un heroe
 * @author Jaime Leon Mulero
 * @since 1.0.0
 */
class HeroeValidator {
    /**
     * Función para validar un heroe
     * @param heroe heroe recibido
     * @return heroe validado
     * @author Jaime Leon Mulero
     * @since 1.0.0
     */
    fun validate(heroe: Heroe): Heroe {
        val regex = Regex("^[a-zA-Z0-9]{3,15}\$")

        if (heroe.nombre.isEmpty()) {
            throw HeroeException.NombreNotValid("El nombre no puede estar vacío")
        }
        if (heroe.nombre.length !in 3..15) {
            throw HeroeException.NombreNotValid("El nombre debe tener entre 3 y 15 caracteres")
        }
        if (!heroe.nombre.matches(regex)) {
            throw HeroeException.NombreNotValid("El nombre solo puede contener caracteres alfanuméricos")
        }

        if (heroe.alias.isEmpty()) {
            throw HeroeException.AliasNotValid("El alias no puede estar vacío")
        }
        if (heroe.alias.length !in 3..15) {
            throw HeroeException.AliasNotValid("El alias debe tener entre 3 y 15 caracteres")
        }
        if (!heroe.alias.matches(regex)) {
            throw HeroeException.AliasNotValid("El alias solo puede contener caracteres alfanuméricos")
        }

        if (heroe.altura < 1) {
            throw HeroeException.AlturaNotValid("La altura no puede ser negativa")
        }

        if (heroe.edad < 1) {
            throw HeroeException.EdadNotValid("La edad no puede ser negativa")
        }

        return heroe
    }
}