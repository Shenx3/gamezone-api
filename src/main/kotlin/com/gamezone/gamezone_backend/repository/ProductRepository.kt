package com.gamezone.gamezone_backend.repository

import com.gamezone.gamezone_backend.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>