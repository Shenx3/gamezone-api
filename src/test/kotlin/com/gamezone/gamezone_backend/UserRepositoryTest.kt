package com.gamezone.gamezone_backend

import com.gamezone.gamezone_backend.model.User
import com.gamezone.gamezone_backend.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

/**
 * Test del repositorio UserRepository.
 * Verifica que findByUsername funcione correctamente.
 */
@DataJpaTest
class UserRepositoryTest @Autowired constructor(
    private val userRepository: UserRepository
) {

    @Test
    fun `findByUsername devuelve el usuario correcto`() {
        // Arrange: creamos y guardamos un usuario
        val user = User(
            id = null,
            username = "kenkyona",
            password = "password-encriptado",
            role = "ROLE_USER"
        )

        userRepository.save(user)

        // Act: buscamos por username
        val found = userRepository.findByUsername("kenkyona")

        // Assert
        assertThat(found).isNotNull()
        assertThat(found!!.username).isEqualTo("kenkyona")
        assertThat(found.role).isEqualTo("ROLE_USER")
    }
}
