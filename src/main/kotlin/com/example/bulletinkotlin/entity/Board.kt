package com.example.bulletinkotlin.entity

import jakarta.persistence.*
import lombok.Data
import java.time.LocalDateTime

@Entity
@Table(name = "boards")
class Board(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var title: String,
    var writer: String,
    @Column(name = "text_content")
    var textContent: String = "",
    @Column(name = "first_Writing_time")
    var firstWritingTime: LocalDateTime,
    @Column(name = "last_modified_time")
    var lastModifiedTime: LocalDateTime,
    @Column(name = "reading_count")
    var readingCount: Int,
    @OneToMany(cascade = [(CascadeType.ALL)], mappedBy = "board") // 반대쪽 필드이름 Comment.board 이다.
    var comments: List<Comment>
)