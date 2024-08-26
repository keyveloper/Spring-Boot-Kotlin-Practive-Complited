package com.example.bulletinkotlin.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "comments")
data class Comment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne
    @JoinColumn(name = "board_id")
    val board: Board,
    val writer: String,
    @Column(name = "text_content")
    val textContent: String,
    @Column(name = "first_writing_time")
    val firstWritingTime: LocalDateTime,
    @Column(name = "last_modified_time")
    val lastModifiedTime: LocalDateTime
) {
    constructor() : this(0L, Board(), "", "", LocalDateTime.now(), LocalDateTime.now())
}
