package com.example.bulletinkotlin.dto

import java.time.LocalDateTime

class GetBoardResponseDto(
    var id: Long,
    var title: String,
    var writer: String,
    var textContent: String,
    var firstWritingTime: LocalDateTime,
    var lastModifiedTime: LocalDateTime,
    var readingCount : Int,
    var comments: List<GetCommentResponseDto>
)
