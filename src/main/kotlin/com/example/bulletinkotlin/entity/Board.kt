package com.example.bulletinkotlin.entity

import jakarta.persistence.*
import lombok.Builder
import lombok.Data
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "boards")
@EntityListeners(AuditingEntityListener::class)
class Board(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var title: String,

    var writer: String,

    @Column(name = "text_content")
    var textContent: String = "",

    @CreatedDate
    @Column(name = "first_Writing_time")
    var firstWritingTime: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "last_modified_time")
    var lastModifiedTime: LocalDateTime? = null,

    @Column(name = "reading_count")
    var readingCount: Int = 0,

    @OneToMany(cascade = [(CascadeType.ALL)], mappedBy = "board") // 반대쪽 필드이름 Comment.board 이다.
    var comments: List<Comment> = mutableListOf()
)