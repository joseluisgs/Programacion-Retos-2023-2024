package org.example.services.storage.bitacora

import java.io.File

/**
 * Implementa una operación de escritura de texto en un fichero plano, utilizada para guardar resultados de las batallas
 */
class BitacoraFileStorageTxt {
    fun anadirTexto(text: String, fileBitacora: File) {
        fileBitacora.appendText("$text\n")
    }

}