package models

data class Menu(val numMenu: Int, val precio: Int): IntegrantesRestaurante{
    fun verMenus(){
        var opcion=-1
        do {
            println("Menu:")
            println("1. VER MENU1")
            println()
            println("2. VER MENU2")
            println()
            println("3. VER MENU3")
            println()
            println("4. VER MENU4")
            println()
            println("5. VER MENU5")
            println("6. Salir")
            print("Seleccione una opcion: ")
            opcion= readln().toIntOrNull() ?: -1
            when (opcion) {
                1 -> {
                    Menu1()
                }
                2 -> {
                    Menu2()
                }
                3 -> {
                    Menu3()
                }
                4 -> {
                    Menu4()
                }
                5 -> {
                    Menu5()
                }
                else -> {
                    println("Opcion no valida")
                }
            }
        }while (opcion!=5)

            println()
    }

    fun Menu1(){

            println("Menu1: 35$")
            println("PRIMER PLATO:")
            println("ENSALADA CESAR CON BROCOLI")
            println()
            println("SEGUNDO PLATO:")
            println("CALABACINES RELLENOS DE POLLO Y CEBOLLA")
            println()
            println("POSTRE:")
            println("HELADO CREMOSO DE PLATANO")
            println()
            println("SALIR")

            println()
    }

    fun Menu2(){
            println("Menu2: 44$")
            println("PRIMER PLATO:")
            println("CREMA ESSAU DE LENTEJAS")
            println()
            println("SEGUNDO PLATO:")
            println("ASADILLO MANCHEGO")
            println()
            println("POSTRE:")
            println("TRUFAS DE AGUACATE")
            println()
            println("SALIR")

    }

    fun Menu3(){

            println("Menu3: 60$")
            println()
            println("PRIMER PLATO:")
            println("PURE DE CALABAZA")
            println()
            println("SEGUNDO PLATO:")
            println("SARTEN DE GARBANZOS")
            println()
            println("POSTRE:")
            println("BIZCOCHO DE CALABAZA SIN AZÚCAR")
    }

    fun Menu4(){
            println("Menu4: 48$")
            println()
            println("PRIMER PLATO:")
            println("JUDÍAS VERDES")
            println()
            println("SEGUNDO PLATO:")
            println("MERLUZA EN SALSA VERDE")
            println()
            println("POSTRE:")
            println("NATILLAS CASERAS")
    }

    fun Menu5(){
            println("Menu5: 50$")
        println()
            println("PRIMER PLATO:")
            println("PISTO DE VERDURAS")
        println()
            println("SEGUNDO PLATO:")
            println("FILETE DE HALIBUT")
        println()
            println("POSTRE:")
            println("TARTA DE QUESO")
    }
}