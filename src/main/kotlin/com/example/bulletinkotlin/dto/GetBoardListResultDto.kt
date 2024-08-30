package com.example.bulletinkotlin.dto

import java.time.LocalDateTime

class GetBoardListResultDto (
    var id: Long,
    var title: String,
    var writer: String,
    var textContent: String,
    var firstWritingTime: LocalDateTime,
    var readingCount : Int,
)