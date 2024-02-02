package org.example


import org.example.controllers.DCManager
import org.example.controllers.MarvelManager
import org.example.models.DC
import org.example.models.Marvel
import org.example.repositories.Marvel.MarvelRepository
import org.example.repositories.SuperHeroes.DCRepository
import org.example.validators.ValidadorSuperHeroes

val validadorSh = ValidadorSuperHeroes()

val marvelRepository = MarvelRepository()
val marvelManager = MarvelManager(marvelRepository, validadorSh)

val dcRepository = DCRepository()
val dcManager = DCManager(dcRepository, validadorSh)

fun main() {



    do { val passwordCorrecto:Boolean = comprobarPassword() } while (!passwordCorrecto)

    do {
        println("Super Hero Leaks")
        println("================")
        mostrarMenuOperaciones()
        print("\nSeleccione opción: ")

        val opcion = readln().toIntOrNull()?:-1
        when (opcion) {
            1-> mostrarSubMenuMarvel()
            2-> mostrarSubMenuDC()
        }
    } while (opcion!= 0)
    println("¡Vuelva pronto!")
}

fun mostrarSubMenuMarvel() {
    do {
        println("Base de Datos Marvel")
        println("====================")
        mostrarSubMenuOperaciones()
        print("\nSeleccione opción: ")

        val opcion = readln().toIntOrNull()?:-1
        when (opcion) {
            1-> menuAltaMarvel()
            2-> menuModifMarvel()
            3-> menuBajaMarvel()
            4-> menuMostrarInfoMarvel()
            5-> menuListadoOrdenIdMarvel()
            6-> menuListadoOrdenNombreMarvel()
            7-> menulistadoSinOrdenarMarvel()
        }
    } while (opcion!= 0)
}

fun mostrarSubMenuDC() {
    do {
        println("Base de Datos DC")
        println("====================")
        mostrarSubMenuOperaciones()
        print("\nSeleccione opción: ")

        val opcion = readln().toIntOrNull()?:-1
        when (opcion) {
            1-> menuAltaDC()
            2-> menuModifDC()
            3-> menubajaDC()
            4-> menuMostrarInfoDC()
            5-> menuListadoOrdenIdDC()
            6-> menuListadoOrdenNombreDC()
            7-> menulistadoSinOrdenarDC()
        }
    } while (opcion!= 0)
}

private fun mostrarSubMenuOperaciones() {
    println("1. Alta Superhéroe")
    println("2. Modificar Superhéroe")
    println("3. Baja Superhéroe")
    println("4. Información Superhéroe")
    println("5. Ordenar base por ID")
    println("6. Ordenar base por Nombre")
    println("7. Listado sin ordenar")
    println()
    println("0. Volver ")
}

private fun mostrarMenuOperaciones() {
    println("1. BD Marvel")
    println("2. BD DC")
    println()
    println("0. Salir")
}

fun comprobarPassword(): Boolean {
    return true
}

fun menuAltaDC() {

    println()
    println("Alta Superhéroe DC")
    println("======================")


    val sh: DC = getDatosNuevoSuperHeroeDC()

    val sHCreado = dcManager.save(sh)
    println("Correcto, se ha dado de alta al Superhéroe ${sHCreado.nombre} (Id = ${sHCreado.id}).")
}

fun getDatosNuevoSuperHeroeDC(): DC {
    var nombreSH:String = ""
    var aliasSH:String = ""
    var alturaSH:Int = 0
    var notasSH:String = ""

    nombreSH = getNombreSuperHeroeValido()
    /*
                aliasSH = getAliasSuperHeroeValido()
                alturaSH = getAlturaSuperHeroeValido()
                notasSH = getNotasSuperHeroeValido()
    */
    return DC(0,nombreSH, aliasSH, alturaSH, notasSH)
}

fun menuAltaMarvel() {

    println()
    println("Alta Superhéroe Marvel")
    println("======================")


    val sh: Marvel = getDatosNuevoSuperHeroeMarvel()

    val sHCreado = marvelManager.save(sh)
    println("Correcto, se ha dado de alta al Superhéroe ${sHCreado.nombre} (Id = ${sHCreado.id}).")

}

fun getDatosNuevoSuperHeroeMarvel(): Marvel {
    var nombreSH:String = ""
    var aliasSH:String = ""
    var alturaSH:Int = 0
    var notasSH:String = ""

    nombreSH = getNombreSuperHeroe()
    /*
                aliasSH = getAliasSuperHeroe()
                alturaSH = getAlturaSuperHeroe()
                notasSH = getNotasSuperHeroe()
    */
    return Marvel(0,nombreSH, aliasSH, alturaSH, notasSH)
}

private fun getNombreSuperHeroeValido(): String {
    var nombreSH:String = ""

        nombreSH = getNombreSuperHeroe()

    return nombreSH
}

fun getNotasSuperHeroe(): String {
    TODO("Not yet implemented")
}

fun getAlturaSuperHeroe(): Int {
    TODO("Not yet implemented")
}

fun getAliasSuperHeroe(): String {
    TODO("Not yet implemented")
}

fun getNombreSuperHeroe():String {
    print("Introduce Nombre del Superhéroe: ")
    return readln()
}

fun menuModifMarvel() {


    println()
    println("Modificar Información Superhéroe")
    println("================================")

    val nombreSH: String = getNombreSuperHeroe()

    try {
        val superHeroeActual:Marvel = marvelManager.getByName(nombreSH)
        println("Estos son los datos actuales del superhéroe:")
        println(superHeroeActual)

        println("Introduzca Nuevos Datos del Superhéroe:")
        val superHeroeModificado = getDatosModificarSuperHeroeMarvel(nombreSH)

        marvelManager.update(superHeroeActual.id,superHeroeModificado)

        println("Correcto, se ha modificado el superhéroe ${superHeroeModificado.nombre} (Id = ${superHeroeModificado.id}).")
        } catch (e:Exception) {
            println(e)
        }
}

fun getDatosModificarSuperHeroeMarvel(nombreSuperHeroe: String): Marvel {
    var nuevoNombreSH:String = ""
    var nuevoAliasSH:String = ""
    var nuevaAlturaSH:Int = 0
    var nuevasNotasSH:String = ""


    nuevoNombreSH = getNombreSuperHeroeValido()
    /*
    nuevoAliasSH = getAliasSuperHeroe()
    nuevaAlturaSH = getAlturaSuperHeroe()
    nuevasNotasSH = getNotasSuperHeroe()
    */

    return Marvel(0,nuevoNombreSH, nuevoAliasSH,nuevaAlturaSH,nuevasNotasSH)

}

fun getDatosModificarSuperHeroeDC(nombreSuperHeroe: String): DC {
    var nuevoNombreSH:String = ""
    var nuevoAliasSH:String = ""
    var nuevaAlturaSH:Int = 0
    var nuevasNotasSH:String = ""


    nuevoNombreSH = getNombreSuperHeroeValido()
    /*
    nuevoAliasSH = getAliasSuperHeroe()
    nuevaAlturaSH = getAlturaSuperHeroe()
    nuevasNotasSH = getNotasSuperHeroe()
    */

    return DC(0,nuevoNombreSH, nuevoAliasSH,nuevaAlturaSH,nuevasNotasSH)

}

fun menuBajaMarvel() {

    println()
    println("Baja Superhéroe Marvel")
    println("======================")

    // pedir datos superhéroe
    val nombreSH: String = getNombreSuperHeroe()


    try {
        val superHeroeActual= marvelManager.getByName(nombreSH)
        println("Estos son los datos actuales del superhéroe a eliminar:")
        println(superHeroeActual)

        val sHBorrado = marvelManager.delete(superHeroeActual.id)
        println("Correcto, se ha eliminado el superhéroe ${sHBorrado.nombre} (Id = ${sHBorrado.id}).")
    } catch (e: Exception) {
        println(e.message)
    }


}

fun menuMostrarInfoMarvel() {

    println()
    println("Información Superhéroe")
    println("======================")
    // pedir datos superhéroe
    val nombreSH: String = getNombreSuperHeroe()


    try {
        val superHeroeActual= marvelManager.getByName(nombreSH)
        println("Estos son los datos actuales del superhéroe:")
        println(superHeroeActual)

    } catch (e: Exception) {
        println(e.message)
    }
}

/**
 * Muestra el listado de superhéroes Marvel ordenado por Id
 */
fun menuListadoOrdenIdMarvel() {
    println("\nListado ordenado por ID - Marvel")
    println("==================================")

    try {
        mostrarRepoMarvel(marvelManager.sortedById())
    } catch (e:Exception) {
        println(e.message)
    }
}

/**
 * Muestra el listado de superhéroes Marvel ordenado por Nombre
 */
fun menuListadoOrdenNombreMarvel() {

    println("\nListado ordenado por Nombre - Marvel")
    println("==================================")

    try{
        mostrarRepoMarvel(marvelManager.sortedByName())
    } catch (e:Exception) {
        println(e.message)
    }

}

/**
 * Muestra el listado de superhéroes Marvel sin ordenar
 */
fun menulistadoSinOrdenarMarvel() {

    println("\nListado sin ordenar - Marvel")
    println("==========================")
    try{
        mostrarRepoMarvel(marvelManager.getAll())
    } catch (e:Exception) {
        println(e.message)
    }
}

/**
 * Muestra el contenido de un repositorio de superhéroes Marvel pasado por parámetro
 * @param dbMarvel Repositorio de tipo Marvel para mostrar
 */
fun mostrarRepoMarvel(dbMarvel:Array<Marvel>) {
    for (i in dbMarvel.indices) {
        print(" [ Pos $i ] \t")
            print("${dbMarvel[i]}")
    }
}
/**
 * Muestra el contenido de un repositorio de superhéroes Marvel pasado por parámetro
 * @param dbDC Repositorio de tipo DC para mostrar
 */
fun mostrarRepoDC(dbDC:Array<DC>) {
    for (i in dbDC.indices) {
        print(" [ Pos $i ] \t")
        print("${dbDC[i]}")
    }
}


fun menuModifDC() {
    println()
    println("Modificar Información Superhéroe")
    println("================================")

    val nombreSH: String = getNombreSuperHeroe()

    try {
        val superHeroeActual:DC = dcManager.getByName(nombreSH)
        println("Estos son los datos actuales del superhéroe:")
        println(superHeroeActual)

        println("Introduzca Nuevos Datos del Superhéroe:")
        val superHeroeModificado = getDatosModificarSuperHeroeDC(nombreSH)

        dcManager.update(superHeroeActual.id,superHeroeModificado)

        println("Correcto, se ha modificado el superhéroe ${superHeroeModificado.nombre} (Id = ${superHeroeModificado.id}).")
    } catch (e:Exception) {
        println(e)
    }
}

fun menubajaDC() {
    println()
    println("Baja Superhéroe DC")
    println("======================")

    // pedir datos superhéroe
    val nombreSH: String = getNombreSuperHeroe()


    try {
        val superHeroeActual= dcManager.getByName(nombreSH)
        println("Estos son los datos actuales del superhéroe a eliminar:")
        println(superHeroeActual)

        val sHBorrado = dcManager.delete(superHeroeActual.id)
        println("Correcto, se ha eliminado el superhéroe ${sHBorrado.nombre} (Id = ${sHBorrado.id}).")
    } catch (e: Exception) {
        println(e.message)
    }
}

fun menuMostrarInfoDC() {
    println()
    println("Información Superhéroe")
    println("======================")
    // pedir datos superhéroe
    val nombreSH: String = getNombreSuperHeroe()


    try {
        val superHeroeActual= dcManager.getByName(nombreSH)
        println("Estos son los datos actuales del superhéroe:")
        println(superHeroeActual)

    } catch (e: Exception) {
        println(e.message)
    }
}

fun menuListadoOrdenIdDC() {
    println("\nListado ordenado por ID - DC")
    println("==================================")

    try {
        mostrarRepoDC(dcManager.sortedById())
    } catch (e:Exception) {
        println(e.message)
    }
}

fun menuListadoOrdenNombreDC() {
    println("\nListado ordenado por Nombre - DC")
    println("==================================")

    try{
        mostrarRepoDC(dcManager.sortedByName())
    } catch (e:Exception) {
        println(e.message)
    }
}

fun menulistadoSinOrdenarDC() {
    println("\nListado sin ordenar - DC")
    println("==========================")
    try{
        mostrarRepoDC(dcManager.getAll())
    } catch (e:Exception) {
        println(e.message)
    }
}
