package com.gamezone.gamezone_backend.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    //definiciones
    val nombreCompleto: String,
    @Column(unique = true, nullable = false)
    val email: String,
    val telefono: String? = null,
    @Column(unique = true, nullable = false)
    val nombreUsuario: String,

    // Contraseña: Dejamos sin hash por simplicidad de la demo con el móvil
    @Column(nullable = false)
    val contrasena: String,

    val generoFavorito: String,
    // La Uri de la foto que guardaste en el móvil se persiste como String
    val profilePhotoUri: String? = null
)