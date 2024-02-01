package org.example.models

import Heroe
import org.example.Controllers.BaseDeDatos

/**
 * @author Mario de Domingo
 * @version 1.0-SNAPSHOT
 * @property dueño Sr.Stark
 */
class Marvel: BaseDeDatos("Sr.Stark"){

    /**
     * @return valores de las primeras posiciones de la base de Datos
     */
    override fun personasIniciales() {
        baseDeDatos[0] = Heroe("Steve Rogers", "Captain America", 100, 1.88)
        baseDeDatos[1] = Heroe("Tony Stark", "IronMan", 56, 1.74)
        baseDeDatos[2] = Heroe("Natasha Romanoff", "Black Widow", 35, 1.68)
        baseDeDatos[3] = Heroe("Thor Odinson", "Thor", 1500, 1.95)
    }
}