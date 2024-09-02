package com.example.bulletinkotlin.controller

import com.example.bulletinkotlin.security.JwtTokenProvider
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authenticationManger: AuthenticationManager,
    private val jwtTokenProvider: JwtTokenProvider,
    private val userDetailsService: UserDetailsService
    ) {

    @PostMapping("/auth/login")
    fun login(
        @RequestParam username: String,
        @RequestParam password: String,
    ): ResponseEntity<String> {
        return try {
            val authentication: Authentication = authenticationManger.authenticate(
                UsernamePasswordAuthenticationToken(username, password)
            )

            ResponseEntity.ok().body(jwtTokenProvider.generateToken(authentication))
        } catch (e: AuthenticationException) {
            throw RuntimeException("Invalid Username or Password")
        }
    }
}