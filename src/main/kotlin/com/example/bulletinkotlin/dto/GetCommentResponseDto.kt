package com.example.bulletinkotlin.dto

import lombok.*
import java.time.LocalDateTime

@Getter
data class GetCommentResponseDto(
    val id: Long,
    val writer: String,
    val textContent: String,
    val firstWritingTime: LocalDateTime,
    val lastModifiedTime: LocalDateTime
)
