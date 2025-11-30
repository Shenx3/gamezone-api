package com.gamezone.gamezone_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
/**
 * Clase principal de la aplicación GameZone.
 * Arranca Spring Boot y levanta el servidor en el puerto configurado.
 */
@SpringBootApplication
class GamezoneBackendApplication

fun main(args: Array<String>) {
	runApplication<GamezoneBackendApplication>(*args)
}
