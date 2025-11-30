package com.gamezone.gamezone_backend

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
/**
 * Test de humo (smoke test).
 * Verifica que el contexto de Spring Boot se levanta correctamente.
 * Si este test falla, hay un problema de configuración global en el backend.
 */
@SpringBootTest
class GamezoneBackendApplicationTests {

	@Test
	fun contextLoads() {
        // No hace nada: solo comprobar que la aplicación arranca sin errores.
	}

}
