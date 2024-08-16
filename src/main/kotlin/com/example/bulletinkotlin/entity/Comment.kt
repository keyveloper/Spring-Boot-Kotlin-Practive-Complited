package com.example.bulletinkotlin.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class Comment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val boardId: Long,
    val writer: String,
    val textContent: String,
    val writingTime: LocalDateTime
) {
    constructor() : this(0L, 0L, "", "", LocalDateTime.now())
}
