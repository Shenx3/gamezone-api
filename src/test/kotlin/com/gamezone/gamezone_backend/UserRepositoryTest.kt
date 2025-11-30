package com.gamezone.gamezone_backend

import com.gamezone.gamezone_backend.model.User
import com.gamezone.gamezone_backend.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

/**
 * Test del repositorio UserRepository.
 * Verifica la búsqueda por identificador (email o nombre de usuario).
 */
@DataJpaTest
class UserRepositoryTest @Autowired constructor(
    private val userRepository: UserRepository
) {

    @Test
    fun `findByEmailOrNombreUsuario devuelve el usuario correcto`() {
        // Arrange: creamos y guardamos un usuario usando los nuevos campos
        val user = User(
            id = 0, // El ID se inicializa a 0 o se deja el valor por defecto
            nombreCompleto = "Kenkyona Bravo",
            email = "kenkyona@gamezone.cl",
            telefono = null,
            nombreUsuario = "kenkyona", // ✅ Nuevo campo
            contrasena = "Password123", // ✅ Nuevo campo
            generoFavorito = "Aventura" // ✅ Nuevo campo
        )

        userRepository.save(user)

        // Act 1: buscamos por nombreUsuario
        val foundByUsername = userRepository.findByEmailOrNombreUsuario("kenkyona", "kenkyona")

        // Act 2: buscamos por email
        val foundByEmail = userRepository.findByEmailOrNombreUsuario("kenkyona@gamezone.cl", "kenkyona@gamezone.cl")


        // Assert 1 (Username)
        assertThat(foundByUsername).isNotNull()
        assertThat(foundByUsername!!.nombreUsuario).isEqualTo("kenkyona") // ✅ Assertions para nombreUsuario
        assertThat(foundByUsername.email).isEqualTo("kenkyona@gamezone.cl")

        // Assert 2 (Email)
        assertThat(foundByEmail).isNotNull()
        assertThat(foundByEmail!!.nombreUsuario).isEqualTo("kenkyona")
    }
}