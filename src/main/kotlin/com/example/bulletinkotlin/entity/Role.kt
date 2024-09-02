package com.example.bulletinkotlin.entity

import jakarta.persistence.*

@Entity
@Table(name = "roles")
class Role (
    @Id @GeneratedValue
    @Column(name= "role_id")
    var id: Long = 0,

    @Column(name = "name", unique = true)
    var name: String,
)