package org.example.models

import org.example.config.Config
import org.example.exceptions.RestauranteExceptions
import org.lighthousegames.logging.logging
import kotlin.math.max

/**
 * Clase Cliente
 */
class Cliente(
    val id:Int,
    val probGritar:Int = 50,
    val tiempoGritar:Int = 15,
    val probIrseSinPagar:Int = 100,
    val tiempoIrseSinPagar:Int = 20
){
    var numMesa:Int = 0
    val icono = listOf("👵🏻","🧓🏻","🧑🏻‍🦰","👨🏻‍🦲","👱🏻‍♀️","👨🏻‍🦳","👩🏻‍🦰","👨🏻","🧔🏿","👩🏿").random()
    var estado:Estado = Estado.ESPERANDO_CAMARERO
    var tiempoEsperando:Int = 0
    var tiempoComiendo:Int = 5
    var numMenuElegido:Int = 0

    enum class Estado { ESPERANDO_CAMARERO, COMIENDO, ESPERANDO_COMANDA, FINALIZADO }

    private val log = logging()

    /**
     * Se decrementa el tiempo de comida del menú que está consumiendo el cliente
     */
    fun comer(){
        tiempoComiendo--
        if (tiempoComiendo == 0) {
            estado = Estado.FINALIZADO
            log.debug { "El cliente $id, mesa $numMesa ha terminado de comer" }
        } else {
            log.debug { "El cliente $id, mesa $numMesa sigue comiendo, le quedan $tiempoComiendo segundos" }
        }
    }

    /**
     * Devuelve el menú elegido por el cliente, de una lista pasada por parámetro
     * El cliente tiene un % de probabilidad de dejarse influenciar por la valoración
     * de los menús. Si lo hace, elegirá el que mas valoración tenga.
     * Si no se deja influenciar, elegirá el que mas le guste (que en nuestro caso se decidirá aleatoriamente)
     * @param cartaMenu Lista con los menús para elegir
     * @return Int - Número del menú elegido
     */
    fun elegirMenu(cartaMenu:List<Menu>):Int{

        if ((0..100).random() < Config.probClienteInfluenciadoValoracionMenu ){

            val maxValoracion = cartaMenu.maxOf { it.valoracion }
            numMenuElegido = cartaMenu.firstOrNull{it.valoracion == maxValoracion}?.id
                ?: throw RestauranteExceptions.MenuNoExistenteException("No se encontró menú")

        } else {

            numMenuElegido = cartaMenu.random().id

        }

        return numMenuElegido

    }

    /**
     * Comprueba si el cliente protesta, según su probabilidad de gritar, si el tiempo de espera ha llegado al tiempo
     * marcado como límite
     * @return Boolean - True si decide protestar, false en caso contrario
     */
    fun comprobarProtesta():Boolean{
        return ((tiempoEsperando == tiempoGritar) && ((0..100).random() < probGritar))
    }

    /**
     * El cliente grita porque ve una rata
     */
    fun gritarRata(){
        println("¡¡¡ UNA RATA... QUÉ ASCOOOO, YO ME LARGO DE AQUÍ !!!")
    }

    /**
     * Comprueba si el cliente se marcha, según su probabilidad, si el tiempo de espera ha llegado al tiempo
     * marcado como límite
     * @return Boolean - True si decide marcharse, false en caso contrario
     */
    fun comprobarIrseSinPagar():Boolean {
        return ((tiempoEsperando == tiempoIrseSinPagar) && ((0..100).random() < probIrseSinPagar))
    }

    override fun toString(): String {
        return ("Cliente ID: $id | Tiempo Espera: $tiempoEsperando | P. Gritar = $probGritar | Tiem. Gritar = $tiempoGritar " +
                "| Pr. Irse sin Pagar = $probIrseSinPagar | Tiem. Irse sin Pagar = $tiempoIrseSinPagar")
    }
}