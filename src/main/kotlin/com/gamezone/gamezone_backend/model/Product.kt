package com.gamezone.gamezone_backend.model

import jakarta.persistence.*

@Entity
@Table(name = "products")
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val title: String,
    @Column(length = 500)
    val description: String,
    val price: String,
    val imageUrl: String
)