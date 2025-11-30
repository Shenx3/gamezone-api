# 🕹️ GameZone API (Backend)

API RESTful desarrollada con **Spring Boot y Kotlin** para dar soporte a la aplicación móvil de comercio electrónico GameZone. Este backend maneja la lógica de negocio, la persistencia de datos y la seguridad, siendo el core de la evaluación.

## 🚀 Tecnologías Clave del Backend (Spring Boot / Kotlin)

* **Framework:** Spring Boot 3+
* **Lenguaje de Programación:** Kotlin
* **Base de Datos:** H2 (Base de datos en memoria configurada en `application.properties`)
* **Persistencia:** Spring Data JPA (utilizando entidades para User y Product)
* **Seguridad y Autenticación:** Spring Security (configurado en `SecurityConfig.kt`)
    * Manejo de la autenticación (`/api/auth/login`, `/api/auth/register`).
* **Documentación:** OpenAPI (Swagger UI, configurado en `OpenApiConfig.kt`)
* **Tests:** JUnit 5 y Mockito (Implementación de tests unitarios/de integración).

## 📂 Estructura de Módulos

El proyecto sigue una arquitectura organizada por capas:

1.  **`model/`**: Definición de las entidades JPA para la persistencia.
    * `User.kt`: Entidad para los datos del usuario.
    * `Product.kt`: Entidad para el catálogo de productos.
2.  **`repository/`**: Interfaces de Spring Data JPA para el acceso a la base de datos.
    * `UserRepository.kt`
    * `ProductRepository.kt`
3.  **`controller/`**: Capa que expone los endpoints REST.
    * `UserController.kt`: Maneja la autenticación (registro, login) y operaciones de usuario.
    * `ProductController.kt`: Maneja las operaciones del catálogo (obtener, crear, etc.).
4.  **`dto/`**: Objetos de Transferencia de Datos (DTOs) para peticiones y respuestas.
    * `AuthDtos.kt`: Contiene estructuras como `LoginRequest`, `RegisterRequest`, y `LoginResponse`.
5.  **`config/`**: Archivos de configuración general.
    * `SecurityConfig.kt`: Configuración de la cadena de filtros de seguridad.
    * `OpenApiConfig.kt`: Configuración de la documentación Swagger.

## 🔗 Endpoints Principales

El frontend móvil está diseñado para interactuar con los siguientes endpoints:

| Endpoint | Método | Descripción | Requiere Auth |
| :--- | :--- | :--- | :--- |
| `/api/auth/register` | `POST` | Registra un nuevo usuario. | No |
| `/api/auth/login` | `POST` | Autentica al usuario y devuelve un token/sesión (simulado). | No |
| `/api/products` | `GET` | Obtiene la lista completa de productos. | Sí |
| `/api/products/{id}` | `GET` | Obtiene un producto por su ID. | Sí |
| `/api/products` | `POST` | Agrega un nuevo producto (Admin/Testing). | Sí |

## ⚙️ Configuración y Ejecución

**Base de Datos:**
La configuración en `src/main/resources/application.properties` está definida para usar la base de datos en memoria H2.

```properties
spring.datasource.url=jdbc:h2:mem:gamezonedb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
