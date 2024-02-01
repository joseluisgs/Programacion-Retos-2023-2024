package org.example
import org.example.repository.DC
import org.example.repository.Marvel

fun main() {
    // Solicita al usuario que ingrese una contraseña
    print("Enter password: ")
    // Lee la contraseña ingresada por el usuario y la convierte a mayúsculas
    val contraseña = readLine()?.uppercase()
    // Instancia objetos de las clases Marvel y DC
    val marvel = Marvel()
    val dc = DC()

    // Utiliza una expresión 'when' para determinar qué menú mostrar según la contraseña ingresada
    val repository: Any = when (contraseña) {
        "MARVEL" -> marvel.menu() // Muestra el menú de Marvel si la contraseña es "MARVEL"
        "DC" -> dc.menu() // Muestra el menú de DC si la contraseña es "DC"
        else -> {
            // Si la contraseña no coincide con ninguna opción, muestra un mensaje de error y finaliza el programa
            println("Contraseña incorrecta❌❌, el programa se cerrará...")
            return
        }
    }
}