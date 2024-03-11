package org.example.storage

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.dto.HeroDto
import org.example.mapper.toHero
import org.example.mapper.toHeroDtop
import org.example.models.Hero
import java.nio.file.Files
import kotlin.io.path.Path

class InformeStorageJson: FileStorage<String> {
    private val  file = Path("informe" , "informe.json").toFile()
    override fun readFromFile(): List<String> {
        return Json{ignoreUnknownKeys = true}
            .decodeFromString<List<String>>(file.readText())

    }

    override fun writeFromFile(list: List<String>) {
        if(!file.exists()){
            Files.createDirectories(Path("informe"))
            file.createNewFile()
            file.writeText(
                Json{
                    ignoreUnknownKeys = true
                    prettyPrint = true
                }.encodeToString<List<String>>(
                    list.map { it }
                )
            )
        }
    }
}