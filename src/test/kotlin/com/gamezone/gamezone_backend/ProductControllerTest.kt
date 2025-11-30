package com.gamezone.gamezone_backend.controller

import com.gamezone.gamezone_backend.model.Product
import com.gamezone.gamezone_backend.repository.ProductRepository
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * Test de la capa web para ProductController.
 * Levanta el contexto completo de Spring Boot y usa MockMvc para
 * simular llamadas HTTP reales contra la API de productos.
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // desactiva filtros de seguridad en los tests
class ProductControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val productRepository: ProductRepository
) {

    @BeforeEach
    fun cleanDb() {
        // Dejamos la tabla limpia antes de cada test
        productRepository.deleteAll()
    }

    @Test
    fun `GET api products devuelve lista de productos`() {
        // Arrange: insertamos un producto real (God of War I, del front)
        val gow1 = Product(
            id = null,
            name = "God of War I",
            description = "Una épica aventura de acción protagonizada por Kratos.",
            price = 32_990.0,
            imageUrl = "https://i.pinimg.com/736x/b0/a6/1c/b0a61c3a3be8c2626e7a20d5ff9408bd.jpg"
        )

        productRepository.save(gow1)

        // Act + Assert
        mockMvc.perform(
            get("/api/products")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize<Any>(1)))
            .andExpect(jsonPath("$[0].name").value("God of War I"))
    }

    @Test
    fun `POST api products crea un producto`() {
        // JSON que se envía en el body (basado en God of War II)
        val jsonBody = """
            {
              "name": "God of War II",
              "description": "Continuación de la saga, donde Kratos busca cambiar su destino.",
              "price": 39990.0,
              "imageUrl": "https://i.pinimg.com/736x/a5/13/ac/a513acf7eaf24791953b3edfa75e2764.jpg"
            }
        """.trimIndent()

        mockMvc.perform(
            post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)
        )
            .andExpect(status().isOk) // cambia a isCreated() si tu controlador devuelve 201
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value("God of War II"))
    }
}
