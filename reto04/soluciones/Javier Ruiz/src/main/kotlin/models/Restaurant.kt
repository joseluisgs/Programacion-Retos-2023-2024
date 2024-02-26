package org.example.models


class Restaurant {

    private val javi=Cliente()
    private val pepe = Cliente()
    private val ramon = Cliente()
    private val luis = Cliente()
    private val gabor = Cliente()
    private val pedro = Cliente()

    private val restaurante: Array<Array<Camarero?>> = Array(7) { Array(3) { null } }
    private val listCliente = listOf(javi, pepe, ramon, luis, gabor, pedro)
    private val listEspera = mutableListOf(Cliente())


    private val cam1 = Camarero(1, 6)
    private val cam2 = Camarero(2, 6)
    private val cam3 = Camarero(3, 6)


    fun posCamareros() {

        restaurante[cam1.fila][cam1.columna - 1] = cam1
        restaurante[cam2.fila][cam2.columna - 1] = cam2
        restaurante[cam3.fila][cam3.columna - 1] = cam3

    }


    fun printRestaurant() {
        for (i in restaurante.indices) {
            for (j in restaurante[i].indices) {
                when (i) {
                    0 -> {
                        print("[  Mesa ${j + 1}  ]")
                    }

                    6 -> {
                        print("[ Cocina ${j + 1} ]")
                    }

                    else -> {
                        when (restaurante[i][j]) {
                            cam1 -> print("[Camarero ${j + 1}]")
                            cam2 -> print("[Camarero ${j + 1}]")
                            cam3 -> print("[Camarero ${j + 1}]")
                            else -> print("[          ]")
                        }
                    }
                }

            }
            println()
        }
    }


    fun simular(){
       listEspera.addLast(listCliente.random())
        println(listEspera.toString())
        posCamareros()
        moverCam(cam1)
        moverCam(cam2)
        moverCam(cam3)
        printRestaurant()
    }

    private fun moverCam(cam: Camarero) {

        val fila = cam.fila
        val reverse = cam.reverse
        var nuevaFila=fila
        comprobarPosicion(fila, reverse)
        if (!reverse) {
            nuevaFila -= 1
            cam.fila = nuevaFila
        } else {
            nuevaFila += 1
            cam.fila =  nuevaFila
        }
        restaurante[fila][cam.columna-1]=null
        restaurante[nuevaFila][cam.columna-1]=cam
    }

    private fun comprobarPosicion(fila: Int, rev: Boolean): Boolean {

        var reverse = rev
        if (fila == restaurante.size) {
            reverse = false
        } else if (fila == 0) {
            reverse = true
        }
        return reverse
    }

}



