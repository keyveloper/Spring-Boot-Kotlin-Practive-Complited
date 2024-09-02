package com.example.bulletinkotlin.service

import com.example.bulletinkotlin.dto.SignUpRequestDto
import com.example.bulletinkotlin.entity.Role
import com.example.bulletinkotlin.entity.User
import com.example.bulletinkotlin.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun save(newUser: SignUpRequestDto) {
        // Encoder로 비번을 암호화 한 후 저장하자
        val encodedPassword = passwordEncoder.encode(newUser.password)
        val role = Role(
            name = "NORMAL"
        )

        val user = User(
            username = newUser.username,
            password = encodedPassword,
            enabled = true,
            roles = setOf(role)
        )
        userRepository.save(user)
    }
}