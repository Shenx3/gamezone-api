package com.gamezone.gamezone_backend.controller

import com.gamezone.gamezone_backend.model.Product
import com.gamezone.gamezone_backend.repository.ProductRepository
import org.springframework.web.bind.annotation.*
import jakarta.annotation.PostConstruct
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/api/products")
class ProductController(private val productRepository: ProductRepository) {

    // 1. LISTAR PRODUCTOS (GET) -> /api/products
    @GetMapping
    fun getAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    // 2. CREAR PRODUCTO (POST) -> /api/products (para el administrador)
    @PostMapping
    fun createProduct(@RequestBody product: Product): Product {
        return productRepository.save(product)
    }

    // 3. EDITAR UN PRODUCTO (PUT) /api/products/{id}
    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody updatedProduct: Product): ResponseEntity<Product> {
        val existingProduct = productRepository.findById(id)
        return if (existingProduct.isPresent) {
            val productToUpdate = existingProduct.get().copy(
                title = updatedProduct.title,
                description = updatedProduct.description,
                price = updatedProduct.price,
                imageUrl = updatedProduct.imageUrl
            )
            ResponseEntity.ok(productRepository.save(productToUpdate))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // 4. ELIMINAR UN PRODUCTO (DELETE) /api/products/{id}
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Void> {
        return if (productRepository.existsById(id)) {
            productRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    /**
     * Inicializa los datos de productos destacados al iniciar el servidor (si la tabla está vacía).
     */
    @PostConstruct
    fun initData() {
        if (productRepository.count() == 0L) {
            val products = listOf(
                Product(title = "League Of Legends", description = "MOBA Multijugador, 3 lineas mas de 300 campeones para cualquier tipo de combinacion existente.", price = "$3.990", imageUrl = "https://i.pinimg.com/736x/20/54/b6/2054b617ac11f24e23d62e05f85303d4.jpg"),
                Product(title = "Remnant II", description = "Shooter multijugador MMORPG en el cual vas subiendo de nivel tu personaje a medida que avanzas en el extenso mundo de Remnant", price = "$24.990", imageUrl = "https://i.pinimg.com/1200x/ba/15/13/ba1513981d08c43c67f5c27053eda7cf.jpg"),
                Product(title = "God of War I", description = "Una épica aventura de acción protagonizada por Kratos en su misión de venganza contra los dioses griegos.", price = "$32.990", imageUrl = "https://i.pinimg.com/736x/b0/a6/1c/b0a61c3a3be8c2626e7a20d5ff9408bd.jpg"),
                Product(title = "God of War II", description = "Continuación de la saga, donde Kratos busca cambiar su destino enfrentándose a nuevos dioses y enemigos poderosos.", price = "$39.990", imageUrl = "https://i.pinimg.com/736x/a5/13/ac/a513acf7eaf24791953b3edfa75e2764.jpg"),
                Product(title = "Resident Evil 4 Remake", description = "Reimaginación del clásico survival horror con gráficos modernos y mecánicas mejoradas.", price = "$59.990", imageUrl = "https://i.pinimg.com/736x/6c/fc/92/6cfc9279823b78bea232ef1408586ac5.jpg")
            )
            productRepository.saveAll(products)
        }
    }
}