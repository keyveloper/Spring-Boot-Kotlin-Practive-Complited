package com.example.bulletinkotlin.entity

import jakarta.persistence.*

@Entity
@Table(name = "roles")
class Role (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "name")
    var name: String,
)



