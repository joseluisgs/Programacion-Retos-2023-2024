package org.example.services.storage

import com.github.michaelbull.result.Result
import org.example.exceptions.storage.StorageError
import java.io.File

interface Storage<T> {
    fun store(data: List<T>): Result<Unit, StorageError>
    fun load(fileName: String): Result<List<T>, StorageError>
}