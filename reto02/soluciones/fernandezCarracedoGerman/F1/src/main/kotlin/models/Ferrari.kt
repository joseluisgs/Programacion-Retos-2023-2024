package org.example.models

/**
 * Clase que representa al equipo Aston Martin
 * Hereda de Equipo e implementa el interfaz MalaEstrategia, pero no sobreescribe su método
 * @property nombre Nombre del equipo
 * @see Equipo
 * @see MalaEstrategia
 */
class Ferrari(nombre:String = "Ferrari"): Equipo(nombre),MalaEstrategia,ProblemaFiabilidad {
    val probProblemaFiabilidad:Int = 25
    val probMalaEstrategia:Int = 15
}