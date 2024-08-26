package com.example.bulletinkotlin.dto

import lombok.Getter
import java.time.LocalDateTime

class GetBoardResultDto(
    var id: Long,
    var title: String,
    var writer: String,
    var textContent: String,
    var firstWritingTime: LocalDateTime,
    var lastModifiedTime: LocalDateTime,
    var readingCount : Int,
    var comments: List<GetCommentResultDto>
)
