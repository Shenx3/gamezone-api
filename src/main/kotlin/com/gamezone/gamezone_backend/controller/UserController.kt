package com.gamezone.gamezone_backend.controller


import com.gamezone.gamezone_backend.model.User
import com.gamezone.gamezone_backend.repository.UserRepository
import com.gamezone.gamezone_backend.dto.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userRepository: UserRepository) {

    // 1. REGISTRO (POST) -> /api/users/register
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<Any> {
        if (userRepository.existsByEmail(request.email) || userRepository.existsByNombreUsuario(request.nombreUsuario)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageResponse("El email o nombre de usuario ya está registrado."))
        }

        val newUser = User(
            nombreCompleto = request.nombreCompleto,
            email = request.email,
            telefono = request.telefono,
            nombreUsuario = request.nombreUsuario,
            contrasena = request.contrasena,
            generoFavorito = request.generoFavorito
        )
        val savedUser = userRepository.save(newUser)
        return ResponseEntity.status(HttpStatus.CREATED).body(MessageResponse("Registro exitoso.", savedUser.id))
    }

    // 2. LOGIN (POST) -> /api/users/login
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<Any> {
        val user = userRepository.findByEmailOrNombreUsuario(request.identifier, request.identifier)

        if (user == null || user.contrasena != request.contrasena) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(MessageResponse("Credenciales incorrectas."))
        }

        // Retorna el ID para el SessionManager de la app móvil
        return ResponseEntity.ok(LoginResponse(id = user.id, nombreUsuario = user.nombreUsuario))
    }

    // 3. RECUPERAR CONTRASEÑA (PUT) -> /api/users/password
    @PutMapping("/password")
    fun resetPassword(@RequestBody request: PasswordResetRequest): ResponseEntity<MessageResponse> {
        val user = userRepository.findByEmailOrNombreUsuario(request.identifier, request.identifier)
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageResponse("Usuario no encontrado."))
        }

        val updatedUser = user.copy(contrasena = request.newPassword)
        userRepository.save(updatedUser)
        return ResponseEntity.ok(MessageResponse("Contraseña actualizada con éxito."))
    }

    // 4. OBTENER PERFIL (GET) -> /api/users/{userId}
    @GetMapping("/{userId}")
    fun getUserProfile(@PathVariable userId: Long): ResponseEntity<User> {
        val user = userRepository.findById(userId)
        return if (user.isPresent) ResponseEntity.ok(user.get()) else ResponseEntity.notFound().build()
    }

    // 5. ACTUALIZAR FOTO DE PERFIL (PUT) -> /api/users/{userId}/photo
    @PutMapping("/{userId}/photo")
    fun updateProfilePhoto(@PathVariable userId: Long, @RequestBody requestBody: Map<String, String>): ResponseEntity<MessageResponse> {
        val newUri = requestBody["profilePhotoUri"]
        val user = userRepository.findById(userId).orElse(null)

        if (user == null || newUri == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageResponse("Usuario o URI inválida."))
        }

        val updatedUser = user.copy(profilePhotoUri = newUri)
        userRepository.save(updatedUser)
        return ResponseEntity.ok(MessageResponse("Foto de perfil actualizada."))
    }
}