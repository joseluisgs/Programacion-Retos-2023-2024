package org.example.models

/**
 * Clase que representa al equipo Mercedes
 * Hereda de Equipo e implementa el interfaz ErrorEquipo, pero no sobreescribe su método
 * @property nombre Nombre del equipo
 * @see Equipo
 * @see ErrorEquipo
 */
class Mercedes (nombre:String = "Mercedes"): Equipo(nombre), ErrorEquipo{
    val probErrorEquipo:Int = 20
}
