package com.example.bulletinkotlin.dto

import lombok.*
import java.time.LocalDateTime

@Getter
data class GetCommentResultDto(
    val id: Long,
    val boardId: Long,
    val writer: String,
    val textContent: String,
    val firstWritingTime: LocalDateTime,
    val lastModifiedTime: LocalDateTime
)
