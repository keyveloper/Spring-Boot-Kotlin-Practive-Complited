package com.example.bulletinkotlin.repository

import com.example.bulletinkotlin.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: String): Role?

}