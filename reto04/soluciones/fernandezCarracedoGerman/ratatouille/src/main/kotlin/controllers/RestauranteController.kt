package org.example.controllers

import org.example.config.Config
import org.example.exceptions.RestauranteExceptions
import org.example.factories.MenuFactory
import org.example.models.*
import org.example.repositories.Cola
import org.example.repositories.ColaImpl
import org.lighthousegames.logging.logging

/**
 *
 * Lista de comandas: Llevaremos el control de las comandas creadas/cocinadas/servidas y nos servirá
 * para calcular recaudación al final del día
 * Lista de clientes: Se generan los clientes de la simulación al principio de la misma
 */

class RestauranteController private constructor(){

    private val log = logging()

    // Cola de comandas para preparar y servir
    private val comandasRepository: Cola<Comanda>
    private val listaComandasTerminadas: MutableList<Comanda> = mutableListOf<Comanda>()

    // Cola de clientes generados para entrar al restaurante
    private val clientesRepository: Cola<Cliente>

    // Lista con los menús disponibles hoy
    private val listaMenusDia:List<Menu>

    private val restaurante= Array(Config.numFilasRestaurante) { arrayOfNulls<Any?>(Config.numColumnasRestaurante) }

    private val iconoSuelo="⬜"

    private var totalRecaudacion:Double = 0.0
    private var totalClientesDescontentos:Int = 0
    private var totalClientesContentos:Int = 0

    /**
     * Constructor privado para singleton
     */
    init {

        // Inicializamos los repositorios de menús y comandas
        listaMenusDia = crearListaMenus()

        // La cola de comandas se irá rellenando según se generen
        comandasRepository = ColaImpl()

        // La cola de clientes se rellena al principio de la simulación
        clientesRepository = ColaImpl()
        inicializarColaClientes()
        inicializarRestaurante()


    }

    companion object {
        /**
         * Para instancia única del controlador restaurante mediante Singleton
         * @return instancia única del controlador restaurante
         */
        private var instance: RestauranteController? = null

        fun getInstance(): RestauranteController {
            if (instance == null) {
                instance = RestauranteController()
            }
            return instance!!
        }
    }

    private fun crearListaMenus():List<Menu> {
        return listOf(
            MenuFactory.crearRandom(),
            MenuFactory.crearRandom(),
            MenuFactory.crearRandom(),
            MenuFactory.crearRandom(),
            MenuFactory.crearRandom()
        )
    }

    /**
     * Coloca los elementos del restaurante es sus posiciones iniciales en la matriz que lo representa
     */
    private fun inicializarRestaurante() {
        // Coloca las Mesas
        for (i in 0 until Config.numColumnasRestaurante)
            restaurante[0][i] = Mesa(i+1)

        // Coloca a los Camareros
        for (i in 0 until Config.numColumnasRestaurante) restaurante[Config.numFilasRestaurante-2][i] = Camarero(i+1, "C$i",i+1)

        // Coloca al Cocinero
        restaurante[Config.numFilasRestaurante-1][1] = Cocinero()
    }

    /**
     * Inicializamos la cola de clientes con el número de clientes que nos piden en los requisitos
     * Solo pasamos el Id de cliente, para el resto de parámetros, usamos su valor por defecto
     */
    private fun inicializarColaClientes() {
        for (i in 0 until Config.numClientesDia) {
            clientesRepository.queue(Cliente(i+1))
            log.debug { clientesRepository.get(i).toString() }
        }
    }

    /**
     * Simulación del restaurante
     */
    fun simular() {

        var tiempo = 0

        log.debug { "Restaurante abierto, Camareros y cocineros a la espera" }
        imprimirRestaurante()

        do {

            log.debug { "Tiempo de simulación: ${tiempo / 1000}" }

            // Cada cierto tiempo, con un porcentaje de probabilidad, entra un cliente nuevo
            if (tiempo % Config.tiempoEntradaNuevoCliente == 0){

                if (hayMesasLibres() && hayClientesEsperando() && entraCliente())
                    colocaClienteEnMesaLibre(clientesRepository.dequeue())

            }

            comprobarSaleRata()

            accionesClientes()

            accionesCamareros()

            accionesCocinero()

            Thread.sleep(Config.pausaTiempo.toLong())
            tiempo+= Config.pausaTiempo

            actualizarTiemposClientes()

            imprimirRestaurante()

        } while ((hayClientesEsperando() || hayMesasOcupadas()) && tiempo <= Config.tiempoSimulacion)

    }


    /**
     * Comprobamos si entra un nuevo cliente según la probabilidad configurada
     * @return Boolean True si se cumple la probabilidad de que entre un cliente, false en caso contrario
     */
    private fun entraCliente(): Boolean {
        return (0..100).random()<Config.probEntrarClientes
    }

    /**
     * Genera una posición aleatoria dentro de los pasillos de los camareros del restaurante, no en la cocina ni en
     * el salón.
     * @return Pair variable par fila - columna con la posición generada
     *
     */
    private fun generarPosicionAleatoriaPasillosRata():Pair<Int,Int> {

        return Pair(
            (1 until Config.numFilasRestaurante -1).random(),
            (0 until Config.numColumnasRestaurante).random()
        )

    }

    /**
     * Comprueba si aparece una rata en los pasillos, en el salón o no aparece, según las probabilidades de cada caso
     */
    private fun comprobarSaleRata() {
        val res = (0..100).random()
        when {
            res <= Config.probSaleRataSalon ->   {  saleRataEnSalon()     }
            res <= Config.probSaleRataPasillo -> {  saleRataEnPasillos()  }
            else -> {   log.debug { "No salió ninguna rata... o nadie la vio... 🧀🐀" } }
        }
    }

    /**
     * Cuando una rata aparece en el pasillo, comprueba dónde y si el camarero tropieza con ella
     */
    private fun saleRataEnPasillos() {

        log.debug { "🐀 ¡¡Una rata atraviesa los pasillos!! 🐀" }

        val pos = generarPosicionAleatoriaPasillosRata()

        if (restaurante[pos.first][pos.second] is Camarero && (0..100).random() <= Config.probCamareroCae) {
            camareroCae(pos.first, pos.second)
        } else {
            log.debug { "¡Nadie tropieza con la rata!" }
        }
    }

    /**
     * Llama a las acciones de los clientes, camareros y cocinero cuando una rata aparece en el salón.
     */
    private fun saleRataEnSalon() {
        log.debug { "🐀 ¡¡Una rata en el salón!! 🐀" }
        reiniciarTareasCocinero()
        clientesSalenDespavoridos()
        reiniciarTareasCamareros()
        //log.debug { "ComandasRepo.size =  ${comandasRepository.size()} " }
        //log.debug { "ListaComandasTerminadas.size = ${listaComandasTerminadas.size} " }
    }

    /**
     * El cocinero deja de cocinar, añade la comanda que estuviera haciendo a la lista de comandas terminadas y la
     * da por perdida. Además, queda a la espera de nuevas comandas.
     */
    private fun reiniciarTareasCocinero() {

        val cocinero = restaurante[Config.numFilasRestaurante-1][1] as Cocinero

        with (cocinero) {
            if (comandaActual != null) {
                listaComandasTerminadas.add(comandaActual!!)
                marcarComandaTerminadaPerdida(comandaActual!!)
                comandaActual = null
                estado = Cocinero.Estado.ESPERANDO_NUEVA_COMANDA
            }
        }


    }

    /**
     * El camarero que se encuentra en la fila y pasillo pasados por parámetro, cae al suelo, la comanda queda
     * inservible y el camarero debe volver a cocina para pedir de nuevo.
     * @param fila fila donde se produce la caída
     * @param col pasillo donde se produce la caída
     */
    private fun camareroCae(fila: Int, col: Int) {
       val camarero: Camarero = restaurante[fila][col] as Camarero
       with (camarero) {
           when (estado) {
               Camarero.Estado.LLEVANDO_COMANDA_MESA -> {
                   log.debug { "Camarero $id tropieza con una rata, se asusta y cae al suelo con la bandeja de comida, vuelve a la cocina" }
                   estado = Camarero.Estado.LLEVANDO_COMANDA_COCINA
                   cambioDireccion()
                   marcarComandaTerminadaPerdida(comandaActual!!)
                   // asignamos al camarero una nueva comanda, con los mismos datos de la que ha perdido
                   comandaActual = Comanda.crearComanda(comandaActual!!.numCliente,comandaActual!!.numMenu)
               }
               else ->{
                   log.debug { "Camarero $id tropieza con una rata y cae al suelo. Por suerte no llevaba bandeja, disimula y se levanta como si nada" }
               }
           }
       }
    }

    /**
     * Busca la comanda en la lista de comandas terminadas y la marca como perdida
     * @param comanda Comanda a marcar
     */
    private fun marcarComandaTerminadaPerdida(comanda:Comanda) {
        listaComandasTerminadas.find { it.id == comanda.id }?.let {
            it.perdida = true
        }
    }

    /**
     * Todos los clientes del salón se marchan sin pagar. Según el estado en que se encontraran, se harán unas acciones
     * u otras. Las comandas estuvieran en la mesa o en la cocina pendientes de preparar, deben darse por terminadas y
     * perdidas, y eliminarse de la cola de comandas pendientes si lo estaban.
     */
    private fun clientesSalenDespavoridos() {
        for (i in 0 until Config.numColumnasRestaurante) {

            if (restaurante[0][i] is Cliente) {
                with((restaurante[0][i] as Cliente)) {

                    log.debug { "Cliente $id, mesa ${i+1} ha visto una rata y se marcha" }
                    gritarRata()

                    when(estado){
                        Cliente.Estado.ESPERANDO_CAMARERO -> {} // nada, aún no había pedido

                        Cliente.Estado.COMIENDO -> { // la comanda se da por perdida
                            listaComandasTerminadas.find { it.numCliente == id }?.let {
                                it.perdida = true
                            }
                        }

                        Cliente.Estado.ESPERANDO_COMANDA -> {
                            comandasRepository.toList().find { it.numCliente == id }?.let {
                                // comanda ya pasó a cocina, a la cola de comandas.
                                listaComandasTerminadas.add(it)
                                marcarComandaTerminadaPerdida(it)
                                comandasRepository.remove(it) // se elimina de la cola para que no se vuelva a cocinar
                            }?:run{
                                // comanda no está en la cola de comandas: el camarero
                                // o bien la recogió pero no la ha llevado al cliente aún (ya en lista terminadas)
                                // o bien no la ha llevado aún (añadimos a lista terminadas)
                                // o bien, el cocinero aun la está preparando
                                // luego, la añadimos a la lista de comandas terminadas solo si no estaba ya
                                val camarero:Camarero = buscarCamareroMesa(numMesa-1)
                                listaComandasTerminadas.find { it.numCliente == id }?.let {
                                }?:run{
                                    listaComandasTerminadas.add(camarero.comandaActual!!)
                                    marcarComandaTerminadaPerdida(camarero.comandaActual!!)
                                }
                            }
                        }
                        Cliente.Estado.FINALIZADO -> { // se marcha sin pagar
                            log.debug { "Cliente $id, mesa ${i+1} había terminado pero se marcha sin pagar" }
                        }
                    }

                    sacarClienteDeMesa(numMesa)
                }
                totalClientesDescontentos ++
            }
        } // si no es un cliente, es una mesa vacía


}

    /**
     * Busca en la matriz del restaurante al camarero correspondiente a una mesa concreta, recorriendo su pasillo
     * @param numMesa Mesa de la que queremos su camarero
     * @return Camarero - Camarero de la mesa pasada por parámetro
     * @throws RestauranteExceptions.CamareroNoExistenteException Si no se encuentra ningún camarero en el pasillo
     */
    private fun buscarCamareroMesa(numMesa: Int): Camarero {
        for (i in 1 until Config.numFilasRestaurante-1){
            if (restaurante[i][numMesa] is Camarero)
                return restaurante[i][numMesa] as Camarero
        }
        throw RestauranteExceptions.CamareroNoExistenteException("El camarero de esa mesa no existe")
    }

    /**
     * Gestionamos las acciones de cada camarero del restaurante
     */
    private fun accionesCamareros() {

        for (col in 0 until Config.numColumnasRestaurante) {
            val fila:Int = buscarFilaCamarero(col)
            val camarero = restaurante[fila][col] as Camarero
            //log.debug { "Camarero ${camarero.id} a la acción" }
            accionCamarero(camarero)
        }
    }

    /**
     * Gestionamos las acciones que puede hacer un camarero, según su estado actual.
     * Podrá esperar a que se siente un cliente en su mesa. ir a preguntarle el menú elegido, llevar el pedido a
     * la cocina, esperar a que esta se prepare y finalmente, llevar la comida al cliente cuando esté.
     * @param camarero Camarero que realizará la acción
     */
    private fun accionCamarero(camarero: Camarero) {
        with (camarero) {

            when (estado){

                Camarero.Estado.ESPERANDO_CLIENTE ->{
                    // si hay un cliente esperando, se dirige hacia él para preguntarle
                    // si no, sigue esperando
                    if (hayClienteEnMesaEsperandoCamarero(numMesa)){
                        estado = Camarero.Estado.BUSCANDO_CLIENTE
                        direccion = Camarero.Direccion.SALON
                        avanzarPosicionCamarero(camarero,numMesa-1)
                        log.debug { "Camarero ${camarero.id} va a pedir menú al cliente" }
                    } else {
                        log.debug { "Camarero ${camarero.id} esperando a que haya cliente en su mesa o termine de comer" }
                    }
                }

                Camarero.Estado.BUSCANDO_CLIENTE->{
                    // comprobar si he llegado a la fila anterior a la mesa (la 1)
                    // si no, avanzar otra casilla
                    // si llegó, preguntar menú al cliente

                    val fila = buscarFilaCamarero(numMesa-1)

                    if (fila != 1) {

                        log.debug { "Camarero $id va hacia la mesa a pedir menú al cliente" }
                        avanzarPosicionCamarero(this,numMesa-1)

                    } else {

                        comandaActual = preguntarMenuCliente(numMesa-1)
                        cambioDireccion()
                        estado = Camarero.Estado.LLEVANDO_COMANDA_COCINA
                        (restaurante[0][numMesa-1] as Cliente).estado = Cliente.Estado.ESPERANDO_COMANDA

                        log.debug { "Camarero $id ha tomado nota al cliente y va a solicitar en cocina Comanda: "+
                            "${comandaActual!!.id}, Menú: ${comandaActual!!.numMenu} " }
                    }

                }

                Camarero.Estado.LLEVANDO_COMANDA_COCINA->{
                    // comprobar si he llegado a la fila anterior a la cocina
                    // si no, avanzar otra casilla
                    // si llegó, entregar la comanda en Cocina

                    val fila = buscarFilaCamarero(numMesa-1)

                    if (fila != Config.numFilasRestaurante-2) {

                        log.debug { "Camarero $id va a Cocina a encargar menú del cliente" }
                        avanzarPosicionCamarero(this,numMesa-1)

                    } else {

                        if (comandaActual != null) {
                            comandasRepository.queue(comandaActual!!)
                            estado = Camarero.Estado.ESPERANDO_COMANDA_PREPARADA
                            log.debug { "Camarero $id ha entregado la comanda ${comandaActual!!.id} en Cocina y espera a que esté lista" }
                        }else{
                            log.error { "Camarero $id intentó entregar una comanda que no existe" }
                            throw RestauranteExceptions.ComandaException("Se intentó entregar una comanda que no existe")
                        }
                    }
                }

                Camarero.Estado.ESPERANDO_COMANDA_PREPARADA -> {
                    // si la comanda que está esperando está terminada, se dirige de nuevo hacia el cliente
                    // si no, sigue esperando
                    if (comprobarComandaTerminada(comandaActual!!.id)) {
                        cambioDireccion()
                        estado = Camarero.Estado.LLEVANDO_COMANDA_MESA
                        log.debug { "Camarero $id ha recogido la comanda ${comandaActual!!.id} y se la lleva al cliente" }

                    } else {
                        log.debug { "Camarero $id sigue esperando la comanda ${comandaActual!!.id}" }

                    }
                }
                Camarero.Estado.LLEVANDO_COMANDA_MESA -> {
                    // comprobar si ha llegado a la fila anterior a la mesa (la 1)
                    // si no, avanzar otra casilla
                    // si llegó,
                        // servir menú al cliente
                        // colocarle directamente en su posición de inicio y con dirección al salón
                        // pasar a ESPERANDO_CLIENTE

                    val fila = buscarFilaCamarero(numMesa-1)
                    if (fila != 1) {
                        avanzarPosicionCamarero(this,numMesa-1)
                        log.debug { "Camarero $id llevando al cliente la comanda ${comandaActual!!.id}" }

                    } else {
                        servirComanda(numMesa-1)
                        log.debug { "Camarero $id ha servido al cliente la comanda ${comandaActual!!.id}, vuelve a su sitio y queda esperando otro cliente" }
                        comandaActual = null
                        moverCamareroPosicionInicial(this, fila,numMesa-1)
                        estado = Camarero.Estado.ESPERANDO_CLIENTE
                    }
                }
            }

        }
    }

    /**
     * Esté donde esté, coloca al camarero pasado por parámetro, la posición inicial,
     * en su pasillo y junto a la cocina
     * @param camarero Camarero a colocar
     * @param fila fila donde se encuentra el camarero
     * @param numMesa mesa a la que está asignado el camarero
     */
    private fun moverCamareroPosicionInicial(camarero: Camarero,fila:Int, numMesa: Int) {
        restaurante[fila][numMesa] = null
        restaurante[Config.numFilasRestaurante-2][numMesa] = camarero
    }

    /**
     * Sirve el menú al cliente, simplemente cambiando su estado a COMIENDO
     * @param numMesa Mesa del cliente
     * @throws RestauranteExceptions.ClienteNoExistenteException si en la mesa no hay ningún cliente
     */
    private fun servirComanda(numMesa: Int) {

        if (restaurante[0][numMesa] !is Cliente)
            throw RestauranteExceptions.ClienteNoExistenteException(
                "Error, se intentó servir un menú a un cliente inexistente.")

        (restaurante[0][numMesa] as Cliente).estado = Cliente.Estado.COMIENDO
        log.debug { "El cliente ${(restaurante[0][numMesa] as Cliente).id}, mesa ${numMesa+1} ha empezado a comer, le quedan "+
            "${(restaurante[0][numMesa] as Cliente).tiempoComiendo} segundos" }

    }

    /**
     * Comprueba si una comanda está lista de comandas terminadas
     * @return Boolean: true si está en la lista, false en caso contrario
     */
    private fun comprobarComandaTerminada(idComanda: Int): Boolean {
        return (listaComandasTerminadas.find { it.id == idComanda } != null)
    }

    /**
     * Genera una comanda con el número del cliente y el menú que este elija
     * @param numMesa Mesa del cliente
     * @return Comanda generada
     * @throws RestauranteExceptions.ClienteNoExistenteException si en la mesa no hay ningún cliente
     */
    private fun preguntarMenuCliente(numMesa: Int): Comanda {

        if (restaurante[0][numMesa] !is Cliente)
            throw RestauranteExceptions.ClienteNoExistenteException(
                "Error, se intentó consultar un menú a un cliente inexistente.")

        val cliente:Cliente = restaurante[0][numMesa] as Cliente
        val numMenuElegido = cliente.elegirMenu(listaMenusDia)
        log.debug { "El cliente ${cliente.id} ha elegido el ${listaMenusDia.find { it.id == numMenuElegido }.toString()}" }

        return Comanda.crearComanda(cliente.id,numMenuElegido)

    }

    /**
     * El camarero pasado por parámetro avanza o retrocede por su pasillo en función de su dirección actual, hacia el
     * Salón o hacia la cocina. Para ello se actualiza el contenido de las casillas de la matriz donde se encuentra.
     * @param camarero Camarero que avanza
     * @param pasillo Pasillo por el que se mueve el camarero
     */
    private fun avanzarPosicionCamarero(camarero:Camarero, pasillo: Int) {

        var fila = buscarFilaCamarero(pasillo)

        if (camarero.direccion == Camarero.Direccion.SALON) {
            fila--
            if (fila == 0 ) return
            restaurante[fila][pasillo] = camarero
            restaurante[fila+1][pasillo] = null
        } else { // Direccion.COCINA
            fila++
            if (fila == Config.numFilasRestaurante-1) return
            restaurante[fila][pasillo] = camarero
            restaurante[fila-1][pasillo] = null
        }
    }


    /**
     * Comprueba si hay un cliente esperando al camarero en la mesa pasada por parámetro
     * @param numMesa Mesa consultada
     * @return Boolean - True si hay un cliente esperando al camarero, false en caso contrario
     */
    private fun hayClienteEnMesaEsperandoCamarero(numMesa: Int): Boolean {
        return (restaurante[0][numMesa-1] is Cliente &&
                ((restaurante[0][numMesa-1])as Cliente).estado == Cliente.Estado.ESPERANDO_CAMARERO)
    }

    /**
     * Devuelve la fila donde está el camarero del pasillo pasado por parámetro
     * @param pasillo Pasillo del camarero
     * @return Int - Fila del camarero
     */
    private fun buscarFilaCamarero(pasillo: Int): Int {
        var fila:Int = 0
        for (i in 1 until Config.numFilasRestaurante-1){
            if (restaurante[i][pasillo] is Camarero) fila = i
        }
        return fila
    }


    /**
     * Gestionamos las acciones que puede hacer el cocinero, según su estado actual.
     * Podrá cocinar su comanda actual o esperar a que haya una comanda disponible en la cola de comandas pendientes.
     */
    private fun accionesCocinero() {

        with(restaurante[Config.numFilasRestaurante-1][1] as Cocinero){ // el cocinero siempre está en esta posición
           when(estado){

               Cocinero.Estado.COCINANDO->{
                   cocinar()
                   if (comandaActual!!.terminada) {
                       listaComandasTerminadas.add(comandaActual!!)
                       log.debug { "Cocinero ha terminado de preparar la comanda ${comandaActual!!.id},y comprobará si hay mas comandas pendientes" }
                       comandaActual = null
                       estado = Cocinero.Estado.ESPERANDO_NUEVA_COMANDA
                   } else {
                       log.debug { "Cocinero está preparando la comanda ${comandaActual!!.id}, quedan ${comandaActual!!.tiempoRestanteCocinar} segundos" }
                   }
               }

               Cocinero.Estado.ESPERANDO_NUEVA_COMANDA -> {
                   if (hayNuevaComanda()){
                       comandaActual = comandasRepository.dequeue()
                       estado = Cocinero.Estado.COCINANDO
                       log.debug { "Cocinero comienza a preparar la comanda ${comandaActual!!.id}, quedan ${comandaActual!!.tiempoRestanteCocinar} segundos" }
                   } else {
                       log.debug { "Cocinero esperando solicitudes de comandas" }
                   }
               }
           }
        }
    }

    /**
     * Comprueba si hay una nueva comanda pendiente de cocinar
     * @return Boolean - True si hay la lista de comandas pendientes tiene elementos, false en caso contrario
     */
    private fun hayNuevaComanda(): Boolean {
        return comandasRepository.size() > 0
    }

    /**
     * Para cada cliente, gestionamos sus acciones
     *
     */
    private fun accionesClientes() {
        for (i in 0 until Config.numColumnasRestaurante) {
            with (restaurante[0][i]) {
                if (this is Cliente) {
                    log.debug { "Cliente ${this.id}, Mesa ${this.numMesa} | Tiempo Simulación: ${this.tiempoEsperando}" }
                    accionCliente(this)
                } // si no es un cliente, es una mesa vacía
            }
        }
    }

    /**
     * Gestionamos las acciones que puede hacer el cliente una vez sentado en su mesa
     * Puede esperar que le atienda un camarero o a que le llegue la comida, comer, y finalizar, pagar y marcharse.
     * Si se superan los tiempos de espera, protestará con una probabilidad, o se irá finalmente sin pagar.
     * ESPERANDO_CAMARERO, COMIENDO, ESPERANDO_COMANDA, FINALIZADO
     * @param cliente Cliente que realiza la acción
     */
    private fun accionCliente(cliente: Cliente) {
        with (cliente) {
            when (estado) {
                Cliente.Estado.ESPERANDO_CAMARERO, Cliente.Estado.ESPERANDO_COMANDA -> {
                    if (comprobarProtesta()) println("¡¡¡En la mesa $numMesa no atienden a nadie!!!")
                    if (comprobarIrseSinPagar()) {
                        println("Mesa $numMesa: ¡¡¡Estoy harto, yo me largo de aquí!!!")
                        sacarClienteDeMesa(numMesa)
                        totalClientesDescontentos ++

                        // TODO refactorizar:
                        // marcar comanda como inservible
                        val fila = buscarFilaCamarero(numMesa-1)
                        val camarero = restaurante[fila][numMesa-1] as Camarero
                        if (camarero.comandaActual !=null) marcarComandaTerminadaPerdida(camarero.comandaActual!!)

                        reiniciarTareaCamarero(camarero, fila)

                    }
                }

                Cliente.Estado.COMIENDO -> {
                    comer()
                }

                Cliente.Estado.FINALIZADO -> { // paga y se marcha
                    totalRecaudacion += getPrecioMenu(numMenuElegido)
                    totalClientesContentos ++
                    sacarClienteDeMesa(numMesa)
                    log.debug { "El cliente $id, mesa $numMesa ha terminado de comer, paga y se marcha" }
                }
            }
        }
    }

    /**
     * Reinicia las tareas de todos los camareros del restaurante
     */
    private fun reiniciarTareasCamareros() {
        for (col in 0 until Config.numColumnasRestaurante) {
            val fila:Int = buscarFilaCamarero(col)
            val camarero = restaurante[fila][col] as Camarero
            reiniciarTareaCamarero(camarero,fila)
        }
    }

    /**
     * Reinicia las tareas del camarero pasado por parámetro
     * @param camarero Camarero a reiniciar sus tareas
     * @param fila Fila donde está el camarero
     */
    private fun reiniciarTareaCamarero(camarero: Camarero, fila:Int) {
        with(camarero) {
            estado = Camarero.Estado.ESPERANDO_CLIENTE
            comandaActual = null
            moverCamareroPosicionInicial(this, fila, numMesa - 1)
            log.debug { "Camarero $id vuelve a su posición inicial y espera nuevo cliente" }
        }
    }

    /**
     * Recuperamos el precio del menú con número pasado por parámetro buscando en la lista de menús del día
     * @param numMenuElegido Número del menú
     * @return Double: Precio del menú
     * @throws RestauranteExceptions.MenuNoExistenteException Si el menú no está en la lista
     */
    private fun getPrecioMenu(numMenuElegido: Int): Double {
        return listaMenusDia.find{ it.id == numMenuElegido }?.precio ?:
            throw RestauranteExceptions.MenuNoExistenteException("No se encontró el menú al buscar precio")
    }

    /**
     * Deja libre la mesa donde estaba sentado el cliente, volviendo a dejar un objeto Mesa en la
     * columna correspondiente al cliente que se marcha
     * @param numMesa Número de mesa del cliente
     */
    private fun sacarClienteDeMesa(numMesa: Int) {
        restaurante[0][numMesa-1] = Mesa(numMesa)
    }

    /**
     * Aumentamos el tiempo de simulación de todos los clientes del restaurante
     */
    private fun actualizarTiemposClientes() {
        for (i in 0 until Config.numColumnasRestaurante) {
            if (restaurante[0][i] is Cliente) {
                (restaurante[0][i] as Cliente).tiempoEsperando++
            }
        } // si no es un cliente, es una mesa vacía
    }

    /**
     * Coloca al cliente pasado por parámetro, en la fila 0 de la matriz (salón), y en la primera mesa que se encuentre
     * libre
     */
    private fun colocaClienteEnMesaLibre(cliente:Cliente) {
        for (i in 0 until Config.numColumnasRestaurante) {
            if (restaurante[0][i] is Mesa) {
                if (!(restaurante[0][i] as Mesa).ocupada) {
                    cliente.numMesa = i+1
                    restaurante[0][i] = cliente // sustituimos la mesa por el cliente (se sienta)
                    log.debug { "Entra Cliente ${cliente.id} y se sienta en la mesa ${cliente.numMesa}" }
                    return
                }
            } // si no es una mesa, es un cliente ya sentado
        }
        if (cliente.numMesa == 0) throw RestauranteExceptions.ClienteColocacionException("No se pudo colocar al cliente en una mesa libre.")

    }

    /**
     * Comprueba si hay clientes en la cola de clientes esperando
     * @return Boolean - True si la cola tiene elementos, false en caso contrario
     */
    private fun hayClientesEsperando(): Boolean {
        return clientesRepository.size()>0
    }

    /**
     * Comprueba si hay mesas libres: Recorre la fila de las mesas y si es una mesa y no está ocupada, es que ya hay
     * algún sitio libre y devuelve true. Si tras recorrer toda la fila, no encontró ninguna libre, devuelve false
     * @return Boolean True so hay mesas libres, false en caso contrario
     */
    private fun hayMesasLibres(): Boolean {

        for (i in 0 until Config.numColumnasRestaurante) {
            if (restaurante[0][i] is Mesa) {
                if (!(restaurante[0][i] as Mesa).ocupada) return true
            }
        } // si no es una mesa, es un cliente ya sentado

        return false
    }

    /**
     * Comprueba si alguna mesa ocupada: recorre la fila de las mesas y si hay un cliente, es que ya hay
     * algún sitio ocupado y devuelve true. Si tras recorrer toda la fila, no encontró ningún cliente, devuelve false
     * @return Boolean True si hay alguna mesa ocupada, false en caso contrario
     */
    private fun hayMesasOcupadas(): Boolean {

        for (i in 0 until Config.numColumnasRestaurante)
            if (restaurante[0][i] is Cliente) return true

        return false
    }

    /**
     * Muestra en pantalla la matriz que representa el restaurante, recorriéndola e imprimiendo el icono correspondiente
     * al tipo de objeto que hay almacenado en cada posición
     */
    private fun imprimirRestaurante(){
        println("🧱" + "🧱".repeat(Config.numColumnasRestaurante) + "🧱")
        for (i in restaurante.indices) {
            print("🧱")
            for (j in restaurante[i].indices) {

                when (restaurante[i][j]) {
                        is Mesa -> print ((restaurante[i][j] as Mesa).icono)
                        is Cliente -> print ((restaurante[i][j] as Cliente).icono)
                        is Cocinero -> print ((restaurante[i][j] as Cocinero).icono)
                        is Camarero -> print ((restaurante[i][j] as Camarero).icono)
                        else -> print(iconoSuelo)
                }
            }
           println("🧱")
        }
        println("🧱" + "🧱".repeat(Config.numColumnasRestaurante) + "🧱")
        log.debug { "Restaurante impreso" }
        println()

    }
    /**
     * Se muestran todas las estadísticas que nos solicitan:
     * - Número de clientes que han entrado hoy
     * - Número de clientes que se han marchado descontentos
     * - Recaudación final
     * Además, mostramos:
     * - Número de comandas cocinadas
     * - Número de comandas servidas
     * - Número de comandas perdidas
     */
    fun mostrarInformes() {
        println()
        println("La jornada de hoy ha finalizado")
        println("===============================")
        println()
        println("\t- Informe -\n")
        println("\tNúmero de clientes total: ${totalClientesContentos + totalClientesDescontentos}")
        println("\tNúmero de clientes descontentos: $totalClientesDescontentos")
        println("\tTotal Recaudación: $totalRecaudacion €")
        println("\n\tNúmero de menús preparados: ${listaComandasTerminadas.size + comandasRepository.size()} ")
        println("\tNúmero de menús servidos: ${listaComandasTerminadas.filter { !it.perdida }.size}")
        println("\tNúmero de menús desperdiciados (se cocinaron pero no se sirvieron): ${listaComandasTerminadas.filter { it.perdida }.size + comandasRepository.size()} ")




    }

}

