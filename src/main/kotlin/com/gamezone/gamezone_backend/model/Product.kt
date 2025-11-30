package com.gamezone.gamezone_backend.model

import jakarta.persistence.*

/**
 * Entidad JPA que representa un producto de la tienda GameZone.
 * Esta clase se mapea a una tabla en MySQL y la usa la app móvil para mostrar los productos.
 */
@Entity
data class Product(
    /**
     * Identificador único del producto (clave primaria, autoincremental).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    /**
     * Nombre del producto (ej: "Control PS5", "Mario Kart 8").
     */
    val title: String,

    /**
     * Descripción breve del producto.
     */
    val description: String,

    /**
     * Precio del producto.
     */
    val price: String,

    /**
     * URL de la imagen que se muestra en la app móvil.
     */
    val imageUrl: String
)
