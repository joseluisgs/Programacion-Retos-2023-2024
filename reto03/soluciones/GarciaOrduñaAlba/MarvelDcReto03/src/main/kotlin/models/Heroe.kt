package org.example.models
/**
 * Clase que representa a un héroe con sus atributos.
 * @property nombre El nombre del héroe.
 * @property alias El alias o apodo del héroe.
 * @property altura La altura del héroe en centímetros.
 * @property edad La edad del héroe.
 * @property notas Notas adicionales sobre el héroe.
 * @property id El identificador único del héroe (predeterminado: 0).
 * @property createdAt La marca de tiempo de creación del héroe (en milisegundos).
 * @property updatedAt La marca de tiempo de la última actualización del héroe (en milisegundos).
 */
data class Heroe(
    var nombre: String,         // Nombre del héroe
    var alias: String,          // Alias del héroe
    var altura: Int,            // Altura del héroe en centímetros
    var edad: Int,              // Edad del héroe
    var notas: String,          // Notas adicionales sobre el héroe
    var id: Int = 0,            // ID del héroe (predeterminado: 0)
    var createdAt: Long = System.currentTimeMillis(),   // Marca de tiempo de creación del héroe (en milisegundos)
    var updatedAt: Long = System.currentTimeMillis()   // Marca de tiempo de actualización del héroe (en milisegundos)
)