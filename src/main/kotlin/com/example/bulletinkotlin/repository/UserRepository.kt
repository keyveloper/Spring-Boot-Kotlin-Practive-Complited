package com.example.bulletinkotlin.repository

import com.example.bulletinkotlin.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun findByUsername(username: String): User?

    fun existsUserBy(username: String): Boolean
}