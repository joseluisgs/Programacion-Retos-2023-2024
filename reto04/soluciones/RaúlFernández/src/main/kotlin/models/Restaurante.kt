package models

class Restaurante (val nombre: String): IntegrantesRestaurante {
    val restaurante: Array<Array<IntegrantesRestaurante?>> = Array(7) {Array (3){null} }


    /**
     * esta funcion es en la que marcamos todos los parametros.
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    fun simulación(){

        colocarElementos()
        mostrarRestaurante()
        println()
        do {
            posicionarClientes()
            moverCamarerosAMesa()
            moverCamarerosACocina()
            cocinar()
            mostrarRestaurante()
            println()
            ponerComida()
            pedidoClientes()
            comer()
            salir()
            Thread.sleep(6000)
        }while (contadorClientes<10)
        mostrarRestaurante()
        informe()
    }

    // Aqui ponemos 10 clientes para que entren de modo aleatoriamente mientras haya mesa libre.
    private val Cliente1 = Cliente("Ruben",null)
    private val Cliente2 = Cliente("Raúl",null)
    private val Cliente3 = Cliente("Yahya",null)
    private val Cliente4 = Cliente("Alba",null)
    private val Cliente5 = Cliente("Javi",null)
    private val Cliente6 = Cliente("Penelope",null)
    private val Cliente7 = Cliente("Paula",null)
    private val Cliente8 = Cliente("Mario",null)
    private val Cliente9 = Cliente("Samuel",null)
    private val Cliente10 = Cliente("Moha",null)

    // Ponemos 3 camareros que seran los encargados de llevar la comida desde su punto de partida (cocina) a las mesas que esten OCUPADAS.
    private val camarero1 = Camarero("Raúl",5, 0)
    private val camarero2 = Camarero("Daniel",5, 1)
    private val camarero3 = Camarero ("Cristian",5,2)

    // Tres huecos de cocina donde cada camarero estara asignado a una mesa y de ahi ira a su cocina.
    private val cocina1 = Cocina (6, 0)
    private val cocina2 = Cocina (6,1)
    private val cocina3 = Cocina (6,2)

    // Tres mesas que estaran asignadas cada una a un camarero.
    private val mesa1 = Mesa (1,0,0)
    private val mesa2 = Mesa (2,0,1)
    private val mesa3 = Mesa (3,0,2)

    // Y cinco menus en los que cada uno tendra un precio diferente y el cliente podra elegir.
    private val menu1= Menu(1,35)
    private val menu2= Menu(2,44)
    private val menu3= Menu(3,60)
    private val menu4= Menu(4,48)
    private val menu5= Menu(5,50)

    // Contador de clientes que van entrando al restaurante y la caja para saber cuanto ganamos.
    private var contadorClientes=0
    private var dineroRecaudado=0

    /**
     *Aqui colocaremos los camareros, la mesas y la cocina en nuestra array de 7x3.
     * @author Raúl Fernández Delgado
     * @since 1.0
     */

    fun colocarElementos(){
        restaurante[camarero1.fila][camarero1.columna] = camarero1
        restaurante[camarero2.fila][camarero2.columna] = camarero2
        restaurante[camarero3.fila][camarero3.columna] = camarero3

        restaurante[cocina1.fila][cocina1.columna] = cocina1
        restaurante[cocina2.fila][cocina2.columna] = cocina2
        restaurante[cocina3.fila][cocina3.columna] = cocina3

        restaurante[mesa1.fila][mesa1.columna] = mesa1
        restaurante[mesa2.fila][mesa2.columna] = mesa2
        restaurante[mesa3.fila][mesa3.columna] = mesa3
    }
    /**
     * En esta función iremos contando todos los clientes que han pasado por el restaurante y el dinero que vamos recaudando .
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun informe() {
        println("HAN PASADO UN TOTAL DE $contadorClientes CLIENTES")
        println(" CON UN TOTAL RECAUDADO DE $dineroRecaudado €")
}

    /**
     * Aqui haremos la funcion que cumplen los clientes una vez hayan terminado de comer, que salgan.
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun salir() {
        clientesalir(Cliente1)
        clientesalir(Cliente2)
        clientesalir(Cliente3)
        clientesalir(Cliente4)
        clientesalir(Cliente5)
        clientesalir(Cliente6)
        clientesalir(Cliente7)
        clientesalir(Cliente8)
        clientesalir(Cliente9)
        clientesalir(Cliente10)
}
    /**
     * En esta funcion haremos que el cliente se vaya pagando su cuenta una vez el cliente haya terminado y eso lo haremos con un if, que indica que si el cliente ha terminado o es distinto de marcado (quiere decir que la mesa se ha quedado libre y ya no sale marcada), el cliente pague su cuenta y se vaya.
     * @param cliente
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun clientesalir(cliente: Cliente) {
        if (cliente.terminado && !cliente.marcado){
            println("EL CLIENTE ${cliente.nombre} PAGA SU CUENTA DE ${cliente.cuenta}€")
            dineroRecaudado+=cliente.cuenta
            cliente.sentado=false
            cliente.marcado=true
            contadorClientes++
            when(cliente.mesa){
            mesa1->{
                mesa1.mesaOcupada=false
                camarero1.atendido=false
                camarero1.pedidoEntregado=false
                cocina1.resetCocina()
            }
            mesa2->{
                mesa2.mesaOcupada=false
                camarero2.atendido=false
                camarero2.pedidoEntregado=false
                cocina2.resetCocina()
            }
            mesa3->{
                mesa3.mesaOcupada=false
                camarero3.atendido=false
                camarero3.pedidoEntregado=false
                cocina3.resetCocina()
            }

        }
    }
}
    /**
     * Aqui marcamos que los 10 clientes que hemos puesto tengan la funcion comer.
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun comer() {
        clienteComer(Cliente1)
        clienteComer(Cliente2)
        clienteComer(Cliente3)
        clienteComer(Cliente4)
        clienteComer(Cliente5)
        clienteComer(Cliente6)
        clienteComer(Cliente7)
        clienteComer(Cliente8)
        clienteComer(Cliente9)
        clienteComer(Cliente10)
}
    /**
     * Esta es la funcion comer donde dice que si el cliente tiene la comida en la mesa o el cliente es distinto de que ha terminado, el cliente come.
     * @param cliente
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun clienteComer(cliente: Cliente) {
        if (cliente.comidaEnMesa && !cliente.terminado)cliente.comer()
}
    /**
     * Colocamos en los 10 clientes que tenemos la funcion ponerComida.
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun ponerComida() {
        ponerComidaClientes(Cliente1)
        ponerComidaClientes(Cliente2)
        ponerComidaClientes(Cliente3)
        ponerComidaClientes(Cliente4)
        ponerComidaClientes(Cliente5)
        ponerComidaClientes(Cliente6)
        ponerComidaClientes(Cliente7)
        ponerComidaClientes(Cliente8)
        ponerComidaClientes(Cliente9)
        ponerComidaClientes(Cliente10)
}
    /**
     * En la funcion de poner comida a los clientes, hago una serie de comprobaciones con el when donde si se dan estas casuisticas el cliente come.
     * @param cliente
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun ponerComidaClientes(cliente: Cliente) {
        when(cliente.mesa){
            mesa1->{
                if (camarero1.atendido && camarero1.fila == 1 && cocina1.cocinado && !cliente.marcado){
                    cliente.comidaEnMesa=true
                    camarero1.pedidoEntregado=true
                    println("La comida ha sido entregada a ${cliente.nombre}")
                }
            }
            mesa2->{
                if (camarero2.atendido && camarero2.fila ==1 && cocina2.cocinado && !cliente.marcado){
                    cliente.comidaEnMesa=true
                    camarero2.pedidoEntregado=true
                    println("La comida ha sido entregada a ${cliente.nombre}")
                    }
            }
            mesa3->{
                if (camarero3.atendido && camarero3.fila ==1 && cocina3.cocinado && !cliente.marcado){
                    cliente.comidaEnMesa=true
                    camarero3.pedidoEntregado=true
                    println("La comida ha sido entregada a ${cliente.nombre}")
                }
            }
        }
}
    /**
     * Aqui hacemos que la cocina1 y la mesa1 se asigne al camarero1 y asi se vaya moviendo hacia arriba y abajo y lo mismo con los otros 2 camareros.
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun cocinar() {
        cocina(cocina1,camarero1,mesa1)
        cocina(cocina2,camarero2,mesa2)
        cocina(cocina3,camarero3,mesa3)
}

    /**
     * Aqui hacemos un if que indica que si la mesa que este asignada a un camarero, esta ocupada o es distinto de cocinar el cocinero preparara comida para que el camarero que este asignado lo lleve a su mesa.
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun cocina(cocina: Cocina, camarero: Camarero, mesa: Mesa) {
        if (camarero.fila==5 && mesa.mesaOcupada && !cocina.cocinado){
            cocina.cocinar(camarero)
        }
}
    /**
     * En esta funcion asignamos a que los camareros se asignen cada uno a una mesa desde la cocina y se puedan mover.
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun moverCamarerosACocina() {
        moverCamareroACocina(camarero1,mesa1,cocina1)
        moverCamareroACocina(camarero2,mesa2,cocina2)
        moverCamareroACocina(camarero3,mesa3,cocina3)
}


    /**
     * Aqui hacemos un if que indice que si la mesa esta ocupada o el camarero ya ha atendido o no se ha cocinado nada, el camarero se mueve hasta que llegue a la cocina para recoger lo que ha pedido el cliente que este en su mesa.
     * @param camarero
     * @param mesa
     * @param cocina
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun moverCamareroACocina(camarero: Camarero, mesa: Mesa, cocina: Cocina) {
        if (mesa.mesaOcupada && camarero.atendido && !cocina.cocinado || camarero.pedidoEntregado){
            if (camarero.fila < 5){
                restaurante[camarero.fila][camarero.columna]=null
                camarero.moverCocina()
                restaurante[camarero.fila][camarero.columna]=camarero
            }
        }
}
    /**
     * Aqui hacemos que cada cliente haga su pedido al camarero.
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun pedidoClientes() {
        pedidoCliente(Cliente1)
        pedidoCliente(Cliente2)
        pedidoCliente(Cliente3)
        pedidoCliente(Cliente4)
        pedidoCliente(Cliente5)
        pedidoCliente(Cliente6)
        pedidoCliente(Cliente7)
        pedidoCliente(Cliente8)
        pedidoCliente(Cliente9)
        pedidoCliente(Cliente10)
}
    /**
     * En esta funcion hacemps que una vez el cliente haya pedido el camarero vaya a la cocina ha decir lo que ha pedido el cliente y que el cocinero lo cocine.
     * @param cliente
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun pedidoCliente(cliente: Cliente) {
        if (cliente.sentado && !cliente.notaPedida){
            when(cliente.mesa){
                mesa1->{
                    interraccionCamarero(camarero1,cliente)

                }
                mesa3->{
                    interraccionCamarero(camarero3,cliente)

                }
                mesa2->{
                    interraccionCamarero(camarero2,cliente)

                }
            }
        }
}
    /**
     * Aqui haremos la interacción del camarero, si el camarero esta en la fila uno o no ha atendido el camarero, ira al cliente a tomarle la nota.
     * @param camarero
     * @param cliente
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun interraccionCamarero(camarero: Camarero, cliente: Cliente) {
        if (camarero.fila==1 && !camarero.atendido){
            cliente.notaPedida=true
            camarero.atendido=true
            cliente.tiempoDeEspera()
            println("CLIENTE ${cliente.nombre}")
            println("Buenos dias bienvenido al restaurante de ratatouille")
            println("¿Qué menu de los 5 deseas?")
            val menu=cliente.seleccionMenu()
            when(menu){
                1->{
                    println("Quiero el menu 1 porfavor")
                    cliente.cuenta=menu1.precio
                    cliente.menu= menu1
                }
                2->{
                    println("Quiero el menu 2 porfavor")
                    cliente.cuenta=menu2.precio
                    cliente.menu=menu2
                }
                3->{
                    println("Quiero el menu 3 porfavor")
                    cliente.cuenta=menu3.precio
                    cliente.menu=menu3
                }
                4->{
                    println("Quiero el menu 4 porfavor")
                    cliente.cuenta=menu4.precio
                    cliente.menu=menu4
                }
                5->{
                    println("Quiero el menu 5 porfavor")
                    cliente.cuenta=menu5.precio
                    cliente.menu= menu5
                }
            }
        }
    }

    /**
     * Asignamos cada camarero a una cocina y una mesa y se van moviendo en vertical.
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun moverCamarerosAMesa() {
        moverCamareroAMesa(mesa1,camarero1,cocina1)
        moverCamareroAMesa(mesa2,camarero2,cocina2)
        moverCamareroAMesa(mesa3,camarero3,cocina3)

}
    /**
     * Hacemos la funcion que haga que el camarero vaya a la mesa una vez haya sido cocinada la comida.
     * @param mesa
     * @param camarero
     * @param cocina
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun moverCamareroAMesa(mesa: Mesa, camarero: Camarero, cocina: Cocina) {
        if (mesa.mesaOcupada && !camarero.atendido || cocina.cocinado && !camarero.pedidoEntregado){
            if (camarero.fila > 1){
                restaurante[camarero.fila][camarero.columna]=null
                camarero.moverMesa()
                restaurante[camarero.fila][camarero.columna]=camarero
            }

        }
    }


    /**
     * Ponemos la posicion de los clientes para cuando entren al restaurante se pongan en una mesa y una vez se vaya alguno entren otros.
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun posicionarClientes() {
        posicionClientes(Cliente1)
        posicionClientes(Cliente2)
        posicionClientes(Cliente3)
        posicionClientes(Cliente4)
        posicionClientes(Cliente5)
        posicionClientes(Cliente6)
        posicionClientes(Cliente7)
        posicionClientes(Cliente8)
        posicionClientes(Cliente9)
        posicionClientes(Cliente10)
    }

    /**
     * Hacemos una funcion para comprobar la mesa, y si esta libre se metera un cliente
     * @param cliente
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun posicionClientes(cliente:Cliente) {
        if (!cliente.sentado && !cliente.marcado){
            val res=(1..3).random()
            when(res){
                1->comprobarMesa(mesa1,cliente)
                2->comprobarMesa(mesa2,cliente)
                3->comprobarMesa(mesa3,cliente)
            }
        }
    }

    /**
     * Comprobamos que el cliente este en la mesa para que se acerque el camarero.
     * @param mesa
     * @param cliente
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun comprobarMesa(mesa:Mesa , cliente:Cliente) {
        if (!mesa.mesaOcupada) {
            cliente.mesa = mesa
            cliente.sentado = true
            mesa.mesaOcupada = true
        }
    }

    /**
     * Funcion para mostrar el restaurante haciendo dos for para que se recorra j e i.
     * @author Raúl Fernández Delgado
     * @since 1.0
     */
    private fun mostrarRestaurante() {
        for (i in restaurante.indices){
            println("----- ----- -----")
            for (j in restaurante[i].indices){
                when(restaurante[i][j]){
                    is Mesa-> {
                        if ((restaurante[i][j] as Mesa).mesaOcupada) print("|OCUPADA|")
                        else print("MESA${(restaurante[i][j] as Mesa).numMesa}|")
                    }
                    is Camarero -> print("${(restaurante[i][j] as Camarero).nombre} ")
                    is Cocina -> print("|COCINA|")
                    else -> print("    |")
                }
            }
            println()
        }
    }
}
