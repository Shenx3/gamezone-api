package com.gamezone.gamezone_backend.dto

// Para /api/users/login
data class LoginRequest(val identifier: String, val contrasena: String)
data class LoginResponse(val id: Long, val nombreUsuario: String)

// Para /api/users/register
data class RegisterRequest(
    val nombreCompleto: String, val email: String, val telefono: String?,
    val nombreUsuario: String, val contrasena: String, val generoFavorito: String
)

// Para /api/users/password
data class PasswordResetRequest(val identifier: String, val newPassword: String)

// Respuesta genérica de mensaje
data class MessageResponse(val message: String, val userId: Long? = null)