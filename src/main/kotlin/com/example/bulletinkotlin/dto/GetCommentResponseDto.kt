package com.example.bulletinkotlin.dto

import lombok.*
import java.time.LocalDateTime

class GetCommentResponseDto(
    var id: Long,
    var boardId: Long,
    var writer: String,
    var textContent: String,
    var firstWritingTime: LocalDateTime,
    var lastModifiedTime: LocalDateTime
)
