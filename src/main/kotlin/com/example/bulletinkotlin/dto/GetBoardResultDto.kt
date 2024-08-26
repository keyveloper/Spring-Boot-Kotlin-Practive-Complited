package com.example.bulletinkotlin.dto

import lombok.Getter
import java.time.LocalDateTime

@Getter
data class GetBoardResultDto(
    val id: Long,
    val title: String,
    val writer: String,
    val textContent: String,
    val firstWritingTime: LocalDateTime,
    val lastModifiedByUser: LocalDateTime,
    val readingCount : Int,
    val comments: List<GetCommentResultDto>
)
