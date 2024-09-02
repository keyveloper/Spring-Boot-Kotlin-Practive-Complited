package com.example.bulletinkotlin.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User (
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    var id: Long?,

    @Column(unique = true, name = "user_name", nullable = false)
    var username: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "enabled", nullable = false)
    var enabled: Boolean = true,

    @ManyToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.EAGER)
    @JoinTable(
        name = "roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: Set<Role> = setOf()
)
