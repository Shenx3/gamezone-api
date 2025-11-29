package com.gamezone.gamezone_backend.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("GameZone API - Microservicio de E-commerce")
                    .version("1.0")
                    .description("API REST para la gestión de usuarios y el catálogo de videojuegos.")
            )
    }
}