package org.example.models

import Heroe
import org.example.Controllers.BaseDeDatos

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 * @property dueño Sr.Wayne
 */

class Dc: BaseDeDatos("Sr.Wayne"){

    /**
     * @return las primeras posiciones de la base de Datos
     */
    override fun personasIniciales() {
        baseDeDatos[0] = (Heroe("Bruce Wayne", "Batman", 40, 1.85))
        baseDeDatos[1] = (Heroe("Clark Kent", "Superman", 45, 1.92))
        baseDeDatos[2] = (Heroe("Diana Prince", "Wonder Woman", 300, 1.89))
        baseDeDatos[3] = (Heroe("Barry Allen", "Flash", 35, 1.75))
    }
}

