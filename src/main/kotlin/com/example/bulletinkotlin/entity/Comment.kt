package com.example.bulletinkotlin.entity

import jakarta.persistence.*
import lombok.Data
import java.time.LocalDateTime

@Entity
@Table(name = "comments")
class Comment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @ManyToOne
    @JoinColumn(name = "board_id")
    var board: Board,
    var writer: String,
    @Column(name = "text_content")
    var textContent: String,
    @Column(name = "first_writing_time")
    var firstWritingTime: LocalDateTime,
    @Column(name = "last_modified_time")
    var lastModifiedTime: LocalDateTime
)