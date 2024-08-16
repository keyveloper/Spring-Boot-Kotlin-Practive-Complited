package com.example.bulletinkotlin.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class Board(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val time: String,
    val writer: String,
    val textContent: String,
    val writingTime: LocalDateTime,
    val readingCount: Int
) {
    constructor() : this(0L, "", "", "", LocalDateTime.now(),  0)
}
