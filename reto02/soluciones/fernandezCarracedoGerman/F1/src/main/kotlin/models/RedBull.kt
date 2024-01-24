package org.example.models

/**
 * Clase que representa al equipo RedBull
 * Hereda de Equipo e implementa el interfaz VueltaRapida, pero no sobreescribe su método
 * @property nombre Nombre del equipo
 * @see Equipo
 * @see VueltaRapida
 */
class RedBull (nombre:String = "Red Bull"):Equipo(nombre), VueltaRapida{
    val probVueltaRapida:Int = 10
}
