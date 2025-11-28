package com.gamezone.gamezone_backend.repository

import com.gamezone.gamezone_backend.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    // Usado para el Login y ForgotPassword (busca por Email O Usuario)
    fun findByEmailOrNombreUsuario(identifier1: String, identifier2: String): User?

    fun existsByEmail(email: String): Boolean
    fun existsByNombreUsuario(nombreUsuario: String): Boolean
}