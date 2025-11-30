package com.gamezone.gamezone_backend.repository

import com.gamezone.gamezone_backend.model.Product
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

/**
 * Test de repositorio para ProductRepository.
 * Verifica operaciones básicas de BD: guardar y buscar productos.
 */
@DataJpaTest
class ProductRepositoryTest @Autowired constructor(
    private val productRepository: ProductRepository
) {

    @Test
    fun `guardar un producto y verificar que se almacena correctamente`() {

        // Producto tomado desde tu frontend (FeaturedProducts)
        val product = Product(
            id = null,
            name = "God of War I",
            description = "Una épica aventura de acción protagonizada por Kratos.",
            price = 32990.0,
            imageUrl = "https://i.pinimg.com/736x/b0/a6/1c/b0a61c3a3be8c2626e7a20d5ff9408bd.jpg"
        )

        val saved = productRepository.save(product)

        assertThat(saved.id).isNotNull()
        assertThat(saved.name).isEqualTo("God of War I")
        assertThat(saved.price).isEqualTo(32990.0)
    }

    @Test
    fun `guardar varios productos y verificar que findAll los devuelve`() {

        val products = listOf(
            Product(
                id = null,
                name = "God of War II",
                description = "Continuación de la saga.",
                price = 39990.0,
                imageUrl = "https://i.pinimg.com/736x/a5/13/ac/a513acf7eaf24791953b3edfa75e2764.jpg"
            ),
            Product(
                id = null,
                name = "Resident Evil 4 Remake",
                description = "Reimaginación del clásico survival horror.",
                price = 59990.0,
                imageUrl = "https://i.pinimg.com/736x/6c/fc/92/6cfc9279823b78bea232ef1408586ac5.jpg"
            )
        )

        productRepository.saveAll(products)

        val list = productRepository.findAll()

        assertThat(list.size).isEqualTo(2)
        assertThat(list.map { it.name }).contains("God of War II", "Resident Evil 4 Remake")
    }
}
