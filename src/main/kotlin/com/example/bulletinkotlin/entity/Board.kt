package com.example.bulletinkotlin.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "boards")
data class Board(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val title: String,
    val writer: String,
    @Column(name = "text_content")
    val textContent: String,
    @Column(name = "first_Writing_time")
    val firstWritingTime: LocalDateTime,
    @Column(name = "last_modified_time")
    val lastModifiedTime: LocalDateTime,
    @Column(name = "reading_count")
    val readingCount: Int,
    @OneToMany(cascade = [(CascadeType.ALL)], mappedBy = "board") // 반대쪽 필드이름 Comment.board 이다.
    val comments: List<Comment>
) {
    constructor() : this(0L, "", "", "", LocalDateTime.now(),
        LocalDateTime.now(),0, mutableListOf())
}
