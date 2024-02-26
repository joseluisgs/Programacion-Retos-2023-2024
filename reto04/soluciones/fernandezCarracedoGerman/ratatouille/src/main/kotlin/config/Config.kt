package org.example.config
import org.lighthousegames.logging.logging
import java.util.*
private val log = logging()

/**
 * Clase de instancia única con los parámetros de configuración cargados de un fichero
 */
object Config {
    var numClientesDia:Int = 10
    var pausaTiempo:Int = 5
    var numFilasRestaurante:Int = 7
    var numColumnasRestaurante:Int = 3
    var tiempoSimulacion:Int = 1000
    var tiempoEntradaNuevoCliente:Int = 3000
    var probEntrarClientes:Int = 70
    var probCamareroCae:Int = 10
    var probClienteInfluenciadoValoracionMenu:Int = 20
    var probSaleRataPasillo:Int = 20
    var probSaleRataSalon:Int = 5

    init {
        val properties = Properties()

        properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))
        log.debug { "Cargando configuración" }

        numClientesDia = properties.getOrDefault("controller.restaurante.numClientesDia", numClientesDia).toString().toInt()
        pausaTiempo = properties.getOrDefault("controller.pausaTiempo", pausaTiempo).toString().toInt()
        numFilasRestaurante = properties.getOrDefault("controller.restaurante.numFilasRestaurante", numFilasRestaurante).toString().toInt()
        numColumnasRestaurante = properties.getOrDefault("controller.restaurante.numColumnasRestaurante",
            numColumnasRestaurante).toString().toInt()
        probEntrarClientes = properties.getOrDefault("controller.restaurante.probEntrarClientes", probEntrarClientes).toString().toInt()
        probCamareroCae = properties.getOrDefault("controller.restaurante.probCamareroCae", probCamareroCae).toString().toInt()
        probClienteInfluenciadoValoracionMenu = properties.getOrDefault("controller.restaurante.probClienteInfluenciadoValoracionMenu",probClienteInfluenciadoValoracionMenu).toString().toInt()
        probSaleRataPasillo = properties.getOrDefault("controller.restaurante.probSaleRataPasillo",probSaleRataPasillo).toString().toInt()
        probSaleRataSalon = properties.getOrDefault("controller.restaurante.probSaleRataSalon",probSaleRataSalon).toString().toInt()
        tiempoSimulacion = properties.getOrDefault("controller.tiempoSimulacion", tiempoSimulacion).toString().toInt()
        tiempoEntradaNuevoCliente = properties.getOrDefault("controller.tiempoEntradaNuevoCliente", tiempoEntradaNuevoCliente).toString().toInt()

    }
}