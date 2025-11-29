package com.gamezone.gamezone_backend

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            // 1. Deshabilitar CSRF (es común en APIs REST sin cookies de sesión)
            .csrf { csrf: CsrfConfigurer<HttpSecurity> -> csrf.disable() }

            // 2. Definir políticas de sesión (Stateless para APIs REST)
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }

            // 3. Definir las reglas de autorización
            .authorizeHttpRequests { auth ->
                auth
                    // Permisos para swagger
                    .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/users/**").permitAll()

                    // Permitir acceso sin autenticación a las rutas públicas:
                    .requestMatchers("/api/users/register").permitAll() // Permite tu registro
                    .requestMatchers("/api/users/login").permitAll()    // Permite el login
                    .requestMatchers("/api/users/password").permitAll() // Permite la recuperación de contraseña
                    .requestMatchers("/api/products").permitAll()       // Permite ver la lista de productos (GET)
                    .requestMatchers(HttpMethod.GET, "/api/users/**").permitAll() // Permite obtener el id del usuario para ver su perfil
                    .requestMatchers(HttpMethod.PUT, "/api/users/**").permitAll()
                    // Para todas las demás rutas, se requiere autenticación:
                    .anyRequest().authenticated()
            }

            // 4. Deshabilitar la configuración de autenticación básica que Spring Security habilita por defecto
            .httpBasic { it.disable() }
            .formLogin { it.disable() }

            // 5. Manejo de excepciones (para que el 401 sea limpio)
            .exceptionHandling { handlers: ExceptionHandlingConfigurer<HttpSecurity?> ->
                handlers.authenticationEntryPoint { _, response, authException ->
                    response.status = 401
                    response.writer.write("Unauthorized: ${authException.message}")
                }
            }

        return http.build()
    }
}