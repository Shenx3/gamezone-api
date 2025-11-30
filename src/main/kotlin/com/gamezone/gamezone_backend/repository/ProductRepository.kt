package com.gamezone.gamezone_backend.repository

import com.gamezone.gamezone_backend.model.Product
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Capa de acceso a datos (repositorio) para Product.
 * Encapsula las operaciones CRUD contra la tabla de productos en MySQL.
 */

interface ProductRepository : JpaRepository<Product, Long>
