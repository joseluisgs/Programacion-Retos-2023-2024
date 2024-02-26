package models

import kotlin.random.Random

class Restaurante(val nombre:String) {

    private val restaurante: Array<Array<IntegrantesRestaurante?>> = Array(7) { Array(3) { null } }

    private val mesa1 = Mesa(1, 0, 0)
    private val mesa2 = Mesa(2, 0, 1)
    private val mesa3 = Mesa(3, 0, 2)

    private val camarero1 = Camarero("JOSE",5,0)
    private val camarero2 = Camarero("DANI", 5,1)
    private val camarero3 = Camarero("MANU", 5,2)

    private val cocina1 = Cocina(6,0)
    private val cocina2 = Cocina(6,1)
    private val cocina3 = Cocina(6,2)

    private val cliente1 = Cliente("RAUL",null)
    private val cliente2 = Cliente("JAVI",null)
    private val cliente3 = Cliente("MOHA",null)
    private val cliente4 = Cliente("SAMUEL",null)
    private val cliente5 = Cliente("MARIO",null)
    private val cliente6 = Cliente("ALBA",null)
    private val cliente7 = Cliente("NICO",null)
    private val cliente8 = Cliente("NATALIA",null)
    private val cliente9 = Cliente("CARLOS",null)
    private val cliente10 = Cliente("ALEX",null)

    private val menu1= Menu(1,10)
    private val menu2= Menu(2,20)
    private val menu3= Menu(3,30)
    private val menu4= Menu(4,40)
    private val menu5= Menu(5,50)

    private var contadoCli=0
    private var caja=0


    fun simulacion(){

        posicionarElementos()
        mostrarRest()
        println()
        do {
            posicionarClientes()

            moverCamarerosAMesa()



            moverCamarerosACocina()

            cocinar()

            mostrarRest()
            println()
            ponerComida()
            pedidoClientes()
            comer()
            salir()
            Thread.sleep(1000)
        }while (contadoCli<10)
        mostrarRest()
        informe()
    }

    private fun informe() {
        println("HAN PASADO UN TOTAL DE $contadoCli CLIENTES")
        println(" CON UN TOTAL RECAUDADO DE $caja €")
    }

    private fun salir() {
        clientesalir(cliente1)
        clientesalir(cliente2)
        clientesalir(cliente3)
        clientesalir(cliente4)
        clientesalir(cliente5)
        clientesalir(cliente6)
        clientesalir(cliente7)
        clientesalir(cliente8)
        clientesalir(cliente9)
        clientesalir(cliente10)
    }

    private fun clientesalir(cliente: Cliente) {
        if (cliente.terminado && !cliente.marcado){
            println("EL CLIENTE ${cliente.nombre} PAGA SU CUENTE  DE ${cliente.cuenta}€")
            caja+=cliente.cuenta
            cliente.sentado=false
            cliente.marcado=true
            contadoCli++
            when(cliente.mesa){
                mesa1->{
                    mesa1.ocupada=false
                    camarero1.atendido=false
                    camarero1.pedidoEntregado=false
                    cocina1.resetCocina()
                }
                mesa2->{
                    mesa2.ocupada=false
                    camarero2.atendido=false
                    camarero2.pedidoEntregado=false
                    cocina2.resetCocina()
                }
                mesa3->{
                    mesa3.ocupada=false
                    camarero3.atendido=false
                    camarero3.pedidoEntregado=false
                    cocina3.resetCocina()
                }

            }
        }
    }

    private fun comer() {
        clienteComer(cliente1)
        clienteComer(cliente2)
        clienteComer(cliente3)
        clienteComer(cliente4)
        clienteComer(cliente5)
        clienteComer(cliente6)
        clienteComer(cliente7)
        clienteComer(cliente8)
        clienteComer(cliente9)
        clienteComer(cliente10)
    }

    private fun clienteComer(cliente: Cliente) {
        if (cliente.comidaEnMesa && !cliente.terminado)cliente.comer()
    }

    private fun ponerComida() {
        ponerComidaClientes(cliente1)
        ponerComidaClientes(cliente2)
        ponerComidaClientes(cliente3)
        ponerComidaClientes(cliente4)
        ponerComidaClientes(cliente5)
        ponerComidaClientes(cliente6)
        ponerComidaClientes(cliente7)
        ponerComidaClientes(cliente8)
        ponerComidaClientes(cliente9)
        ponerComidaClientes(cliente10)
    }

    private fun ponerComidaClientes(cliente: Cliente) {
        when(cliente.mesa){
            mesa1->{
                if (camarero1.atendido && camarero1.fila == 1 && cocina1.cocinado && !cliente.marcado){
                    cliente.comidaEnMesa=true
                    camarero1.pedidoEntregado=true
                    println("COMIDA ENTREGADA A CLIENTE ${cliente.nombre}")
                }
            }
            mesa2->{
                if (camarero2.atendido && camarero2.fila ==1 && cocina2.cocinado && !cliente.marcado){
                    cliente.comidaEnMesa=true
                    camarero2.pedidoEntregado=true
                    println("COMIDA ENTREGADA A CLIENTE ${cliente.nombre}")
                }
            }
            mesa3->{
                if (camarero3.atendido && camarero3.fila ==1 && cocina3.cocinado && !cliente.marcado){
                    cliente.comidaEnMesa=true
                    camarero3.pedidoEntregado=true
                    println("COMIDA ENTREGADA A CLIENTE ${cliente.nombre}")
                }
            }
        }
    }

    private fun cocinar() {
        cocina(cocina1,camarero1,mesa1)
        cocina(cocina2,camarero2,mesa2)
        cocina(cocina3,camarero3,mesa3)
    }

    private fun cocina(cocina: Cocina, camarero: Camarero, mesa: Mesa) {
        if (camarero.fila==5 && mesa.ocupada && !cocina.cocinado){
            cocina.cocinar(camarero)
        }
    }

    private fun moverCamarerosACocina() {
        moverCamareroACocina(camarero1,mesa1,cocina1)
        moverCamareroACocina(camarero2,mesa2,cocina2)
        moverCamareroACocina(camarero3,mesa3,cocina3)
    }

    private fun moverCamareroACocina(camarero: Camarero, mesa: Mesa, cocina: Cocina) {
        if (mesa.ocupada && camarero.atendido && !cocina.cocinado || camarero.pedidoEntregado){
            if (camarero.fila < 5){
                restaurante[camarero.fila][camarero.columna]=null
                camarero.moverACocina()
                restaurante[camarero.fila][camarero.columna]=camarero
            }
        }
    }

    private fun pedidoClientes() {
        pedidoCliente(cliente1)
        pedidoCliente(cliente2)
        pedidoCliente(cliente3)
        pedidoCliente(cliente4)
        pedidoCliente(cliente5)
        pedidoCliente(cliente6)
        pedidoCliente(cliente7)
        pedidoCliente(cliente8)
        pedidoCliente(cliente9)
        pedidoCliente(cliente10)
    }

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

    private fun interraccionCamarero(camarero: Camarero, cliente: Cliente) {
        if (camarero.fila==1 && !camarero.atendido){
            cliente.notaPedida=true
            camarero.atendido=true
            cliente.tiempoDeEspera()
            println("CLIENTE ${cliente.nombre}")
            println("BUENOS DIAS BIENVENIDO AL RESTAURANTE")
            println("QUE MENU DE LOS 5 DESEA")
            val menu=cliente.seleccionMenu()
            when(menu){
                1->{
                    println("QUIERO EL MENU 1")
                    cliente.cuenta=menu1.precio
                    cliente.menu=menu1
                }
                2->{
                    println("QUIERO EL MENU 2")
                    cliente.cuenta=menu2.precio
                    cliente.menu=menu2
                }
                3->{
                    println("QUIERO EL MENU 3")
                    cliente.cuenta=menu3.precio
                    cliente.menu=menu3
                }
                4->{
                    println("QUIERO EL MENU 4")
                    cliente.cuenta=menu4.precio
                    cliente.menu=menu4
                }
                5->{
                    println("QUIERO EL MENU 5")
                    cliente.cuenta=menu5.precio
                    cliente.menu=menu5
                }
            }
        }
    }

    private fun moverCamarerosAMesa() {
        moverCamareroAMesa(mesa1,camarero1,cocina1)
        moverCamareroAMesa(mesa2,camarero2,cocina2)
        moverCamareroAMesa(mesa3,camarero3,cocina3)

    }


    private fun moverCamareroAMesa(mesa: Mesa, camarero: Camarero, cocina: Cocina) {
        if (mesa.ocupada && !camarero.atendido || cocina.cocinado && !camarero.pedidoEntregado){
            if (camarero.fila > 1){
                restaurante[camarero.fila][camarero.columna]=null
                camarero.moverAMesa()
                restaurante[camarero.fila][camarero.columna]=camarero
            }

        }

    }

    private fun posicionarClientes() {
        posicionClientes(cliente1)
        posicionClientes(cliente2)
        posicionClientes(cliente3)
        posicionClientes(cliente4)
        posicionClientes(cliente5)
        posicionClientes(cliente6)
        posicionClientes(cliente7)
        posicionClientes(cliente8)
        posicionClientes(cliente9)
        posicionClientes(cliente10)
    }

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

    private fun comprobarMesa(mesa:Mesa , cliente:Cliente){
        if (!mesa.ocupada){
            cliente.mesa=mesa
            cliente.sentado=true
            mesa.ocupada=true

        }
    }
    private fun mostrarRest() {
        for (i in restaurante.indices){
            println("----- ----- ----- ")
            for (j in restaurante[i].indices){
                when(restaurante[i][j]){
                    is Mesa-> {
                        if ((restaurante[i][j] as Mesa).ocupada) print("OCUPA|")
                        else print("MESA${(restaurante[i][j] as Mesa).numMesa}|")
                    }
                    is Camarero -> print("${(restaurante[i][j] as Camarero).nombre} |")
                    is Cocina -> print("COCIN|")
                    else -> print("     |")
                }
            }
           println()
        }
    }

    private fun posicionarElementos() {
        restaurante[mesa1.fila][mesa1.columna]=mesa1
        restaurante[mesa2.fila][mesa2.columna]=mesa2
        restaurante[mesa3.fila][mesa3.columna]=mesa3

        restaurante[camarero1.fila][camarero1.columna]=camarero1
        restaurante[camarero2.fila][camarero2.columna]=camarero2
        restaurante[camarero3.fila][camarero3.columna]=camarero3

        restaurante[cocina1.fila][cocina1.columna]=cocina1
        restaurante[cocina2.fila][cocina2.columna]=cocina2
        restaurante[cocina3.fila][cocina3.columna]=cocina3

    }
}